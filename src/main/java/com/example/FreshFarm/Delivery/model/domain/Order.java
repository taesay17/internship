package com.example.FreshFarm.Delivery.model.domain;

import com.example.FreshFarm.Delivery.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders_tb")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private double totalPrice;

    @ManyToOne
    @JoinColumn
    private User customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
