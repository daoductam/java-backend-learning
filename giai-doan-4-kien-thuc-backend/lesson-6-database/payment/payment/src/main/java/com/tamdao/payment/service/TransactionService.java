package com.tamdao.payment.service;

import com.tamdao.payment.dto.CreateTransactionRequest;
import com.tamdao.payment.dto.CreateTransactionResponse;
import com.tamdao.payment.dto.GetUserTransactionsRequest;
import com.tamdao.payment.dto.GetUserTransactionsResponse;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    CreateTransactionResponse createTransaction(CreateTransactionRequest request);

    GetUserTransactionsResponse getUserTransactions(GetUserTransactionsRequest request);
}
