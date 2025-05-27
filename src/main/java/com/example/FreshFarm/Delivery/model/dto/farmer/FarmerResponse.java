package com.example.FreshFarm.Delivery.model.dto.farmer;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FarmerResponse {
    private Long id;
    private String farmerName;
    private String imagePath;
    private String email;
    private String address;
    private String phone;
    private LocalDateTime createdAt;
}
