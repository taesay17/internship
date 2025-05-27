package com.example.FreshFarm.Delivery.service;

import com.example.FreshFarm.Delivery.model.dto.auth.AuthLoginRequest;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthRegisterRequest;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthResponse;

public interface AuthService {
    AuthResponse register(AuthRegisterRequest request);
    AuthResponse login(AuthLoginRequest request);
}
