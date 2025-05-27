package com.example.FreshFarm.Delivery.mapper;

import com.example.FreshFarm.Delivery.model.domain.Basket;
import com.example.FreshFarm.Delivery.model.domain.Product;
import com.example.FreshFarm.Delivery.model.domain.User;
import com.example.FreshFarm.Delivery.model.dto.basket.BasketResponse;

import java.util.List;

public interface BasketMapper {
    Basket toBasket(User user, Product product, Double kilo);
    BasketResponse toResponse(Basket basket);
    List<BasketResponse> toResponses(List<Basket> baskets);
}
