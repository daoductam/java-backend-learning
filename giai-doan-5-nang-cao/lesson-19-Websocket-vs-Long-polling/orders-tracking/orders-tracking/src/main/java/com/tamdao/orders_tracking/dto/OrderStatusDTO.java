package com.tamdao.orders_tracking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDTO {
    private String orderId;
    private String status;
    private Instant updatedAt;
    private boolean pointsGranted;
}
