package com.kart.Order.MicroService.Service;


import com.kart.Order.MicroService.model.OrderRequest;
import com.kart.Order.MicroService.model.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
