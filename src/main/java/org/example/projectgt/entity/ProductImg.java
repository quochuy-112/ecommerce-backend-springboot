package org.example.projectgt.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductImg {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String imgUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}