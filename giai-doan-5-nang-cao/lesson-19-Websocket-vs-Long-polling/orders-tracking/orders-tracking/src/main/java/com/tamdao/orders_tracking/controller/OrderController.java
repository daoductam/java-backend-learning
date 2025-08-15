package com.tamdao.orders_tracking.controller;

import com.tamdao.orders_tracking.service.OrderService;
import com.tamdao.orders_tracking.utils.OrderWaiterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderWaiterRegistry waiters;

    // Long Polling endpoint: giữ tối đa 20s
    @GetMapping("/status")
    public DeferredResult<ResponseEntity<?>> getStatus(@RequestParam String orderId) {
        var current = orderService.find(orderId).orElse(null);
        if (current == null) {
            return immediate(ResponseEntity.noContent().build());
        }
        var deferredResult = waiters.register(orderId, 20_000);
        return deferredResult;
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<?> update(@PathVariable String orderId, @RequestBody Map<String, String> body) {
        var status = body.get("status");
        var order = orderService.updateStatus(orderId, status);
        if ("COMPLETED".equals(status)) {
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {}
                orderService.grantPoints(orderId);
            });
        }
        return ResponseEntity.ok(Map.of("orderId", order.getId(),
                                        "status", order.getStatus()));
    }

    private DeferredResult<ResponseEntity<?>> immediate(ResponseEntity<?> response) {
        var deferred = new DeferredResult<ResponseEntity<?>>(0L);
        deferred.setResult(response);
        return deferred;
    }
}
