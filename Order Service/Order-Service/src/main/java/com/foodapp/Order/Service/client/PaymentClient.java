package com.foodapp.Order.Service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Define the name and the absolute URL address location of the target Payment-Service
@FeignClient(name = "payment-service", url = "http://localhost:8082")
public interface PaymentClient {

    // This signature matches the exact endpoint structure inside PaymentController
    @PostMapping("/api/payments")
    Object processPaymentFromOrderService(@RequestBody Object paymentPayload);
}

