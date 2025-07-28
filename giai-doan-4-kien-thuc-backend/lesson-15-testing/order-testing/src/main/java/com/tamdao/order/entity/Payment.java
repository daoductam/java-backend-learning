package com.tamdao.order.entity;

import com.tamdao.order.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity {

    private String orderId;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
