package com.example.FreshFarm.Delivery.controller;

import com.example.FreshFarm.Delivery.model.dto.auth.AuthLoginRequest;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthRegisterRequest;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthResponse;
import com.example.FreshFarm.Delivery.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@ModelAttribute AuthRegisterRequest request, HttpServletResponse response) {
        AuthResponse authResponse = authService.register(request);
        cookieOperation(authResponse, response);
        return "redirect:/pages/home";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute AuthLoginRequest request, HttpServletResponse response) {
        AuthResponse authResponse = authService.login(request);
        cookieOperation(authResponse, response);
        return "redirect:/pages/home";
    }

    private void cookieOperation(AuthResponse authResponse, HttpServletResponse response) {
        Cookie tokenCookie = new Cookie("access_token", authResponse.getToken());
        Cookie idCookie = new Cookie("user_id", authResponse.getId().toString());

        tokenCookie.setMaxAge(24 * 60 * 60);
        tokenCookie.setPath("/");
        idCookie.setMaxAge(24 * 60 * 60);
        idCookie.setPath("/");

        response.addCookie(tokenCookie);
        response.addCookie(idCookie);
    }
}
