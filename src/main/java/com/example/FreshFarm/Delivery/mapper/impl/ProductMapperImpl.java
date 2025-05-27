package com.example.FreshFarm.Delivery.mapper.impl;

import com.example.FreshFarm.Delivery.mapper.ProductMapper;
import com.example.FreshFarm.Delivery.model.domain.Farmer;
import com.example.FreshFarm.Delivery.model.domain.Image;
import com.example.FreshFarm.Delivery.model.domain.Product;
import com.example.FreshFarm.Delivery.model.dto.product.DiscountedProductResponse;
import com.example.FreshFarm.Delivery.model.dto.product.ProductRequest;
import com.example.FreshFarm.Delivery.model.dto.product.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public Product toProduct(Product product, Farmer farmer, ProductRequest request) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setFarmer(farmer);
        product.setCreatedAt(LocalDateTime.now());
        return product;
    }

    @Override
    public ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());

        List<String> imagesPath = new ArrayList<>();
        for (Image image : product.getImages()) {
            imagesPath.add(image.getPath());
        }
        response.setImagePath(imagesPath);

        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setCreatedAt(product.getCreatedAt());
        return response;
    }

    @Override
    public List<ProductResponse> toResponseList(List<Product> products) {
        List<ProductResponse> responseList = new ArrayList<>();
        for (Product product : products) {
            responseList.add(toResponse(product));
        }
        return responseList;
    }

    @Override
    public DiscountedProductResponse toDiscountResponse(Product product) {
        DiscountedProductResponse response = new DiscountedProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());

        List<String> imagesPath = new ArrayList<>();
        for (Image image : product.getImages()) {
            imagesPath.add(image.getPath());
        }
        response.setImagePath(imagesPath);

        double price = product.getPrice();
        response.setDescription(product.getDescription());
        response.setPrice(price);
        response.setStock(product.getStock());
        response.setCreatedAt(product.getCreatedAt());
        int discount = product.getDiscount();
        response.setDiscount(discount);
        response.setDiscountPrice(price - (price * discount / 100));
        log.atInfo().log("Discount response: {}", response.getDiscount());
        return response;
    }

    @Override
    public List<DiscountedProductResponse> toDiscountedResponseList(List<Product> products) {
        List<DiscountedProductResponse> responseList = new ArrayList<>();
        for (Product product : products) {
            responseList.add(toDiscountResponse(product));
        }
        return responseList;
    }
}
