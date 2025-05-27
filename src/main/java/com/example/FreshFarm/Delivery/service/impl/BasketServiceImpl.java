package com.example.FreshFarm.Delivery.service.impl;

import com.example.FreshFarm.Delivery.config.JwtService;
import com.example.FreshFarm.Delivery.exception.CustomException;
import com.example.FreshFarm.Delivery.mapper.BasketMapper;
import com.example.FreshFarm.Delivery.model.domain.Basket;
import com.example.FreshFarm.Delivery.model.domain.Product;
import com.example.FreshFarm.Delivery.model.domain.User;
import com.example.FreshFarm.Delivery.model.dto.basket.BasketProductsPrice;
import com.example.FreshFarm.Delivery.model.dto.basket.BasketResponse;
import com.example.FreshFarm.Delivery.repository.BasketRepository;
import com.example.FreshFarm.Delivery.repository.ProductRepository;
import com.example.FreshFarm.Delivery.service.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final BasketMapper basketMapper;
    private final JwtService jwtService;

    @Override
    public void add(String token, Long productId, Double kilo) {
        User user = jwtService.getUserFromToken(token);
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));
        if (basketRepository.existsByUserAndProduct(user, product)) {
            throw new CustomException("Product already exists", HttpStatus.CONFLICT);
        }
        basketRepository.save(basketMapper.toBasket(user, product, kilo));
    }

    @Override
    public List<BasketResponse> getCustomerBasket(String token) {
        User user = jwtService.getUserFromToken(token);
        return basketMapper.toResponses(basketRepository.findAllByUser(user));
    }

    @Override
    public BasketProductsPrice getPrice(String token) {
        User user = jwtService.getUserFromToken(token);
        BasketProductsPrice basketProductsPrice = new BasketProductsPrice();
        basketProductsPrice.setTotalPrice(generateTotalPrice(basketRepository.findAllByUser(user)));
        return basketProductsPrice;
    }

    @Override
    public BasketProductsPrice updateKilo(String token, Long basketId, Double kilo) {
        User user = jwtService.getUserFromToken(token);
        Basket basket = basketRepository.findById(basketId).orElseThrow(() -> new CustomException("Basket not found", HttpStatus.NOT_FOUND));
        if (basket.getUser().equals(user)) {
            basket.setKilo(kilo);
            basket.setUpdatedAt(LocalDateTime.now());
            basketRepository.save(basket);
        } else {
            throw new CustomException("Basket does not belong to user", HttpStatus.CONFLICT);
        }
        return getPrice(token);
    }

    @Override
    public List<BasketResponse> removeProduct(String token, Long basketId) {
        User user = jwtService.getUserFromToken(token);
        Basket basket = basketRepository.findById(basketId).orElseThrow(() -> new CustomException("Basket not found", HttpStatus.NOT_FOUND));
        if (basket.getUser().equals(user)) {
            basketRepository.delete(basket);
        } else {
            throw new CustomException("Basket does not belong to user", HttpStatus.CONFLICT);
        }
        return getCustomerBasket(token);
    }

    @Override
    public void removeAllProducts(String token) {
        User user = jwtService.getUserFromToken(token);
        basketRepository.deleteAllByUser(user);
    }

    private Double generateTotalPrice(List<Basket> baskets) {
        double totalPrice = 0.0;
        for (Basket basket : baskets) {
            totalPrice += basket.getKilo() * basket.getProduct().getPrice();
        }
        return totalPrice;
    }
}
