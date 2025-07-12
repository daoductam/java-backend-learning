package com.tamdao.order.repository;

import com.tamdao.order.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMongoRepository extends MongoRepository<Payment, String> {
}
