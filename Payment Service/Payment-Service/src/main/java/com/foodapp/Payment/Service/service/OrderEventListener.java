package com.foodapp.Payment.Service.service;

import com.foodapp.Payment.Service.dto.OrderPlacedEvent;
import com.foodapp.Payment.Service.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class OrderEventListener {

    @Autowired
    private PaymentService paymentService; // Inject your core business service layer

    @KafkaListener(
            topics = "order-events",
            groupId = "payment-group",
            properties = {"spring.json.value.default.type=com.foodapp.Payment.Service.dto.OrderPlacedEvent"}
    )
    public void handleOrderPlaced(OrderPlacedEvent event) {
        System.out.println("\n💳 Kafka consumer received event for Order ID: " + event.getOrderId());

        // 1. Map incoming DTO to your un-saved Entity wrapper
        Payment paymentData = Payment.builder()
                .orderId(Long.valueOf(event.getOrderId())) // Converts String ID to Long
                .amount(event.getTotalAmount())
                .build();

        // 2. Delegate directly to your service impl layer to attach UUID, set status, and save to Postgres
        Payment processedPayment = paymentService.processPayment(paymentData);

        System.out.println("✅ Payment successfully recorded in DB!");
        System.out.println("Transaction tracking code: " + processedPayment.getTransactionId() + "\n");
    }

}
