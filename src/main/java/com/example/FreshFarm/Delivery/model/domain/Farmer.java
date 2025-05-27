package com.example.FreshFarm.Delivery.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "farmers_tb")
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn
    private User userDetails;

    @OneToMany(mappedBy = "farmer")
    private List<Product> products;

    @OneToOne(mappedBy = "farmer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Image image;
}
