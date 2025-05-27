package com.example.FreshFarm.Delivery.mapper;

import com.example.FreshFarm.Delivery.model.domain.User;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthRegisterRequest;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthResponse;

public interface AuthMapper {
    User toUser(AuthRegisterRequest authRegisterRequest);
    AuthResponse toResponse(Long id, String token);
}
