package org.example.projectgt.repository;

import org.example.projectgt.entity.CategoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryGroupRepository extends JpaRepository<CategoryGroup, String> {
    boolean existsByName(String name);
    boolean existsByNameAndCategory_Id(String name, String id);

    List<CategoryGroup> findByCategory_Id(String id);
}