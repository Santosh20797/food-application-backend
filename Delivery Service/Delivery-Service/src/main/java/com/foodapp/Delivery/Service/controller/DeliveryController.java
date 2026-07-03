package com.foodapp.Delivery.Service.controller;
import com.foodapp.Delivery.Service.entity.Delivery;
import com.foodapp.Delivery.Service.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@EnableFeignClients
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
//@Tag(name = "Delivery Management", description = "Endpoints for managing logistics and delivery tracking")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
//    @Operation(summary = "Initialize a new delivery pipeline tracking profile")
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery) {
        return new ResponseEntity<>(deliveryService.createDelivery(delivery), HttpStatus.CREATED);
    }

    @GetMapping("/order/{orderId}")
//    @Operation(summary = "Fetch the complete tracking status profile using Order ID")
    public ResponseEntity<Delivery> getDeliveryByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(deliveryService.getDeliveryByOrderId(orderId));
    }

    @PutMapping("/order/{orderId}")
//    @Operation(summary = "Update progress phase or assign a driver/courier ID")
    public ResponseEntity<Delivery> updateStatus(
            @PathVariable String orderId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String courierId) {
        return ResponseEntity.ok(deliveryService.updateDeliveryStatus(orderId, status, courierId));
    }

    @GetMapping
//    @Operation(summary = "Retrieve all delivery manifests across the system")
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        return ResponseEntity.ok(deliveryService.getAllDeliveries());
    }
}
