package com.tamdao.order.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity

@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {

    @NotBlank(message = "Tên khách hàng không được để trống ")
    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Column(name = "product_name", nullable = false)
    private String productName;

    @Positive(message = "Số lượng phải lớn hơn 0")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @DecimalMin(value = "0.0", inclusive = false, message = "Giá phải lớn hơn 0")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

}
