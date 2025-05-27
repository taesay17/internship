package com.example.FreshFarm.Delivery.model.dto.comment;

import com.amazonaws.services.dynamodbv2.xspec.S;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponse {
    private String content;
    private LocalDateTime createdAt;
    private String userEmail;
    private String productName;
}
