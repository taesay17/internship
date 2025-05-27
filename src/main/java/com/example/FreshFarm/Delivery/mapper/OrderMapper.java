package com.example.FreshFarm.Delivery.mapper;

import com.example.FreshFarm.Delivery.model.domain.Basket;
import com.example.FreshFarm.Delivery.model.domain.Order;
import com.example.FreshFarm.Delivery.model.domain.User;
import com.example.FreshFarm.Delivery.model.dto.order.OrderResponse;

import java.util.List;

public interface OrderMapper {
    Order toOrder(double totalPrice, User customer);
    OrderResponse toResponse(Order order);
    List<OrderResponse> toResponses(List<Order> orders);
}
