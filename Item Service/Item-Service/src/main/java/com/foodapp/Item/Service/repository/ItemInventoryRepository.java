package com.foodapp.Item.Service.repository;

import com.foodapp.Item.Service.entity.ItemInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ItemInventoryRepository extends JpaRepository<ItemInventory, Long> {
    // Custom query to find the inventory record by the matching Menu Item ID
    Optional<ItemInventory> findByMenuItemId(Long menuItemId);
}
