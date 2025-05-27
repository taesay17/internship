package com.example.FreshFarm.Delivery.mapper.impl;

import com.example.FreshFarm.Delivery.mapper.CommentMapper;
import com.example.FreshFarm.Delivery.model.domain.Comment;
import com.example.FreshFarm.Delivery.model.domain.Product;
import com.example.FreshFarm.Delivery.model.domain.User;
import com.example.FreshFarm.Delivery.model.dto.comment.CommentResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapperImpl implements CommentMapper {
    @Override
    public Comment toComment(User user, Product product, String content) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setProduct(product);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());

        return comment;
    }

    @Override
    public CommentResponse toResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setContent(comment.getContent());
        response.setCreatedAt(comment.getCreatedAt());
        response.setUserEmail(comment.getUser().getEmail());
        response.setProductName(comment.getProduct().getName());
        return response;
    }

    @Override
    public List<CommentResponse> toResponseList(List<Comment> comments) {
        List<CommentResponse> responseList = new ArrayList<>();
        for (Comment comment : comments) {
            responseList.add(toResponse(comment));
        }
        return responseList;
    }
}
