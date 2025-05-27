package com.example.FreshFarm.Delivery.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "image_tb")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String filename;
    @Column(unique = true, nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn
    private Product product;

    @OneToOne
    @JoinColumn
    private Farmer farmer;
}
