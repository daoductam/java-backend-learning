package com.tamdao.spring_security.controller;

import com.nimbusds.jose.JOSEException;
import com.tamdao.spring_security.dto.request.AuthenticationRequest;
import com.tamdao.spring_security.dto.response.AuthenticationResponse;
import com.tamdao.spring_security.dto.response.BaseResponse;
import com.tamdao.spring_security.dto.request.IntrospectRequest;
import com.tamdao.spring_security.dto.response.IntrospectResponse;
import com.tamdao.spring_security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;



    @PostMapping("/login")
    BaseResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return BaseResponse.<AuthenticationResponse>builder()
                .data(result)
                .build();
    }

    @PostMapping("/introspect")
    BaseResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return BaseResponse.<IntrospectResponse>builder()
                .data(result)
                .build();
    }
}
