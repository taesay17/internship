package com.example.FreshFarm.Delivery.service.impl;

import com.example.FreshFarm.Delivery.config.JwtService;
import com.example.FreshFarm.Delivery.exception.CustomException;
import com.example.FreshFarm.Delivery.mapper.AuthMapper;
import com.example.FreshFarm.Delivery.model.domain.Farmer;
import com.example.FreshFarm.Delivery.model.domain.User;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthLoginRequest;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthRegisterRequest;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthResponse;
import com.example.FreshFarm.Delivery.model.enums.Role;
import com.example.FreshFarm.Delivery.repository.FarmerRepository;
import com.example.FreshFarm.Delivery.repository.UserRepository;
import com.example.FreshFarm.Delivery.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final FarmerRepository farmerRepository;


    @Override
    public AuthResponse register(AuthRegisterRequest request) {
        if (!request.getPassword().trim().equals(request.getConfirmPassword().trim())) {
            throw new CustomException("Incorrect password", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException("User is already contains", HttpStatus.FOUND);
        }
        request.setPassword(encoder.encode(request.getPassword()));
        User user = userRepository.save(authMapper.toUser(request));
        if (Role.FARMER.equals(user.getRole())) {
            Farmer farmer = new Farmer();
            farmer.setUserDetails(user);
            farmerRepository.save(farmer);
        }
        String token = jwtService.generateToken(user);
        return authMapper.toResponse(user.getId(), token);
    }

    @Override
    public AuthResponse login(AuthLoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
        return authMapper.toResponse(user.getId(), jwtService.generateToken(user));
    }
}
