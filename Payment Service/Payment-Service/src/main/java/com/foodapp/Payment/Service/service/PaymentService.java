package com.foodapp.Payment.Service.service;


import com.foodapp.Payment.Service.entity.Payment;

public interface PaymentService {
    Payment processPayment(Payment payment);
    Payment getPaymentByOrderId(Long orderId);
}
