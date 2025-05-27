package com.example.FreshFarm.Delivery.service;

import com.example.FreshFarm.Delivery.model.domain.Basket;
import com.example.FreshFarm.Delivery.model.domain.Order;
import com.example.FreshFarm.Delivery.model.dto.orderItem.OrderItemResponse;

import java.util.List;

public interface OrderItemService {
    void add(Order order, List<Basket> baskets);
    List<OrderItemResponse> ordersItem(Long orderId);
}
