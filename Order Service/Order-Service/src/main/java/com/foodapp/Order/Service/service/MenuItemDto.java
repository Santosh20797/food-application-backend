package com.foodapp.Order.Service.service;

import lombok.Data;

@Data
public class MenuItemDto {
    private Long id;
    private String name;
    private Double price;
    private boolean available;
}
