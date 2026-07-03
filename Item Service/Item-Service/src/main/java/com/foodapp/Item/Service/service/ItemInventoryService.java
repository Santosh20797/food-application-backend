package com.foodapp.Item.Service.service;


import com.foodapp.Item.Service.entity.ItemInventory;
import java.util.List;

public interface ItemInventoryService {
    ItemInventory addInventory(ItemInventory inventory);
    List<ItemInventory> getAllInventory();
    ItemInventory getStockByMenuItem(Long menuItemId);
    ItemInventory reduceStock(Long menuItemId, Integer quantity);
}
