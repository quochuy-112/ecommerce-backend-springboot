package org.example.projectgt.mapper;

import org.example.projectgt.dto.request.CategoryGroupCreation;
import org.example.projectgt.dto.response.CategoryGroupResponse;
import org.example.projectgt.entity.CategoryGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryGroupMapper {
    CategoryGroup toCategoryGroup(CategoryGroupCreation categoryGroupCreation);

    @Mapping(target = "subcategories", ignore = true)
    CategoryGroupResponse toCategoryGroupResponse(CategoryGroup categoryGroup);

    @Mapping(target = "subcategories", ignore = true)
    @Mapping(target = "id", ignore = true)
    CategoryGroup updateCategoryGroup(@MappingTarget CategoryGroup categoryGroup, CategoryGroupCreation categoryGroupCreation);
}
