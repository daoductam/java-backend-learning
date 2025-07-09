package com.tamdao.spring_security.service;

import com.nimbusds.jose.JOSEException;
import com.tamdao.spring_security.dto.request.AuthenticationRequest;
import com.tamdao.spring_security.dto.request.IntrospectRequest;
import com.tamdao.spring_security.dto.response.AuthenticationResponse;
import com.tamdao.spring_security.dto.response.IntrospectResponse;

import java.text.ParseException;


public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    IntrospectResponse introspect(IntrospectRequest response) throws JOSEException, ParseException;
}
