package com.tamdao.order.intergration;

import com.tamdao.order.config.TestEmailConfig;
import com.tamdao.order.dto.OrderRequest;
import com.tamdao.order.entity.Order;
import com.tamdao.order.repository.OrderRepository;
import com.tamdao.order.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestEmailConfig.class)
@ActiveProfiles("test")
public class OrderIntegrationTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
    }

    @Test
    void testCreateOrder_Success() {
        OrderRequest request = OrderRequest.builder()
                .customerName("Nguyen Van A")
                .email("a@gmail.com")
                .productName("iphone 15")
                .quantity(1)
                .price(BigDecimal.valueOf(25000000))
                .build();

        client.post()
                .uri("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.data.productName").isEqualTo("iphone 15")
                .jsonPath("$.data.email").isEqualTo("a@gmail.com");

        List<Order> orders = orderRepository.findAll();
        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).getCustomerName()).isEqualTo("Nguyen Van A");

        // Verify email được gọi
        Mockito.verify(emailService).sendMailWithRetry(
                Mockito.eq("a@gmail.com"),
                Mockito.eq("Xac nhan don hang"),
                Mockito.contains("iphone 15"));
    }

    @Test
    void testGetAllOrders() {
        client.get()
                .uri("/api/orders")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.data").isArray();
    }

    @Test
    void testGetOrderById_notFound() {
        client.get()
                .uri("/api/orders/invalid-id-123")
                .exchange()
                .expectStatus().isEqualTo(500);
    }
}
