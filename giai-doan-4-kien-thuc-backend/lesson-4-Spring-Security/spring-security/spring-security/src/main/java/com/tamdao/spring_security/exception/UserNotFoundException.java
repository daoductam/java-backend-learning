package com.tamdao.spring_security.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException{
    public UserNotFoundException() {
        super("Khong tim thay tai khoan","USER_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
