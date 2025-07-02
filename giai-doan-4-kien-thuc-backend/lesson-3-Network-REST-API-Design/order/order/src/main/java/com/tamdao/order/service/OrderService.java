package com.tamdao.order.service;

import com.tamdao.order.dto.OrderRequest;
import com.tamdao.order.dto.OrderResponse;
import com.tamdao.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order createOrder(OrderRequest request);
    Order getOrderById(String id);
    List<Order> getAllOrders();
    Order updateOrder(String id, OrderRequest request);
    void deleteOrder(String id);
}
