package org.example.projectgt.mapper;

import org.example.projectgt.dto.request.SubcategoryCreation;
import org.example.projectgt.dto.response.SubcategoryResponse;
import org.example.projectgt.entity.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubcategoryMapper {
    Subcategory toSubcategory(SubcategoryCreation subcategoryCreation);

    @Mapping(target = "productSubcategories", ignore = true)
    SubcategoryResponse toSubcategoryResponse(Subcategory subcategory);

    @Mapping(target = "productSubcategories", ignore = true)
    @Mapping(target = "id", ignore = true)
    Subcategory updateSubcategory(@MappingTarget Subcategory subcategory, SubcategoryCreation subcategoryCreation);
}
