package com.tamdao.spring_security.exception;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Getter
public class BaseException extends RuntimeException{
    private final String code;
    private final HttpStatus httpStatus;

    @Builder
    public BaseException(String message, String code, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
