package com.foodapp.Item.Service.service.serviceImpl;

import com.foodapp.Item.Service.entity.ItemInventory;
import com.foodapp.Item.Service.repository.ItemInventoryRepository;
import com.foodapp.Item.Service.service.ItemInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemInventoryServiceImpl implements ItemInventoryService {

    private final ItemInventoryRepository repository;

    @Override
    public ItemInventory addInventory(ItemInventory inventory) {
        return repository.save(inventory);
    }

    @Override
    public List<ItemInventory> getAllInventory() {
        return repository.findAll();
    }

    @Override
    public ItemInventory getStockByMenuItem(Long menuItemId) {
        return repository.findByMenuItemId(menuItemId)
                .orElseThrow(() -> new RuntimeException("Inventory record missing for Menu Item ID: " + menuItemId));
    }

    @Override
    public ItemInventory reduceStock(Long menuItemId, Integer quantity) {
        ItemInventory inventory = getStockByMenuItem(menuItemId);

        if (inventory.getStockQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock in the kitchen for Menu Item ID: " + menuItemId);
        }

        inventory.setStockQuantity(inventory.getStockQuantity() - quantity);
        return repository.save(inventory);
    }
}
