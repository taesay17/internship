package com.example.FreshFarm.Delivery.service.impl;

import com.example.FreshFarm.Delivery.exception.CustomException;
import com.example.FreshFarm.Delivery.mapper.OrderItemMapper;
import com.example.FreshFarm.Delivery.model.domain.Basket;
import com.example.FreshFarm.Delivery.model.domain.Order;
import com.example.FreshFarm.Delivery.model.dto.orderItem.OrderItemResponse;
import com.example.FreshFarm.Delivery.repository.OrderItemRepository;
import com.example.FreshFarm.Delivery.repository.OrderRepository;
import com.example.FreshFarm.Delivery.service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public void add(Order order, List<Basket> baskets) {
        for (Basket basket : baskets) {
            orderItemRepository.save(orderItemMapper.toOrderItem(basket.getProduct(), order, basket.getKilo()));
        }
    }

    @Override
    public List<OrderItemResponse> ordersItem(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new CustomException("Order not found", HttpStatus.NOT_FOUND));
        return orderItemMapper.toOrderItemResponses(orderItemRepository.findAllByOrder(order));
    }
}
