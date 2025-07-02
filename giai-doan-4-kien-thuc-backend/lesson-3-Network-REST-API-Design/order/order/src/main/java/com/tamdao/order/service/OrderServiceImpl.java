package com.tamdao.order.service;

import com.tamdao.order.dto.OrderRequest;
import com.tamdao.order.dto.OrderResponse;
import com.tamdao.order.entity.Order;
import com.tamdao.order.exception.OrderNotFoundException;
import com.tamdao.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(OrderRequest request) {
        log.info("Tạo Order request={}", request);
        Order order = Order.builder()
                .customerName(request.getCustomerName())
                .productName(request.getProductName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .createdAt(Instant.now())
                .build();
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(String id) {
        log.info("Get order id={}", id);
        return orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Không tìm thấy Order với id: " + id));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(String id, OrderRequest request) {
        log.info("Cập nhật Order id={}",id);
        Order order =  getOrderById(id);
        order.setCustomerName(request.getCustomerName());
        order.setProductName(request.getProductName());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());
        order.setUpdatedAt(Instant.now());
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String id) {
        log.info("Xóa Order id={}",id);
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Không tìm thấy Order id: "+id);
        }
        orderRepository.deleteById(id);
    }
}
