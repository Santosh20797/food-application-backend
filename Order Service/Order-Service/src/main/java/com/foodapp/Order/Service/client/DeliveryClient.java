package com.foodapp.Order.Service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Map;

@FeignClient(name = "delivery-service", url = "http://localhost:8085/api/deliveries")
public interface DeliveryClient {

    @PostMapping
    Object triggerDeliveryFromOrderService(@RequestBody Map<String, Object> deliveryPayload);
}

