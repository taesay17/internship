package com.example.FreshFarm.Delivery.mapper;

import com.example.FreshFarm.Delivery.model.domain.Farmer;
import com.example.FreshFarm.Delivery.model.domain.Image;
import com.example.FreshFarm.Delivery.model.dto.farmer.FarmerRequest;
import com.example.FreshFarm.Delivery.model.dto.farmer.FarmerResponse;

import java.util.List;

public interface FarmerMapper {
    Farmer toFarmer(Farmer farmer, FarmerRequest request, Image image);
    FarmerResponse toResponse(Farmer farmer);
    List<FarmerResponse> toResponseList(List<Farmer> farmers);
}
