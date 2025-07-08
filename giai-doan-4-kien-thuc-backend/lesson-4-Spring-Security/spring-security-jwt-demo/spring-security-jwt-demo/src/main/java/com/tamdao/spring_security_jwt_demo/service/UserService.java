package com.tamdao.spring_security_jwt_demo.service;

import com.tamdao.spring_security_jwt_demo.dto.UserRequest;

public interface UserService {
    void createUser(UserRequest request);
}
