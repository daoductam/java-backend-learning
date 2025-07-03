package com.tamdao.spring_security.service;

import com.tamdao.spring_security.dto.AuthenticationRequest;
import org.springframework.stereotype.Service;


public interface AuthenticationService {
    boolean authenticate(AuthenticationRequest request);
}
