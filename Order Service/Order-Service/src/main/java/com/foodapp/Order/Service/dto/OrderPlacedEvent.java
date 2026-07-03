package com.foodapp.Order.Service.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlacedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;
    private Long menuItemId;
    private Integer quantity;
    private Double totalAmount;
    private String orderStatus;
}
