package com.tamdao.order.service;

import com.tamdao.order.dto.OrderRequest;
import com.tamdao.order.dto.OrderResponse;
import com.tamdao.order.entity.Order;
import com.tamdao.order.enums.OrderStatus;
import com.tamdao.order.exception.AppException;
import com.tamdao.order.mapper.OrderMapper;
import com.tamdao.order.repository.OrderRepository;
import com.tamdao.order.repository.PaymentRepository;
import com.tamdao.order.repository.SellerRevenueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private SellerRevenueRepository revenueRepository;

    @Mock
    private EmailService emailService;
    @Mock
    private OrderMapper orderMapper;

    @BeforeEach
    public void init() {
        orderService = new OrderServiceImpl(orderRepository,paymentRepository, revenueRepository,
                 emailService, orderMapper);
    }

    @Test
    void testCreateOrder_Success() {

        // Given
        OrderRequest orderRequest = OrderRequest.builder()
                .customerName("Tam Dao")
                .productName("Macbook Pro")
                .email("tam@gmail.com")
                .quantity(2)
                .price(new BigDecimal("1999.99"))
                .build();

        Order mockOrder = Order.builder()
                .id(UUID.randomUUID().toString())
                .customerName(orderRequest.getCustomerName())
                .productName(orderRequest.getProductName())
                .email(orderRequest.getEmail())
                .quantity(orderRequest.getQuantity())
                .price(orderRequest.getPrice())
                .status(OrderStatus.INIT)
                .build();

        OrderResponse expectedResponse = OrderResponse.builder()
                .id(mockOrder.getId())
                .customerName(mockOrder.getCustomerName())
                .email(mockOrder.getEmail())
                .productName(mockOrder.getProductName())
                .quantity(mockOrder.getQuantity())
                .price(mockOrder.getPrice())
                .build();

        when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);
        when(orderMapper.toOrderResponse(mockOrder)).thenReturn(expectedResponse);

        // When
        OrderResponse result = orderService.createOrder(orderRequest);

        // Then
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderCaptor.capture());
        verify(orderMapper).toOrderResponse(mockOrder);
        verify(emailService).sendMailWithRetry(eq(orderRequest.getEmail()),
                anyString(), contains("Cam on ban da dat hang"));

        Order savedOrder = orderCaptor.getValue();
        assertThat(savedOrder.getCustomerName()).isEqualTo(orderRequest.getCustomerName());
        assertThat(savedOrder.getQuantity()).isEqualTo(orderRequest.getQuantity());
        assertThat(result).isNotNull();
        assertThat(result.getProductName()).isEqualTo(orderRequest.getProductName());
    }

    @Test
    void testCreateOrder_InvalidQuantity_ThrowsException() {

        OrderRequest request = new OrderRequest();
        request.setQuantity(0);

        AppException exception =  assertThrows(AppException.class, () -> {
            orderService.createOrder(request);
        });

        assertEquals("QUANTITY_NOT_ENOUGH", exception.getErrorCode().name());
        verify(orderRepository, never()).save(any());
        verify(emailService, never()).sendMailWithRetry(any(), any(),any());
    }
}
