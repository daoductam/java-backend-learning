package com.tamdao.order.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @NotBlank(message = "Tên khách hàng không được để trống")
    private String customerName;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String productName;

    @NotNull(message = "Số lượng là bắt buộc")
    @Positive(message = "Số lượng phải lớn hơn 0")
    private Integer quantity;

    private String email;

    @NotNull(message = "Giá là bắt buộc")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá phải lớn hơn 0")
    private BigDecimal price;
}
