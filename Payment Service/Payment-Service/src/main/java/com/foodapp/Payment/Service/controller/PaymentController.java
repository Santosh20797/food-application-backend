package com.foodapp.Payment.Service.controller;


import com.foodapp.Payment.Service.entity.Payment;
import com.foodapp.Payment.Service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
//@Tag(name = "Payment Management", description = "APIs for processing transaction states")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
//    @Operation(summary = "Process transaction for an order")
    public ResponseEntity<Payment> pay(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.processPayment(payment));
    }

    @GetMapping("/order/{orderId}")
//    @Operation(summary = "Get transaction details using Order ID")
    public ResponseEntity<Payment> getByOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(paymentService.getPaymentByOrderId(orderId));
    }
}
