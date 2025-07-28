package com.tamdao.SecurityDemo.service;

import com.tamdao.SecurityDemo.dto.UserCreateRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    boolean loginInsecure(String username, String password) ;

    boolean loginSecure(String username, String password) ;

    String register(UserCreateRequest request);

}
