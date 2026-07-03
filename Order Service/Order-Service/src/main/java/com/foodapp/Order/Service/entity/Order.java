package com.foodapp.Order.Service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long menuItemId; // Loosely coupled ID reference to the Menu Microservice
    private Integer quantity;
    private Double totalAmount;
    private String orderStatus; // PLACED, PAID, PREPARING, DELIVERED
    private LocalDateTime createdAt;
    private String customerAddress;


}
