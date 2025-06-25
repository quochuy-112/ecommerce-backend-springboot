package org.example.projectgt.repository;

import org.example.projectgt.entity.ProductSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSubcategoryRepository extends JpaRepository<ProductSubcategory, String> {
    List<ProductSubcategory> findBySubcategory_Id(String subcategory_Id);
    boolean existsBySubcategory_IdAndProduct_Id(String subcategory_Id, String product_Id);
}
