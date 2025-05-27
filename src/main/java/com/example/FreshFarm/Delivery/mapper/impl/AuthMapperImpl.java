package com.example.FreshFarm.Delivery.mapper.impl;

import com.example.FreshFarm.Delivery.mapper.AuthMapper;
import com.example.FreshFarm.Delivery.model.domain.User;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthRegisterRequest;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthResponse;
import com.example.FreshFarm.Delivery.model.enums.Role;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuthMapperImpl implements AuthMapper {
    @Override
    public User toUser(AuthRegisterRequest authRegisterRequest) {
        User user = new User();
        user.setName(authRegisterRequest.getUsername());
        user.setPassword(authRegisterRequest.getPassword());
        user.setEmail(authRegisterRequest.getEmail());
        user.setRole(Role.valueOf(authRegisterRequest.getRole()));
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    @Override
    public AuthResponse toResponse(Long id, String token) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setId(id);
        authResponse.setToken(token);
        return authResponse;
    }
}
