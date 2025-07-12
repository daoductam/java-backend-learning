package com.tamdao.order.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "revenues")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerRevenue {
    @Id
    private String sellerId;

    private BigDecimal totalRevenue;
}
