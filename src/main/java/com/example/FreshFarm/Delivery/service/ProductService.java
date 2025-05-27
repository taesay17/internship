package com.example.FreshFarm.Delivery.service;

import com.example.FreshFarm.Delivery.model.dto.product.DiscountedProductResponse;
import com.example.FreshFarm.Delivery.model.dto.product.ProductRequest;
import com.example.FreshFarm.Delivery.model.dto.product.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductResponse> all(int page, int size);
    List<ProductResponse> farmerProducts(Long farmerId, int page, int size);
    List<ProductResponse> findByName(String name, int page, int size);
    List<ProductResponse> sortBy(String sortBy, String sortType, int page, int size);
    List<ProductResponse> getOwnProducts(String token, int page, int size);
    ProductResponse getDetail(Long id);
    ProductResponse add(String token, ProductRequest productRequest, List<MultipartFile> images);
    ProductResponse update(String token, Long id, ProductRequest productRequest);
    void delete(String token, Long id);
    Integer totalPages(int page, int size);
    List<DiscountedProductResponse> getDiscountedProducts(int page, int size);
    List<ProductResponse> getRelatedProducts(String name, double price, Long id, int page, int size);
}
