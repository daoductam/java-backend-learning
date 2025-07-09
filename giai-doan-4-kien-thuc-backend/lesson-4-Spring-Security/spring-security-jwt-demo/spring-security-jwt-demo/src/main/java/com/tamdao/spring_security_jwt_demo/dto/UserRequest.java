package com.tamdao.spring_security_jwt_demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class UserRequest {
    private String username;
    private String password;

}
