package com.foodapp.Payment.Service.repository;




import com.foodapp.Payment.Service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Let Postgres do the filtering instantly at the database level!
    Optional<Payment> findByOrderId(Long orderId);
}
