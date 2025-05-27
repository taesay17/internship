package com.example.FreshFarm.Delivery.model.dto.product;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private double price;
    private int stock;
}
