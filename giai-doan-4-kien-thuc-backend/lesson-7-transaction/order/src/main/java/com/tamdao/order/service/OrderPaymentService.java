package com.tamdao.order.service;

import com.tamdao.order.dto.CreateOrderRequest;
import com.tamdao.order.entity.Order;
import com.tamdao.order.entity.Payment;
import com.tamdao.order.entity.SellerRevenue;
import com.tamdao.order.enums.OrderStatus;
import com.tamdao.order.enums.PaymentStatus;
import com.tamdao.order.repository.OrderRepository;
import com.tamdao.order.repository.PaymentRepository;
import com.tamdao.order.repository.SellerRevenueRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderPaymentService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final SellerRevenueRepository revenueRepository;
    private final PaymentGatewayClient paymentGateway;


    public Order createOrder(CreateOrderRequest request) {
        log.info("[ORDER] Tạo đơn hàng cho userId={}, sản phẩm={}, giá={}",
                request.getUserId(), request.getProductName(), request.getPrice());
        Order order = Order.builder()
                .userId(request.getUserId())
                .productName(request.getProductName())
                .price(request.getPrice())
                .status(OrderStatus.INIT)
                .build();
        return orderRepository.save(order);
    }
    @Transactional
    public void payOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if(order.getStatus() == OrderStatus.DONE) {
            throw new IllegalArgumentException("Đơn hàng đã thanh toaán rồi");
        }

        log.info("[PAYMENT] Bắt đầu thanh toán cho orderId={}, giá={}", order.getId(), order.getPrice());

        Payment payment = Payment.builder()
                .orderId(orderId)
                .amount(order.getPrice())
                .status(PaymentStatus.INIT)
                .build();
        paymentRepository.save(payment);

        boolean success = paymentGateway.processPayment(orderId, order.getPrice());
        if (!success) {
            throw new RuntimeException("Thanh Toán Thất bại");
        }

//        if (true) {
//            throw new RuntimeException("Fake lỗi khi cập nhật trạng thái đơn hàng");
//        }

        order.setStatus(OrderStatus.DONE);
        payment.setStatus(PaymentStatus.SUCCESS);
        orderRepository.save(order);
        paymentRepository.save(payment);

        SellerRevenue revenue = revenueRepository.findById(1L)
                .orElse(SellerRevenue.builder().sellerId(1L).totalRevenue(BigDecimal.ZERO).build());
        revenue.setTotalRevenue(revenue.getTotalRevenue().add(order.getPrice()));

        log.info("[PAYMENT] Thanh toán thành công cho orderId={}", orderId);

    }
}
