package com.tamdao.payment.service;

import org.springframework.stereotype.Service;

@Service
public interface DistributedLockService {
    boolean acquireLock(String idempotentKey);

    void releaseLock(String idempotentKey);
}
