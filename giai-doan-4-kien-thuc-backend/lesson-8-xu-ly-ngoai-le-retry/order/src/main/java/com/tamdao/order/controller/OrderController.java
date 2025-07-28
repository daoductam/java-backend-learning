package com.tamdao.order.controller;

import com.tamdao.order.dto.BaseResponse;
import com.tamdao.order.dto.OrderRequest;
import com.tamdao.order.dto.OrderResponse;
import com.tamdao.order.exception.AppException;
import com.tamdao.order.exception.ErrorCode;
import com.tamdao.order.service.EmailService;
import com.tamdao.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.tamdao.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<OrderResponse>> getOrderById(@PathVariable String id) {
        log.info("[API] GET /api/orders/{} - lay don hang tu id", id);
        OrderResponse response = orderService.getOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                BaseResponse.<OrderResponse>builder()
                        .message("Lay don hang thanh cong")
                        .data(response).build());
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<OrderResponse>>> getAllOrders() {
        log.info("[API] GET /api/orders - lay tat ca don hang tu");
        List<OrderResponse> orders = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(
                BaseResponse.<List<OrderResponse>>builder()
                        .message("Lay danh sach don hang thanh cong")
                        .data(orders).build());

    }

    @PostMapping("/")
    public ResponseEntity<BaseResponse<OrderResponse>> createOrder(@Valid @RequestBody OrderRequest request) {
        log.info("[API] POST /api/orders - Tao don hang");

        OrderResponse saved = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponse.<OrderResponse>builder()
                        .message("Tao don hang thanh cong va email da gui")
                        .data(saved).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<String>> updateOrder(
            @PathVariable String id,
            @Valid @RequestBody OrderRequest request
    ) {
        log.info("[API] PUT /api/orders - cap nhat don hang");
        orderService.updateOrder(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(
                BaseResponse.<String>builder()
                        .message("Cap nhat don hang thanh cong")
                        .data(id)
                        .build());
    }

    @DeleteMapping("/id")
    public BaseResponse<String> deleteOrder(@PathVariable String id) {
        log.info("[API] DELETE /api/orders/{} - xoa don hang tu id", id);
        orderService.deleteOrder(id);
        return BaseResponse.<String>builder()
                .data("Order has been deleted").build();
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<String> payOrder(@PathVariable("id") String orderId) {
        log.info("[API] POST /api/orders/{}/pay - thanh toán đơn hàng", orderId);
        orderService.payOrder(orderId);
        return ResponseEntity.ok("Thanh toán thành công cho đơn hàng: " + orderId);
    }
}
