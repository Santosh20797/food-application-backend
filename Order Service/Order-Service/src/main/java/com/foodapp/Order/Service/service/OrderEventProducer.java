package com.foodapp.Order.Service.service;

import com.foodapp.Order.Service.dto.OrderPlacedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishOrderPlaced(OrderPlacedEvent event) {
        // "order-events" is the topic name
        kafkaTemplate.send("order-events", event);
    }
}
