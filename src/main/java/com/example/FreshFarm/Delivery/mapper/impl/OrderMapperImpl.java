package com.example.FreshFarm.Delivery.mapper.impl;

import com.example.FreshFarm.Delivery.mapper.OrderMapper;
import com.example.FreshFarm.Delivery.model.domain.Order;
import com.example.FreshFarm.Delivery.model.domain.User;
import com.example.FreshFarm.Delivery.model.dto.order.OrderResponse;
import com.example.FreshFarm.Delivery.model.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toOrder(double totalPrice, User customer) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.PENDING);
        order.setCustomer(customer);
        return order;
    }

    @Override
    public OrderResponse toResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getId());
        orderResponse.setCustomerId(order.getCustomer().getId());
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setOrderStatus(String.valueOf(order.getStatus()));
        orderResponse.setTotalAmount(order.getTotalPrice());
        return orderResponse;
    }

    @Override
    public List<OrderResponse> toResponses(List<Order> orders) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            orderResponses.add(toResponse(order));
        }
        return orderResponses;
    }
}
