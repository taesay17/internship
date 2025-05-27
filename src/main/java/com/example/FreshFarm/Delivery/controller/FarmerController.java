package com.example.FreshFarm.Delivery.controller;

import com.example.FreshFarm.Delivery.model.dto.farmer.FarmerRequest;
import com.example.FreshFarm.Delivery.model.dto.farmer.FarmerResponse;
import com.example.FreshFarm.Delivery.service.FarmerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/farmers")
public class FarmerController {
    private final FarmerService farmerService;

    @GetMapping
    public List<FarmerResponse> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return farmerService.all(page, size);
    }

    @GetMapping("/profile")
    public FarmerResponse getProfile(@RequestHeader("Authorization") String token) {
        return farmerService.getProfile(token);
    }

    @GetMapping("/{id}")
    public FarmerResponse getById(@PathVariable Long id) {
        return farmerService.getById(id);
    }

    @PutMapping
    public FarmerResponse update(
            @RequestHeader("Authorization") String token,
            @RequestPart(name = "request") FarmerRequest request,
            @RequestPart(name = "image") MultipartFile image
    ) {
        return farmerService.update(token, request, image);
    }

    @DeleteMapping
    public void delete(@RequestHeader("Authorization") String token) {
        farmerService.delete(token);
    }
}
