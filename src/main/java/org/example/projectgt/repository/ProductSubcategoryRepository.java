package org.example.projectgt.repository;

import org.example.projectgt.entity.ProductSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSubcategoryRepository extends JpaRepository<ProductSubcategory, String> {
}
