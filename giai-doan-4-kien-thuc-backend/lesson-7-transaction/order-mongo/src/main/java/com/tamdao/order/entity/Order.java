package com.tamdao.order.entity;

import com.tamdao.order.enums.OrderStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.math.BigDecimal;

@Document(collection = "orders")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String id;

    private Long userId;

    private String productName;

    private BigDecimal price;

    private OrderStatus status;
}
