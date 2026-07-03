package com.foodapp.Payment.Service.service;

import com.foodapp.Payment.Service.dto.PaymentSuccessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class PaymentEventProducer {

    // Changing Object to PaymentSuccessEvent or keeping it generic is fine,
    // but the library must be imported via Maven first!
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishPaymentSuccess(PaymentSuccessEvent event) {
        System.out.println("Sending event out...");
        kafkaTemplate.send("payment-events", event);
    }
}
