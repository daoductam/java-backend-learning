package com.tamdao.orders_tracking.repository;

import com.tamdao.orders_tracking.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
