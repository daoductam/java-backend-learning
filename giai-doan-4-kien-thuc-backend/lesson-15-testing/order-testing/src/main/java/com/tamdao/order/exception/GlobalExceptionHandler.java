package com.tamdao.order.exception;

import com.tamdao.order.dto.BaseResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleAllExceptions(RuntimeException ex) {
        return ResponseEntity.badRequest().body(
                BaseResponse.builder()
                        .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
                        .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<BaseResponse<?>> handleAppException(AppException ex) {
        return ResponseEntity
                .status(ex.getErrorCode().getStatusCode())
                .body(
                    BaseResponse.builder()
                            .code(ex.getErrorCode().getCode())
                            .message(ex.getErrorCode().getMessage())
                            .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<BaseResponse<?>> handlingValidation(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError() != null
                ? exception.getFieldError().getDefaultMessage()
                : "INVALID_KEY";
        ErrorCode errorCode ;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException | NullPointerException e) {
            errorCode  = ErrorCode.INVALID_KEY;
        }

        return ResponseEntity.badRequest().body(
                BaseResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build()
        );
    }

}