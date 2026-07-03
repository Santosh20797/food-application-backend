package com.foodapp.menu_service.service.serviceImpl;

import com.foodapp.menu_service.entity.MenuItem;
import com.foodapp.menu_service.repository.MenuItemRepository;
import com.foodapp.menu_service.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;

    @Override
    public MenuItem createMenuItem(MenuItem item) {
        item.setAvailable(true);
        return menuItemRepository.save(item);
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item missing with ID: " + id));
    }

    @Override
    public MenuItem updateMenuItem(Long id, MenuItem updatedItem) {
        MenuItem existingItem = getMenuItemById(id);
        existingItem.setName(updatedItem.getName());
        existingItem.setDescription(updatedItem.getDescription());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setAvailable(updatedItem.isAvailable());
        return menuItemRepository.save(existingItem);
    }

    @Override
    public void deleteMenuItem(Long id) {
        MenuItem existingItem = getMenuItemById(id);
        menuItemRepository.delete(existingItem);
    }
}
