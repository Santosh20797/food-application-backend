package com.foodapp.Order.Service.controller;





import com.foodapp.Order.Service.entity.Order;
import com.foodapp.Order.Service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
//@Tag(name = "Order Management", description = "APIs for processing customer checkouts and tracking states")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
//    @Operation(summary = "Place a new food order")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.placeOrder(order));
    }

    @GetMapping("/{id}")
//    @Operation(summary = "Get detailed order status by ID")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
//    @Operation(summary = "Retrieve all system order histories")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{id}/status")
//    @Operation(summary = "Update order tracking state (e.g. PAID, DELIVERED)")
    public ResponseEntity<Order> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }
}
