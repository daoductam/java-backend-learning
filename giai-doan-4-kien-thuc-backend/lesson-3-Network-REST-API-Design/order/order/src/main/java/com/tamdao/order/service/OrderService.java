package com.tamdao.order.service;

import com.tamdao.order.dto.OrderRequest;
import com.tamdao.order.dto.OrderResponse;
import com.tamdao.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderResponse createOrder(OrderRequest request);
    OrderResponse getOrderById(String id);
    List<OrderResponse> getAllOrders();
    void updateOrder(String id, OrderRequest request);
    void deleteOrder(String id);
}
