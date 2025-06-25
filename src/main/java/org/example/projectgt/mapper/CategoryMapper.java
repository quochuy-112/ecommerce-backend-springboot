package org.example.projectgt.mapper;

import org.example.projectgt.dto.request.CategoryCreation;
import org.example.projectgt.dto.response.CategoryResponse;
import org.example.projectgt.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreation categoryCreation);

    @Mapping(target = "categoryGroups", ignore = true)
    CategoryResponse toCategoryResponse(Category category);

    @Mapping(target = "categoryGroups", ignore = true)
    @Mapping(target = "id", ignore = true)
    Category updateCategory(@MappingTarget Category category, CategoryCreation categoryCreation);
}
