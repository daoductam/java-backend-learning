package com.tamdao.spring_security.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tamdao.spring_security.dto.request.AuthenticationRequest;
import com.tamdao.spring_security.dto.request.IntrospectRequest;
import com.tamdao.spring_security.dto.response.AuthenticationResponse;
import com.tamdao.spring_security.dto.response.IntrospectResponse;
import com.tamdao.spring_security.entity.User;
import com.tamdao.spring_security.exception.AppException;
import com.tamdao.spring_security.exception.ErrorCode;
import com.tamdao.spring_security.repository.UserRepository;
import com.tamdao.spring_security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.StringJoiner;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @NonFinal // @NonFinal giúp Lombok không đưa field này vào constructor.
    @Value("${jwt.signerKey}") // Inject signerKey từ file application.properties.
    protected String SIGNER_KEY ;

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) // Xác minh token hợp lệ hay không
            throws JOSEException, ParseException {
        // Lấy JWT từ request.
        var token = request.getToken();

        //Tạo verifier để kiểm tra chữ ký.
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        // Parse JWT thành đối tượng SignedJWT.
        SignedJWT signedJWT = SignedJWT.parse(token);

        // Lấy thời hạn hết hạn của JWT.
        Date expityTime= signedJWT.getJWTClaimsSet().getExpirationTime();

        // Xác minh chữ ký của token đúng hay không.
        var verified = signedJWT.verify(verifier);

        // Trả về valid=true nếu Chữ ký đúng Chưa hết hạn
        return IntrospectResponse.builder()
                .valid(verified && expityTime.after(new Date()))
                .build();
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Dùng BCrypt để kiểm tra password (đã mã hóa).
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        // Tìm user theo username. Nếu không có → throw exception.
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));


        // So sánh password người dùng nhập với password trong DB.
        boolean authenticated =  passwordEncoder.matches(request.getPassword(),
                user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        // Nếu thành công, sinh JWT token và trả về.
        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }


    private String generateToken(User user) {
        // Header JWT chứa thuật toán dùng để ký (HMAC-SHA512).
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        /*
        Payload (claim) gồm:
            subject: username
            issuer: bên phát hành
            issueTime, expirationTime: thời gian sinh & hết hạn
            custom claim: scope – danh sách role của user
         */
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("tamdao174.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", buildScope(user))
                .build();

        // Tạo đối tượng JWSObject từ header + payload.
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            // Ký JWT với signerKey
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            //serialize() tạo chuỗi token hoàn chỉnh.
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Can not create token ", e);
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user) {
        // Nối các role bằng khoảng trắng, ví dụ: "ADMIN USER"
        StringJoiner stringJoiner= new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(stringJoiner::add);
        }

        return stringJoiner.toString();
    }
}
