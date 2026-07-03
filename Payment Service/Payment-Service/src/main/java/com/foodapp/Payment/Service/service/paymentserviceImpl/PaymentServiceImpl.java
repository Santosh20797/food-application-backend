package com.foodapp.Payment.Service.service.paymentserviceImpl;


import com.foodapp.Payment.Service.entity.Payment;
import com.foodapp.Payment.Service.repository.PaymentRepository;
import com.foodapp.Payment.Service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment processPayment(Payment payment) {
        payment.setPaymentStatus("SUCCESS"); // Simulating a successful transaction auto-approval
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymentDate(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentByOrderId(Long orderId) {
        return paymentRepository.findAll().stream()
                .filter(p -> p.getOrderId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Payment details missing for Order ID: " + orderId));
    }
}
