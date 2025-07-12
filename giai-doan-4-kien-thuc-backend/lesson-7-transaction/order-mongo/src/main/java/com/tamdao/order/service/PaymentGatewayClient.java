package com.tamdao.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class PaymentGatewayClient {
    public boolean processPayment(String orderId, BigDecimal amount) {
        log.info("[GATEWAY] Thanh toán thành công cho orderId={}, số tiền={}", orderId, amount);
        return true;
    }
}
