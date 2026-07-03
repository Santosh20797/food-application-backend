package com.foodapp.Payment.Service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId; // Loosely coupled ID reference linking back to Order Service
    private Double amount;
    private String paymentStatus; // SUCCESS, FAILED, PENDING
    private String transactionId; // Unique tracking UUID
    private LocalDateTime paymentDate;
}
