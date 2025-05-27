package com.example.FreshFarm.Delivery.controller;

import com.example.FreshFarm.Delivery.model.dto.basket.BasketProductsPrice;
import com.example.FreshFarm.Delivery.model.dto.basket.BasketResponse;
import com.example.FreshFarm.Delivery.service.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/basket")
public class BasketController {
    private final BasketService basketService;

    @PostMapping("/{productId}")
    public void add(
            @RequestHeader("Authorization") String token,
            @PathVariable Long productId,
            @RequestParam(defaultValue = "1") Double kilo
    ) {
        basketService.add(token, productId, kilo);
    }

    @GetMapping
    public List<BasketResponse> getCustomerBasket(@RequestHeader("Authorization") String token) {
        return basketService.getCustomerBasket(token);
    }

    @GetMapping("/price")
    public BasketProductsPrice getPrice(@RequestHeader("Authorization") String token) {
        return basketService.getPrice(token);
    }

    @PutMapping("/{basketId}")
    public BasketProductsPrice updateKilo(
            @RequestHeader("Authorization") String token,
            @PathVariable Long basketId,
            @RequestParam Double kilo
    ) {
        return basketService.updateKilo(token, basketId, kilo);
    }

    @DeleteMapping("/{basketId}")
    public List<BasketResponse> removeProduct(
            @RequestHeader("Authorization") String token,
            @PathVariable Long basketId
    ) {
        return basketService.removeProduct(token, basketId);
    }

    @DeleteMapping
    public void removeAllProducts(@RequestHeader("Authorization") String token) {
        basketService.removeAllProducts(token);
    }
}
