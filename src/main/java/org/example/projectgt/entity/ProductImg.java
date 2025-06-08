package org.example.projectgt.entity;

import jakarta.persistence.*;

@Entity
public class ProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String imgUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}