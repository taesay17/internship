package com.example.FreshFarm.Delivery.mapper;

import com.example.FreshFarm.Delivery.model.domain.Farmer;
import com.example.FreshFarm.Delivery.model.domain.Product;
import com.example.FreshFarm.Delivery.model.dto.product.DiscountedProductResponse;
import com.example.FreshFarm.Delivery.model.dto.product.ProductRequest;
import com.example.FreshFarm.Delivery.model.dto.product.ProductResponse;

import java.util.List;

public interface ProductMapper {
    Product toProduct(Product product, Farmer farmer, ProductRequest request);
    ProductResponse toResponse(Product product);
    List<ProductResponse> toResponseList(List<Product> products);
    DiscountedProductResponse toDiscountResponse(Product product);
    List<DiscountedProductResponse> toDiscountedResponseList(List<Product> products);
}
