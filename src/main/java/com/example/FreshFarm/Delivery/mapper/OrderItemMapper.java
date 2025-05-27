package com.example.FreshFarm.Delivery.mapper;

import com.example.FreshFarm.Delivery.model.domain.Order;
import com.example.FreshFarm.Delivery.model.domain.OrderItem;
import com.example.FreshFarm.Delivery.model.domain.Product;
import com.example.FreshFarm.Delivery.model.dto.orderItem.OrderItemResponse;

import java.util.List;

public interface OrderItemMapper {
    OrderItem toOrderItem(Product product, Order order, double kilo);
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
    List<OrderItemResponse> toOrderItemResponses(List<OrderItem> orderItems);
}
