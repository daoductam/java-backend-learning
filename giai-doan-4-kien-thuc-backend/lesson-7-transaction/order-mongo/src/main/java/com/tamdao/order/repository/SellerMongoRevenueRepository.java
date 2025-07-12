package com.tamdao.order.repository;

import com.tamdao.order.entity.SellerRevenue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerMongoRevenueRepository extends MongoRepository<SellerRevenue, String> {
}
