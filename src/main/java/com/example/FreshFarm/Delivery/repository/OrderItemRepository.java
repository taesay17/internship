package com.example.FreshFarm.Delivery.repository;

import com.example.FreshFarm.Delivery.model.domain.Order;
import com.example.FreshFarm.Delivery.model.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrder(Order order);
}
