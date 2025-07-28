package com.tamdao.order.service;

import com.tamdao.order.dto.OrderRequest;
import com.tamdao.order.dto.OrderResponse;
import com.tamdao.order.entity.Order;
import com.tamdao.order.entity.Payment;
import com.tamdao.order.entity.SellerRevenue;
import com.tamdao.order.enums.OrderStatus;
import com.tamdao.order.enums.PaymentStatus;
import com.tamdao.order.exception.AppException;
import com.tamdao.order.exception.ErrorCode;
import com.tamdao.order.repository.OrderRepository;
import com.tamdao.order.repository.PaymentRepository;
import com.tamdao.order.repository.SellerRevenueRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.tamdao.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{


    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final SellerRevenueRepository revenueRepository;
    private final PaymentGatewayClient paymentGateway;
    private final EmailService emailService;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        log.info("[ORDER] Tạo đơn hàng cho customerName={}, sản phẩm={}, giá={}",
                request.getCustomerName(), request.getProductName(), request.getPrice());
        if (request.getQuantity() <= 0) {
            throw new AppException(ErrorCode.QUANTITY_NOT_ENOUGH);
        }
        Order order = Order.builder()
                .customerName(request.getCustomerName())
                .productName(request.getProductName())
                .email(request.getEmail())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .status(OrderStatus.INIT)
                .build();
        Order saved = orderRepository.save(order);

        String content = "Cam on ban da dat hang "+request.getProductName()+" x"+ request.getQuantity();
        emailService.sendMailWithRetry(request.getEmail(),"Xac nhan don hang", content);
        return orderMapper.toOrderResponse(saved);
    }

    @Override
    public OrderResponse getOrderById(String id) {
        log.info("Get order id={}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.ORDER_NOT_FOUND));
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
                .orElseThrow(()-> new AppException(ErrorCode.ORDER_NOT_FOUND));
        order.setCustomerName(request.getCustomerName());
        order.setProductName(request.getProductName());
        order.setEmail(request.getEmail());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String id) {
        log.info("Xóa Order id={}",id);
        if (!orderRepository.existsById(id)) {
            throw new AppException(ErrorCode.ORDER_NOT_FOUND);
        }
        orderRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void payOrder(String orderId) {
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
