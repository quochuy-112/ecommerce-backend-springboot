package org.example.projectgt.repository;

import org.example.projectgt.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, String> {
    boolean existsByNameAndCategoryGroup_Id(String name, String id);

    List<Subcategory> findByCategoryGroup_Id(String id);
}
