package com.foodapp.menu_service.controller;





import com.foodapp.menu_service.entity.MenuItem;
import com.foodapp.menu_service.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemRepository menuItemRepository;

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem item) {
        item.setAvailable(true);
        return ResponseEntity.ok(menuItemRepository.save(item));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        return menuItemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem updatedItem) {
        return menuItemRepository.findById(id)
                .map(existingItem -> {
                    existingItem.setName(updatedItem.getName());
                    existingItem.setDescription(updatedItem.getDescription());
                    existingItem.setPrice(updatedItem.getPrice());
                    existingItem.setAvailable(updatedItem.isAvailable());
                    return ResponseEntity.ok(menuItemRepository.save(existingItem));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable Long id) {
        return menuItemRepository.findById(id)
                .map(item -> {
                    menuItemRepository.delete(item);
                    return ResponseEntity.ok("Menu item with ID " + id + " was deleted successfully.");
                })
                .orElse(ResponseEntity.notFound().build());
    }

}

