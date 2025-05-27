package com.example.FreshFarm.Delivery.model.dto.order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private Long orderId;
    private Long customerId;
    private LocalDateTime orderDate;
    private String orderStatus;
    private double totalAmount;
}
