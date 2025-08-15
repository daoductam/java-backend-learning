package com.tamdao.orders_tracking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();
}
