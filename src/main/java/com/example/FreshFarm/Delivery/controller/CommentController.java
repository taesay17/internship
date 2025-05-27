package com.example.FreshFarm.Delivery.controller;

import com.example.FreshFarm.Delivery.model.dto.comment.CommentRequest;
import com.example.FreshFarm.Delivery.model.dto.comment.CommentResponse;
import com.example.FreshFarm.Delivery.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{productId}")
    public CommentResponse leaveComment(
            @RequestBody CommentRequest commentRequest,
            @RequestHeader("Authorization") String token,
            @PathVariable Long productId
    ) {
        return commentService.leaveComment(commentRequest, token, productId);
    }

    @GetMapping("/{productId}")
    public List<CommentResponse> getAllComments(
            @PathVariable Long productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return commentService.getAllComments(productId, page, size);
    }

    @GetMapping("/amount/{productId}")
    public Integer getAmountOfComments(@PathVariable Long productId) {
        return commentService.getAmountOfComments(productId);
    }
}
