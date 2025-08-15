package com.tamdao.orders_tracking.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class OrderWaiterRegistry {
    private final ConcurrentMap<String, CopyOnWriteArrayList<DeferredResult<ResponseEntity<?>>>> waiters = new ConcurrentHashMap<>();

    public DeferredResult<ResponseEntity<?>> register(String orderId, long timeoutMillis) {
        var result = new DeferredResult<ResponseEntity<?>>(timeoutMillis);
        waiters.computeIfAbsent(orderId, k -> new CopyOnWriteArrayList<>())
                .add(result);
        result.onCompletion(() -> waiters.remove(orderId, result));
        result.onTimeout(() -> result.setResult(ResponseEntity.noContent().build()));
        return result;
    }

    public void notifyChange(String orderId, Object payload) {
        var list = waiters.get(orderId);
        if (list == null) {
            return;
        }
        for (var deferred : list) {
            deferred.setResult(ResponseEntity.ok(payload));
        }
        list.clear();
    }

    public void remove(String orderId, DeferredResult<ResponseEntity<?>> deferredResult) {
        var list = waiters.get(orderId);
        if (list != null) {
            list.remove(deferredResult);
            if (list.isEmpty()) waiters.remove(orderId);
        }
    }
}
