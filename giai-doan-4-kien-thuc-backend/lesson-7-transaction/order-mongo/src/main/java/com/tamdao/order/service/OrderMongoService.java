package com.tamdao.order.service;

import com.mongodb.ClientSessionOptions;
import com.mongodb.client.ClientSession;
import com.tamdao.order.dto.CreateOrderRequest;
import com.tamdao.order.entity.Order;
import com.tamdao.order.entity.Payment;
import com.tamdao.order.entity.SellerRevenue;
import com.tamdao.order.enums.OrderStatus;
import com.tamdao.order.enums.PaymentStatus;
import com.tamdao.order.repository.OrderMongoRepository;
import com.tamdao.order.repository.PaymentMongoRepository;
import com.tamdao.order.repository.SellerMongoRevenueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderMongoService {
    private final MongoTemplate mongoTemplate;
    private final PaymentGatewayClient paymentGateway;

    public Order createOrder(CreateOrderRequest request) {
        log.info("[ORDER][Mongo] Tạo đơn hàng: {}", request);
        Order order = Order.builder()
                .userId(request.getUserId())
                .productName(request.getProductName())
                .price(request.getPrice())
                .status(OrderStatus.INIT)
                .build();
        return mongoTemplate.insert(order);
    }

    public void payOrder(String orderId) {
        MongoDatabaseFactory factory = mongoTemplate.getMongoDatabaseFactory();
        ClientSession session = factory.getSession(ClientSessionOptions.builder().build());

        try {
            session.startTransaction();

            Order order = mongoTemplate.findById(orderId, Order.class);
            if (order == null) throw new RuntimeException("Không tìm thấy đơn hàng");
            if (order.getStatus() == OrderStatus.DONE) throw new IllegalArgumentException("Đã thanh toán");

            log.info("[PAYMENT][Mongo] Bắt đầu thanh toán cho orderId={}", orderId);

            Payment payment = Payment.builder()
                    .orderId(orderId)
                    .amount(order.getPrice())
                    .status(PaymentStatus.INIT)
                    .build();
            mongoTemplate.insert(payment);

            boolean success = paymentGateway.processPayment(orderId, order.getPrice());
            if (!success) throw new RuntimeException("Thanh toán thất bại");

            order.setStatus(OrderStatus.DONE);
            payment.setStatus(PaymentStatus.SUCCESS);
            mongoTemplate.save(order);
            mongoTemplate.save(payment);

            SellerRevenue revenue = mongoTemplate.findById("1", SellerRevenue.class);
            if (revenue == null) {
                revenue = SellerRevenue.builder()
                        .sellerId("1")
                        .totalRevenue(BigDecimal.ZERO)
                        .build();
            }
            revenue.setTotalRevenue(revenue.getTotalRevenue().add(order.getPrice()));
            mongoTemplate.save(revenue);

            session.commitTransaction();
            log.info("[PAYMENT][Mongo] Thanh toán thành công cho orderId={}", orderId);
        } catch (Exception e) {
            log.error("[ERROR][Mongo] Lỗi thanh toán, rollback: {}", e.getMessage());
            session.abortTransaction();
            throw new RuntimeException("Rollback do lỗi: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
