package com.example.FreshFarm.Delivery.service;

import com.example.FreshFarm.Delivery.model.dto.comment.CommentRequest;
import com.example.FreshFarm.Delivery.model.dto.comment.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse leaveComment(CommentRequest commentRequest, String token, Long productId);
    List<CommentResponse> getAllComments(Long productId, int page, int size);
    Integer getAmountOfComments(Long productId);
}
