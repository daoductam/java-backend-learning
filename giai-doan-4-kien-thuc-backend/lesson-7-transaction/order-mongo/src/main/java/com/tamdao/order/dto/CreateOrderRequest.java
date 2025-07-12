package com.tamdao.order.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CreateOrderRequest {
    private Long userId;
    private String productName;
    private BigDecimal price;
}
