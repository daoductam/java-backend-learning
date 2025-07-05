package com.tamdao.spring_security.config;

import com.tamdao.spring_security.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Mảng các API không cần đăng nhập (permitAll())
    private final String[] PUBLIC_ENDPOINTS={"/users",
            "/auth/token", "/auth/introspect"};

    // Lấy key để decode JWT từ file application.properties
    @Value("${jwt.signerKey}")
    private String signerKey;

    // Tạo chuỗi filter bảo mật chính.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        /*
        POST /users, /auth/token, /auth/introspect → ai cũng truy cập được.
        GET /users → yêu cầu role là ADMIN.
         */
        httpSecurity.authorizeHttpRequests(request ->
                request.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET,"/users")
//                        .hasAuthority("ROLE_ADMIN")
                        .hasRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/dashboard").hasRole(Role.ADMIN.name())
                        .anyRequest().authenticated());

        // Cấu hình dùng JWT để xác thực
        // Bảo mật dựa theo chuẩn OAuth2 Resource Server
        httpSecurity.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwtConfigurer ->
                        jwtConfigurer.decoder(jwtDecoder()) // dùng secret để verify JWT
                                // Dùng JwtAuthenticationConverter để trích role từ claim "scope"
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())));

        // Tắt CSRF nếu không dùng form HTML (REST API không cần CSRF).
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        // Dùng để trích xuất "scope" từ JWT và chuyển thành GrantedAuthority
        // Ví dụ: nếu "scope": "ADMIN" thì sau khi prefix "ROLE_", Spring hiểu là "ROLE_ADMIN".
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    // Giải mã JWT theo thuật toán HMAC-SHA512 (HS512) với signerKey được cấu hình từ file properties.
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(),"HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    //Mã hóa password bằng BCrypt, dùng strength = 10.
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}