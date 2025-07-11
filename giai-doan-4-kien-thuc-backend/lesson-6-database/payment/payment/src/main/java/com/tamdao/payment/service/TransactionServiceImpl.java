package com.tamdao.payment.service;

import com.tamdao.payment.dto.CreateTransactionRequest;
import com.tamdao.payment.dto.CreateTransactionResponse;
import com.tamdao.payment.dto.GetUserTransactionsRequest;
import com.tamdao.payment.dto.GetUserTransactionsResponse;
import com.tamdao.payment.entity.Transaction;
import com.tamdao.payment.entity.User;
import com.tamdao.payment.exception.AppException;
import com.tamdao.payment.exception.ErrorCode;
import com.tamdao.payment.repository.TransactionRepository;
import com.tamdao.payment.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final DistributedLockService distributedLockService;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public CreateTransactionResponse createTransaction(CreateTransactionRequest request) {
        CreateTransactionResponse createTransactionResponse;

        if (distributedLockService.acquireLock(request.getIdempotentKey())) {
            try {
                TimeUnit.SECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("accquired lock successfully");

            try {
                Optional<Transaction> optionalExistedTransaction = transactionRepository
                        .findOneByIdempotentKey(request.getIdempotentKey());

                log.info("optionalExistedTransactional={}", optionalExistedTransaction);

                if (optionalExistedTransaction.isPresent()) {

                    Transaction existedTransaction = optionalExistedTransaction.get();
                    if (existedTransaction.getUserId() == request.getUserId()
                            && existedTransaction.getAmount() == request.getAmount()) {
                        createTransactionResponse = CreateTransactionResponse.builder().transactionId(existedTransaction.getId())
                                .remainBalance(existedTransaction.getBalanceAfterTransaction()).build();
                    } else {
                        throw new AppException(ErrorCode.DuplicatedIdempotentKeyException);
                    }

                }else  {
                    final User user = userRepository.findOneWithLockingById(request.getUserId())
                            .orElseThrow(() -> new AppException(ErrorCode.UserNotFoundException));
                    if (user.getBalance() < request.getAmount()) {
                        throw new AppException(ErrorCode.NotEnoughBalanceException);
                    }

                    int balanceBeforeTransaction= user.getBalance();
                    int balanceAfterTransaction = user.getBalance() - request.getAmount();
                    user.setBalance(balanceAfterTransaction);
                    userRepository.save(user);
                    Transaction transaction = transactionRepository.save(Transaction.builder()
                            .idempotentKey(request.getIdempotentKey())
                            .userId(request.getUserId())
                            .amount(request.getAmount())
                            .balanceBeforeTransaction(balanceBeforeTransaction)
                            .balanceAfterTransaction(balanceAfterTransaction)
                            .build());

                    createTransactionResponse = CreateTransactionResponse.builder().transactionId(transaction.getId())
                            .remainBalance(balanceAfterTransaction)
                            .build();
                }
            } finally {
                distributedLockService.releaseLock(request.getIdempotentKey());
                log.info("release log successfully");
            }
        } else  {
            log.info("Cannot required lock");
            throw new AppException(ErrorCode.TooManyRequestException);
        }

        return createTransactionResponse;
    }

    @Override
    public GetUserTransactionsResponse getUserTransactions(GetUserTransactionsRequest request) {
        return GetUserTransactionsResponse.builder().transactions(transactionRepository.findByUserId(request.getUserId()))
                .build();
    }
}

