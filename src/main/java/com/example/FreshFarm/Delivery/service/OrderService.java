package com.example.FreshFarm.Delivery.service;

import com.example.FreshFarm.Delivery.model.dto.order.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> customersOrder(String token);
    void placeOrder(String token);
}
