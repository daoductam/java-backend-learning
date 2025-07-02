package com.tamdao.order.dto;

import com.tamdao.order.entity.Order;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class OrderResponse {
    private Order order;
}
