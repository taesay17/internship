package com.example.FreshFarm.Delivery.model.dto.product;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private List<String> imagePath;
    private String description;
    private double price;
    private int stock;
    private LocalDateTime createdAt;
}
