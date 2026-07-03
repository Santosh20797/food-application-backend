package com.foodapp.menu_service.service;


import com.foodapp.menu_service.entity.MenuItem;

import java.util.List;

public interface MenuItemService {
    MenuItem createMenuItem(MenuItem item);
    List<MenuItem> getAllMenuItems();
    MenuItem getMenuItemById(Long id);
    MenuItem updateMenuItem(Long id, MenuItem updatedItem);
    void deleteMenuItem(Long id);
}
