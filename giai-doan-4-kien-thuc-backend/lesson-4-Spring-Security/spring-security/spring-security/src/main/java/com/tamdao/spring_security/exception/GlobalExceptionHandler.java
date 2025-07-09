package com.tamdao.spring_security.exception;

import com.tamdao.spring_security.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

//    @ExceptionHandler(value = AccessDeniedException.class)
//    ResponseEntity<Object> handlingAccessDeniedException(AccessDeniedException exception) {
//        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
//        return ResponseEntity
//                .status(errorCode.getStatusCode())
//                .body(
//                  BaseResponse.builder()
//                          .code(errorCode.getCode())
//                          .message(errorCode.getMessage())
//                          .build()
//                );
//    }
}