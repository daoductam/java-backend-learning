package com.tamdao.order.service;

import com.tamdao.order.entity.Order;
import com.tamdao.order.enums.OrderStatus;
import com.tamdao.order.repository.OrderRepository;
import com.tamdao.order.repository.PaymentRepository;
import com.tamdao.order.repository.SellerRevenueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class OrderPaymentServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private SellerRevenueRepository revenueRepository;

    @Mock
    private PaymentGatewayClient paymentGateway;

    @InjectMocks
    private OrderPaymentService orderPaymentService;

    private Order fakeOrder;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        fakeOrder = Order.builder()
                .id(1L)
                .userId(1L)
                .productName("Test Product")
                .price(BigDecimal.valueOf(100000))
                .status(OrderStatus.INIT)
                .build();
    }

    @Test
    @Transactional
    void testRollbackWhenExceptionOccursAfterPayment() {
        // Given
        when(orderRepository.findById(1L)).thenReturn(Optional.of(fakeOrder));
        when(paymentGateway.processPayment(eq(1L), any())).thenReturn(true);

        // Giả lập lỗi khi update order → sẽ throw exception
        doThrow(new RuntimeException("Fake lỗi khi cập nhật trạng thái đơn hàng"))
                .when(orderRepository).save(argThat(order -> order.getStatus() == OrderStatus.DONE));

        // When + Then
        assertThatThrownBy(() -> orderPaymentService.payOrder(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Fake lỗi");

        // Verify rollback: không lưu trạng thái DONE và không gọi update doanh thu
        verify(orderRepository, times(1)).findById(1L);
        verify(paymentRepository, times(1)).save(any());
        verify(orderRepository, times(1)).save(any());
        verify(revenueRepository, never()).save(any());
    }
}
