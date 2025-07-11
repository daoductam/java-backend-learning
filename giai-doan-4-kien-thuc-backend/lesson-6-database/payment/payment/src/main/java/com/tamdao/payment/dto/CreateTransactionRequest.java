package com.tamdao.payment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class CreateTransactionRequest {
    @Positive
    @NotNull
    private int userId;

    @Positive
    @NotNull
    private int amount;

    @Length(max = 100, min = 10)
    @NotNull
    private String idempotentKey;
}
