package com.foodapp.Item.Service.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long menuItemId; // Loosely coupled reference linking back to Menu Service ID
    private String category; // Veg, Non-Veg, Extra Topping, etc.
    private Integer stockQuantity; // How many portions are left in the kitchen today
}
