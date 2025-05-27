package com.example.FreshFarm.Delivery.model.dto.orderItem;

import lombok.Data;

@Data
public class OrderItemResponse {
    private Long id;
    private String farmerName;
    private String productName;
    private String imagePath;
    private double kilo;
    private double price;
}
