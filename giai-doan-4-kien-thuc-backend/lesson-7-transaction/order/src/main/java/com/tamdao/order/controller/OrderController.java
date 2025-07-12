package com.tamdao.order.controller;

import com.tamdao.order.dto.CreateOrderRequest;
import com.tamdao.order.entity.Order;
import com.tamdao.order.service.OrderPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderPaymentService orderPaymentService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {
        log.info("[API] POST /api/orders - tạo đơn hàng");
        Order createdOrder = orderPaymentService.createOrder(request);
        return ResponseEntity.ok(createdOrder);

    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<String> payOrder(@PathVariable("id") Long orderId) {
        log.info("[API] POST /api/orders/{}/pay - thanh toán đơn hàng", orderId);
        orderPaymentService.payOrder(orderId);
        return ResponseEntity.ok("Thanh toán thành công cho đơn hàng: " + orderId);
    }
}
