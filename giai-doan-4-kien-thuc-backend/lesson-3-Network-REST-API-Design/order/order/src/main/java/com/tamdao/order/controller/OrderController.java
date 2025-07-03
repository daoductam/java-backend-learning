package com.tamdao.order.controller;

import com.tamdao.order.dto.BaseResponse;
import com.tamdao.order.dto.OrderRequest;
import com.tamdao.order.dto.OrderResponse;
import com.tamdao.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.tamdao.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;


    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<OrderResponse>> getOrderById(@PathVariable String id) {
        OrderResponse response = orderService.getOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                BaseResponse.<OrderResponse>builder()
                        .httpCode(HttpStatus.OK.value())
                        .message("Lay don hang thanh cong")
                        .code("ORDER_FOUND")
                        .data(response).build());
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<OrderResponse>>> getAllOrders() {
        List<OrderResponse> orders = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(
                BaseResponse.<List<OrderResponse>>builder()
                        .httpCode(HttpStatus.OK.value())
                        .message("Lay danh sach don hang thanh cong")
                        .code("ORDER_LIST")
                        .data(orders).build());

    }

    @PostMapping("/")
    public ResponseEntity<BaseResponse<OrderResponse>> createOrder(@Valid @RequestBody OrderRequest request) {
        OrderResponse saved = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponse.<OrderResponse>builder()
                        .httpCode(HttpStatus.CREATED.value())
                        .message("Tao don hang thanh cong")
                        .code("ORDER_CREATED")
                        .data(saved).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<String>> updateOrder(
            @PathVariable String id,
            @Valid @RequestBody OrderRequest request
    ) {
        orderService.updateOrder(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(
                BaseResponse.<String>builder()
                        .httpCode(HttpStatus.OK.value())
                        .message("Cap nhat don hang thanh cong")
                        .code("ORDER_UPDATED")
                        .data(id)
                        .build());
    }

    @DeleteMapping("/id")
    public void deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);

    }
}
