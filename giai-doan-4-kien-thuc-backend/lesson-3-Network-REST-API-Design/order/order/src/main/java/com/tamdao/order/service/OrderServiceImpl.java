package com.tamdao.order.service;

import com.tamdao.order.dto.OrderRequest;
import com.tamdao.order.dto.OrderResponse;
import com.tamdao.order.entity.Order;
import com.tamdao.order.exception.OrderNotFoundException;
import com.tamdao.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.tamdao.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        log.info("Tạo Order request={}", request);
        Order order = Order.builder()
                .customerName(request.getCustomerName())
                .productName(request.getProductName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
        Order saved = orderRepository.save(order);
        return orderMapper.toOrderResponse(saved);
    }

    @Override
    public OrderResponse getOrderById(String id) {
        log.info("Get order id={}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Không tìm thấy Order với id: " + id));
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    @Override
    public void updateOrder(String id, OrderRequest request) {
        log.info("Cập nhật Order id={}",id);
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Không tìm thấy Order với id: " + id));        order.setCustomerName(request.getCustomerName());
        order.setProductName(request.getProductName());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());
        orderRepository.save(order);
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
