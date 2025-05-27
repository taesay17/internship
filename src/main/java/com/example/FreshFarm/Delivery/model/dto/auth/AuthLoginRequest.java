package com.example.FreshFarm.Delivery.model.dto.auth;

import lombok.Data;

@Data
public class AuthLoginRequest {
    private String email;
    private String password;
}
