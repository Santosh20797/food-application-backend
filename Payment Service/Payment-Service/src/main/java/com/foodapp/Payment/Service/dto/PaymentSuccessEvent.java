package com.foodapp.Payment.Service.dto;


import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSuccessEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderId;
    private String transactionId;
    private String status;
}
