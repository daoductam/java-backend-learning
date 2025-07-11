package com.tamdao.payment.repository;

import com.tamdao.payment.entity.Transaction;
import io.netty.util.internal.ObjectPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByUserId(int userId);

    Optional<Transaction> findOneByIdempotentKey(String idempotentKey);
}
