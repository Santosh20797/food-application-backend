package com.foodapp.Delivery.Service.service.serviceImpl;
import com.foodapp.Delivery.Service.entity.Delivery;
import com.foodapp.Delivery.Service.repository.DeliveryRepository;
import com.foodapp.Delivery.Service.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Override
    public Delivery createDelivery(Delivery delivery) {
        delivery.setStatus("PENDING");
        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery getDeliveryByOrderId(String orderId) {
        return deliveryRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery record not found for Order ID: " + orderId));
    }

    @Override
    public Delivery updateDeliveryStatus(String orderId, String status, String courierId) {
        Delivery delivery = getDeliveryByOrderId(orderId);

        if (status != null) delivery.setStatus(status);
        if (courierId != null) delivery.setCourierId(courierId);

        return deliveryRepository.save(delivery);
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
}
