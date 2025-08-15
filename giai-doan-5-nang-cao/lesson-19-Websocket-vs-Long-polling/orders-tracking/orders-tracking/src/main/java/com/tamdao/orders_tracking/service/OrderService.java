package com.tamdao.orders_tracking.service;

import com.tamdao.orders_tracking.dto.OrderStatusDTO;
import com.tamdao.orders_tracking.entity.Order;
import com.tamdao.orders_tracking.repository.OrderRepository;
import com.tamdao.orders_tracking.utils.OrderWaiterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderWaiterRegistry orderWaiterRegistry;
    private final RestTemplate rest = new RestTemplate();
    @Value("${app.ws.endpoint}")
    private String wsEndpoint;
    @Value("${app.ws.token}")
    private String wsToken;

    private Optional<Order> find(String id) {
        return orderRepository.findById(id);
    }

    public Order updateStatus(String id, String status) {
        var order = orderRepository.findById(id).orElseThrow();
        order.setStatus(status);
        order.setUpdatedAt(Instant.now());
        orderRepository.save(order);

        OrderStatusDTO orderStatusDTO = new OrderStatusDTO(
                order.getId(),
                order.getStatus(),
                order.getUpdatedAt(),
                false
        );

        orderWaiterRegistry.notifyChange(id, orderStatusDTO);

        sendWsCallback(orderStatusDTO);
        return order;
    }

    public void grantPoints(String id) {
        var order = orderRepository.findById(id).orElseThrow();
        var dto = new OrderStatusDTO(
                order.getId(),
                order.getStatus(),
                order.getUpdatedAt(),
                true
        );
        sendWsCallback(dto);
    }

    public void sendWsCallback(OrderStatusDTO orderStatusDTO) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("tamdao", wsToken);
        var request = new HttpEntity<>(orderStatusDTO, headers);
        try {
            rest.postForEntity(wsEndpoint,request, Void.class);
        } catch (Exception e) {

        }
    }
}
