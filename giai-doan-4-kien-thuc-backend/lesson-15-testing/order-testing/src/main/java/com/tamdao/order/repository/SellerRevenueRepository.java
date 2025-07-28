package com.tamdao.order.repository;

import com.tamdao.order.entity.SellerRevenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRevenueRepository extends JpaRepository<SellerRevenue, Long> {
}
