package com.example.FreshFarm.Delivery.mapper.impl;

import com.example.FreshFarm.Delivery.mapper.BasketMapper;
import com.example.FreshFarm.Delivery.model.domain.Basket;
import com.example.FreshFarm.Delivery.model.domain.Image;
import com.example.FreshFarm.Delivery.model.domain.Product;
import com.example.FreshFarm.Delivery.model.domain.User;
import com.example.FreshFarm.Delivery.model.dto.basket.BasketResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class BasketMapperImpl implements BasketMapper {

    @Override
    public Basket toBasket(User user, Product product, Double kilo) {
        Basket basket = new Basket();
        basket.setUser(user);
        basket.setKilo(kilo);
        basket.setProduct(product);
        basket.setCreatedAt(LocalDateTime.now());
        return basket;
    }

    @Override
    public BasketResponse toResponse(Basket basket) {
        BasketResponse response = new BasketResponse();
        response.setBasketId(basket.getId());
        response.setName(basket.getProduct().getName());
        response.setImagePath(basket.getProduct().getImages().get(0).getPath());
        response.setPrice(basket.getProduct().getPrice());
        response.setKilo(basket.getKilo());
        response.setTotalPrice(basket.getKilo() * basket.getProduct().getPrice());
        return response;
    }

    @Override
    public List<BasketResponse> toResponses(List<Basket> baskets) {
        List<BasketResponse> responses = new ArrayList<>();
        for (Basket basket : baskets) {
            responses.add(toResponse(basket));
        }
        return responses;
    }
}
