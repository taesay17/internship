package com.example.FreshFarm.Delivery.service;

import com.example.FreshFarm.Delivery.model.domain.Image;
import com.example.FreshFarm.Delivery.model.domain.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image save(MultipartFile image);
    Image save(MultipartFile image, Product product);
    void delete(String filename);
}
