package com.tamdao.l3_database.demo.service;


import com.tamdao.l3_database.demo.dto.OrderDto;
import com.tamdao.l3_database.demo.entity.Order;
import com.tamdao.l3_database.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        order.setAmount(orderDto.getAmount());
        return orderRepository.save(order);
    }
}
