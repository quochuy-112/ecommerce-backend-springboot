package org.example.projectgt.mapper;

import org.example.projectgt.dto.request.CategoryCreation;
import org.example.projectgt.dto.request.ProductCreation;
import org.example.projectgt.dto.response.CategoryResponse;
import org.example.projectgt.entity.Category;
import org.example.projectgt.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreation categoryCreation);

    @Mapping(target = "categoryGroups", ignore = true)
    CategoryResponse toCategoryResponse(Category category);

    @Mapping(target = "categoryGroups", ignore = true)
    Category updateCategory(@MappingTarget Category category, CategoryCreation categoryCreation);
}
