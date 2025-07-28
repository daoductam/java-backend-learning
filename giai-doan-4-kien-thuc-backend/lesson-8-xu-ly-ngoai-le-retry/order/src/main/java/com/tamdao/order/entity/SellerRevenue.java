package com.tamdao.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "seller_revenue")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerRevenue {
    @Id
    private Long sellerId;

    private BigDecimal totalRevenue;
}
