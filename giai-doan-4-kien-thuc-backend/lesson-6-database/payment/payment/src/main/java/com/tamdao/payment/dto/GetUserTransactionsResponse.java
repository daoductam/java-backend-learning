package com.tamdao.payment.dto;

import com.tamdao.payment.entity.Transaction;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserTransactionsResponse {
    private List<Transaction> transactions;
}
