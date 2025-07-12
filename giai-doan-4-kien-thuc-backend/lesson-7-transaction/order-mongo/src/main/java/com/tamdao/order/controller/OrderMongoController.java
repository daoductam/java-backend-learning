package com.tamdao.order.controller;

import com.tamdao.order.dto.CreateOrderRequest;
import com.tamdao.order.entity.Order;
import com.tamdao.order.service.OrderMongoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mongo/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderMongoController {

    private final OrderMongoService orderMongoService;

    // Tạo đơn hàng mới
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {
        Order order = orderMongoService.createOrder(request);
        return ResponseEntity.ok(order);
    }

    // Thanh toán đơn hàng (có transaction rollback nếu lỗi)
    @PostMapping("/{id}/pay")
    public ResponseEntity<String> payOrder(@PathVariable String id) {
        try {
            orderMongoService.payOrder(id);
            return ResponseEntity.ok("Thanh toán thành công");
        } catch (Exception e) {
            log.error("[ERROR][Mongo] Lỗi thanh toán: {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Thanh toán thất bại: " + e.getMessage());
        }
    }
}