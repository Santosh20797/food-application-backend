package com.foodapp.Order.Service.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Target the address location of your active Item-Service (Running on 8084)
@FeignClient(name = "item-service", url = "http://localhost:8084")
public interface ItemClient {

    // Matches the exact endpoint structure inside ItemInventoryController
    @PutMapping("/api/inventory/menu-item/{menuItemId}/reduce")
    Object reduceStockFromOrderService(
            @PathVariable("menuItemId") Long menuItemId,
            @RequestParam("quantity") Integer quantity
    );
}
