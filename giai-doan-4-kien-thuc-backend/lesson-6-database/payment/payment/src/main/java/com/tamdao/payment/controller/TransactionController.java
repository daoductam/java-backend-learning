package com.tamdao.payment.controller;

import com.tamdao.payment.dto.*;
import com.tamdao.payment.service.TransactionService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Timed(histogram = true)
@RequestMapping("/transactions")
public class TransactionController {
    private final Counter myCounter;
    private final TransactionService transactionService;

    public TransactionController(MeterRegistry registry, TransactionService transactionService) {
        this.myCounter = Counter.builder("my_custom_counter")
                .description("A custom counter metric")
                .register(registry);
        this.transactionService = transactionService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse<GetUserTransactionsResponse>> getUserTransactions(@PathVariable Integer userId) {
        myCounter.increment();

        final GetUserTransactionsResponse response = transactionService
                .getUserTransactions(GetUserTransactionsRequest.builder().userId(userId).build());

        return ResponseEntity.status(HttpStatus.OK).body(
                BaseResponse.<GetUserTransactionsResponse>builder()
                        .message("Lay transaction thanh cong").
                data(response).build()
        );
    }

    @PostMapping
    public ResponseEntity<BaseResponse<CreateTransactionResponse>> createTransaction(
            @Valid @RequestBody CreateTransactionRequest registrationRequest
            ) {
        final CreateTransactionResponse response = transactionService.createTransaction(registrationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                BaseResponse.<CreateTransactionResponse>builder()
                        .message("Giao dich than cong")
                        .data(response).build()
        );
    }

}
