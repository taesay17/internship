package com.example.FreshFarm.Delivery.service;

import com.example.FreshFarm.Delivery.model.dto.basket.BasketProductsPrice;
import com.example.FreshFarm.Delivery.model.dto.basket.BasketResponse;

import java.util.List;

public interface BasketService {
    void add(String token, Long productId, Double kilo);
    List<BasketResponse> getCustomerBasket(String token);
    BasketProductsPrice getPrice(String token);
    BasketProductsPrice updateKilo(String token, Long basketId, Double kilo);
    List<BasketResponse> removeProduct(String token, Long basketId);
    void removeAllProducts(String token);
}
