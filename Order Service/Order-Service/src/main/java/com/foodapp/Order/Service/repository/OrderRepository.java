package com.foodapp.Order.Service.repository;

import com.foodapp.Order.Service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
