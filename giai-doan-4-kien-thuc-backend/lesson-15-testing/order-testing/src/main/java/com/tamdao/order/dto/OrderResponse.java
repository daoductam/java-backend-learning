package com.tamdao.order.dto;

import com.tamdao.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String id;
    private String customerName;
    private String email;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private Instant createdAt;
}
