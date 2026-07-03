package com.foodapp.Order.Service.service.serviceImpl;

import com.foodapp.Order.Service.client.MenuClient;
import com.foodapp.Order.Service.client.PaymentClient;
import com.foodapp.Order.Service.client.ItemClient;
import com.foodapp.Order.Service.client.DeliveryClient;
import com.foodapp.Order.Service.dto.OrderPlacedEvent; // 🚀 Import your DTO
import com.foodapp.Order.Service.entity.Order;
import com.foodapp.Order.Service.repository.OrderRepository;
import com.foodapp.Order.Service.service.MenuItemDto;
import com.foodapp.Order.Service.service.OrderService;
import com.foodapp.Order.Service.service.OrderEventProducer; // 🚀 Import your Kafka Producer
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;
    private final MenuClient menuClient;
    private final ItemClient itemClient;
    private final DeliveryClient deliveryClient;
    private final OrderEventProducer orderEventProducer; // 🚀 Injected automatically by Lombok!

    @Override
    public Order placeOrder(Order order) {
        order.setOrderStatus("PENDING_PAYMENT");
        order.setCreatedAt(LocalDateTime.now());

        // Step A: Fetch live item data from your Menu Service over OpenFeign
        MenuItemDto itemDetails = menuClient.getMenuItemByIdFromOrderService(order.getMenuItemId());

        if (!itemDetails.isAvailable()) {
            throw new RuntimeException("Sorry! This food item is currently sold out.");
        }

        // Step B: Automatically calculate total amount using the true database price
        order.setTotalAmount(order.getQuantity() * itemDetails.getPrice());

        // Step C: Atomically check and reduce kitchen stock via Item-Service over OpenFeign
        itemClient.reduceStockFromOrderService(order.getMenuItemId(), order.getQuantity());

        // Save order footprint to PostgreSQL database
        Order savedOrder = orderRepository.save(order);

        // 🚀 Step D: Drop an event payload into Kafka asynchronously!
        // This alerts your payment-service and delivery-service instantly via your message broker.
        try {
            OrderPlacedEvent orderEvent = OrderPlacedEvent.builder()
                    .orderId(String.valueOf(savedOrder.getId()))
                    .menuItemId(savedOrder.getMenuItemId())
                    .quantity(savedOrder.getQuantity())
                    .totalAmount(savedOrder.getTotalAmount())
                    .orderStatus(savedOrder.getOrderStatus())
                    .build();

            orderEventProducer.publishOrderPlaced(orderEvent);
            System.out.println("🚀 Asynchronous Order event pushed to Kafka broker channel.");
        } catch (Exception kafkaException) {
            System.err.println("Kafka Broker broadcast failure: " + kafkaException.getMessage());
        }

        // --- Keep your fallback legacy REST blocks intact for stability ---
        try {
            Map<String, Object> paymentPayload = new HashMap<>();
            paymentPayload.put("orderId", savedOrder.getId());
            paymentPayload.put("amount", savedOrder.getTotalAmount());

            paymentClient.processPaymentFromOrderService(paymentPayload);

            savedOrder.setOrderStatus("PAID");
            Order fullyPaidOrder = orderRepository.save(savedOrder);

            try {
                Map<String, Object> deliveryPayload = new HashMap<>();
                deliveryPayload.put("orderId", String.valueOf(fullyPaidOrder.getId()));
                deliveryPayload.put("customerAddress", fullyPaidOrder.getCustomerAddress());

                deliveryClient.triggerDeliveryFromOrderService(deliveryPayload);
            } catch (Exception deliveryException) {
                System.err.println("Delivery Service connection failure: " + deliveryException.getMessage());
            }

        } catch (Exception e) {
            savedOrder.setOrderStatus("PAYMENT_FAILED_RETRY");
            orderRepository.save(savedOrder);
        }

        return savedOrder;
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrderStatus(Long id, String status) {
        Order existingOrder = getOrderById(id);
        existingOrder.setOrderStatus(status);
        return orderRepository.save(existingOrder);
    }
}
