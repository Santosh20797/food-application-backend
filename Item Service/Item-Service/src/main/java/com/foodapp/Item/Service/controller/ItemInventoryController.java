package com.foodapp.Item.Service.controller;
import com.foodapp.Item.Service.entity.ItemInventory;
import com.foodapp.Item.Service.service.ItemInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ItemInventoryController {

    private final ItemInventoryService inventoryService;

    @PostMapping
    public ResponseEntity<ItemInventory> addInventory(@RequestBody ItemInventory inventory) {
        return ResponseEntity.ok(inventoryService.addInventory(inventory));
    }

    @GetMapping
    public ResponseEntity<List<ItemInventory>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping("/menu-item/{menuItemId}")
    public ResponseEntity<ItemInventory> getStockByMenuItem(@PathVariable Long menuItemId) {
        return ResponseEntity.ok(inventoryService.getStockByMenuItem(menuItemId));
    }

    @PutMapping("/menu-item/{menuItemId}/reduce")
    public ResponseEntity<ItemInventory> reduceStock(
            @PathVariable Long menuItemId,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(inventoryService.reduceStock(menuItemId, quantity));
    }
}
