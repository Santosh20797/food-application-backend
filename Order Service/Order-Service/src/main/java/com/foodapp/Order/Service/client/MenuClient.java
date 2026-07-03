package com.foodapp.Order.Service.client;

import com.foodapp.Order.Service.service.MenuItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Target the address location of your active Menu-Service (Running on 8083 in your setup)
@FeignClient(name = "menu-service", url = "http://localhost:8083")
public interface MenuClient {

    @GetMapping("/api/menu/{id}")
    MenuItemDto getMenuItemByIdFromOrderService(@PathVariable("id") Long id);
}
