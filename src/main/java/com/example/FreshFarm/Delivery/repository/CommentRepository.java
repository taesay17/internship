package com.example.FreshFarm.Delivery.repository;

import com.example.FreshFarm.Delivery.model.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Integer countCommentByProduct_Id(Long productId);
}
