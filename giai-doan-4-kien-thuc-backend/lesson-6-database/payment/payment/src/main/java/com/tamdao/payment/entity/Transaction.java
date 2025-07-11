package com.tamdao.payment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "transaction")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "id")
    private  int id;

    @Column(name = "idempotent_key", unique = true, nullable = false, length = 200)
    private String idempotentKey;

    @Column(name = "user_id",nullable = false)
    private int userId;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "balance_before_transaction", nullable = false)
    private int balanceBeforeTransaction;

    @Column(name = "balance_after_transaction", nullable = false)
    private int balanceAfterTransaction;

    @CreationTimestamp
    private Instant createdOn;
}
