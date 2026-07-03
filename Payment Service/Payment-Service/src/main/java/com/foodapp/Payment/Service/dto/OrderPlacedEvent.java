package com.foodapp.Payment.Service.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlacedEvent {
    private String orderId;
    private Long menuItemId;
    private Integer quantity;
    private Double totalAmount;
    private String orderStatus;
}
