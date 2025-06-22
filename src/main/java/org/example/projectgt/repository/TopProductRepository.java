package org.example.projectgt.repository;

import org.example.projectgt.entity.TopProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopProductRepository extends JpaRepository<TopProduct, String> {
}
