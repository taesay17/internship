package com.example.FreshFarm.Delivery.mapper.impl;

import com.example.FreshFarm.Delivery.mapper.FarmerMapper;
import com.example.FreshFarm.Delivery.model.domain.Farmer;
import com.example.FreshFarm.Delivery.model.domain.Image;
import com.example.FreshFarm.Delivery.model.dto.farmer.FarmerRequest;
import com.example.FreshFarm.Delivery.model.dto.farmer.FarmerResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FarmerMapperImpl implements FarmerMapper {
    @Override
    public Farmer toFarmer(Farmer farmer, FarmerRequest request, Image image) {
        farmer.getUserDetails().setName(request.getFarmerName());
        farmer.setAddress(request.getAddress());
        farmer.setPhone(request.getPhone());
        farmer.setImage(image);
        return farmer;
    }

    @Override
    public FarmerResponse toResponse(Farmer farmer) {
        FarmerResponse response = new FarmerResponse();
        response.setId(farmer.getId());
        response.setFarmerName(farmer.getUserDetails().getName());
        response.setImagePath(farmer.getImage().getPath());
        response.setEmail(farmer.getUserDetails().getEmail());
        response.setAddress(farmer.getAddress());
        response.setPhone(farmer.getPhone());
        response.setCreatedAt(farmer.getUserDetails().getCreatedAt());
        return response;
    }

    @Override
    public List<FarmerResponse> toResponseList(List<Farmer> farmers) {
        List<FarmerResponse> responseList = new ArrayList<>();
        for (Farmer farmer : farmers) {
            responseList.add(toResponse(farmer));
        }
        return responseList;
    }
}
