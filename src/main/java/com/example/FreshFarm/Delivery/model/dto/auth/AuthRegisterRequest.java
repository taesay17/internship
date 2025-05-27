package com.example.FreshFarm.Delivery.model.dto.auth;

import lombok.Data;

@Data
public class AuthRegisterRequest {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String role;
}
