package com.example.FreshFarm.Delivery.model.dto.basket;

import lombok.Data;

@Data
public class BasketResponse {
    private Long basketId;
    private String name;
    private String imagePath;
    private Double price;
    private Double kilo;
    private Double totalPrice;
}
