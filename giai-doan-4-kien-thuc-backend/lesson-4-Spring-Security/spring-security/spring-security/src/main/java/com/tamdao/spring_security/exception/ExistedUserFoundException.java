package com.tamdao.spring_security.exception;

import org.springframework.http.HttpStatus;

public class ExistedUserFoundException extends BaseException{
    public ExistedUserFoundException() {
        super("tai khoan da ton tai","EXISTED_USER_FOUND", HttpStatus.NOT_FOUND);
    }
}
