package com.tamdao.payment.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    UserNotFoundException(1001,"USER_NOT_FOUND",HttpStatus.NOT_FOUND),
    DuplicatedIdempotentKeyException(1002,"DUPLICATED_IDEMPOTENT_KEY",HttpStatus.CONFLICT),
    NotEnoughBalanceException(1003,"NOT_ENOUGH_BALANCE", HttpStatus.BAD_REQUEST),
    TooManyRequestException(1004, "TOO_MANY_REQUEST",HttpStatus.TOO_MANY_REQUESTS);

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
