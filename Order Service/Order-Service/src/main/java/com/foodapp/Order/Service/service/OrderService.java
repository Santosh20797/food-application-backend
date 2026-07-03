package com.foodapp.Order.Service.service;



import com.foodapp.Order.Service.entity.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    Order updateOrderStatus(Long id, String status);
}