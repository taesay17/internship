package com.example.FreshFarm.Delivery.controller;

import com.example.FreshFarm.Delivery.model.dto.orderItem.OrderItemResponse;
import com.example.FreshFarm.Delivery.service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order_items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemResponse> ordersItem(Long orderId) {
        return orderItemService.ordersItem(orderId);
    }
}
