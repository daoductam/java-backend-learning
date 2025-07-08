package com.tamdao.spring_security_jwt_demo.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
