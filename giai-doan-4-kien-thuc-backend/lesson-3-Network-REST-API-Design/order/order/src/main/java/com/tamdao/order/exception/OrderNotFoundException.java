package com.tamdao.order.exception;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends BaseException{
    public OrderNotFoundException(String message) {
        super("Khong tim thay don hang","ORDER_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
