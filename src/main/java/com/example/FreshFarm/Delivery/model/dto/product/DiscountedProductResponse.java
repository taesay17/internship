package com.example.FreshFarm.Delivery.model.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountedProductResponse extends ProductResponse {
    private Integer discount;
    private Double discountPrice;
}
