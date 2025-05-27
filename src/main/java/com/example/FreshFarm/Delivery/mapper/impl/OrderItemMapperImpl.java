package com.example.FreshFarm.Delivery.mapper.impl;

import com.example.FreshFarm.Delivery.mapper.OrderItemMapper;
import com.example.FreshFarm.Delivery.model.domain.Order;
import com.example.FreshFarm.Delivery.model.domain.OrderItem;
import com.example.FreshFarm.Delivery.model.domain.Product;
import com.example.FreshFarm.Delivery.model.dto.orderItem.OrderItemResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemMapperImpl implements OrderItemMapper {
    @Override
    public OrderItem toOrderItem(Product product, Order order, double kilo) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderItem.setKilo(kilo);
        orderItem.setPrice(product.getPrice());
        return orderItem;
    }

    @Override
    public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        OrderItemResponse response = new OrderItemResponse();
        response.setId(orderItem.getId());
        response.setFarmerName(orderItem.getProduct().getFarmer().getUserDetails().getName());
        response.setProductName(orderItem.getProduct().getName());
        response.setImagePath(orderItem.getProduct().getImages().get(0).getPath());
        response.setKilo(orderItem.getKilo());
        response.setPrice(orderItem.getPrice());
        return response;
    }

    @Override
    public List<OrderItemResponse> toOrderItemResponses(List<OrderItem> orderItems) {
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            orderItemResponses.add(toOrderItemResponse(orderItem));
        }
        return orderItemResponses;
    }
}
