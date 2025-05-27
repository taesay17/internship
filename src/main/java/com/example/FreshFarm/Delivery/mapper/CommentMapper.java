package com.example.FreshFarm.Delivery.mapper;

import com.example.FreshFarm.Delivery.model.domain.Comment;
import com.example.FreshFarm.Delivery.model.domain.Product;
import com.example.FreshFarm.Delivery.model.domain.User;
import com.example.FreshFarm.Delivery.model.dto.comment.CommentResponse;

import java.util.List;

public interface CommentMapper {
    Comment toComment(User user, Product product, String content);
    CommentResponse toResponse(Comment comment);
    List<CommentResponse> toResponseList(List<Comment> comments);
}
