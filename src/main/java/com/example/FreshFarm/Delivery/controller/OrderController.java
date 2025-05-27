package com.example.FreshFarm.Delivery.controller;

import com.example.FreshFarm.Delivery.model.dto.order.OrderResponse;
import com.example.FreshFarm.Delivery.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderResponse> customersOrder(@RequestHeader("Authorization") String token) {
        return orderService.customersOrder(token);
    }

    @PostMapping
    public void placeOrder(@RequestHeader("Authorization") String token) {
        orderService.placeOrder(token);
    }
}
