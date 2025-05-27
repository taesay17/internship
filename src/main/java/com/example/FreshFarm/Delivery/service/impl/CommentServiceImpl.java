package com.example.FreshFarm.Delivery.service.impl;

import com.example.FreshFarm.Delivery.config.JwtService;
import com.example.FreshFarm.Delivery.exception.CustomException;
import com.example.FreshFarm.Delivery.mapper.CommentMapper;
import com.example.FreshFarm.Delivery.model.domain.Product;
import com.example.FreshFarm.Delivery.model.domain.User;
import com.example.FreshFarm.Delivery.model.dto.comment.CommentRequest;
import com.example.FreshFarm.Delivery.model.dto.comment.CommentResponse;
import com.example.FreshFarm.Delivery.repository.CommentRepository;
import com.example.FreshFarm.Delivery.repository.ProductRepository;
import com.example.FreshFarm.Delivery.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final CommentMapper commentMapper;
    private final JwtService jwtService;

    @Override
    public CommentResponse leaveComment(CommentRequest commentRequest, String token, Long productId) {
        User user = jwtService.getUserFromToken(token);
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));

        return commentMapper.toResponse(
                commentRepository.save(
                        commentMapper.toComment(
                                user,
                                product,
                                commentRequest.getContent()
                        )
                )
        );
    }

    @Override
    public List<CommentResponse> getAllComments(Long productId, int page, int size) {
        return commentMapper.toResponseList(
                commentRepository.findAll(
                        PageRequest.of(page, size)
                ).toList()
        );
    }

    @Override
    public Integer getAmountOfComments(Long productId) {
        return commentRepository.countCommentByProduct_Id(productId);
    }
}
