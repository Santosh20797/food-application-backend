package com.foodapp.Delivery.Service.service;
import com.foodapp.Delivery.Service.entity.Delivery;
import java.util.List;

public interface DeliveryService {
    Delivery createDelivery(Delivery delivery);
    Delivery getDeliveryByOrderId(String orderId);
    Delivery updateDeliveryStatus(String orderId, String status, String courierId);
    List<Delivery> getAllDeliveries();
}
