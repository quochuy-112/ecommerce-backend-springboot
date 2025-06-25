package org.example.projectgt.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.CategoryGroupCreation;
import org.example.projectgt.dto.response.CategoryGroupResponse;
import org.example.projectgt.dto.response.SubcategoryResponse;
import org.example.projectgt.entity.Category;
import org.example.projectgt.entity.CategoryGroup;
import org.example.projectgt.exception.AppException;
import org.example.projectgt.exception.ErrorCode;
import org.example.projectgt.mapper.CategoryGroupMapper;
import org.example.projectgt.repository.CategoryGroupRepository;
import org.example.projectgt.repository.CategoryRepository;
import org.example.projectgt.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CategoryGroupService {
    CategoryRepository categoryRepository;
    CategoryGroupRepository categoryGroupRepository;
    CategoryGroupMapper categoryGroupMapper;
    SubcategoryRepository subcategoryRepository;

    public CategoryGroupResponse createCategoryGroup(CategoryGroupCreation categoryGroupCreation) {
        Category category = categoryRepository.findById(categoryGroupCreation.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXIST));

        if(categoryGroupRepository.existsByNameAndCategory_Id(categoryGroupCreation.getName(), category.getId()))
            throw new AppException(ErrorCode.CATEGORY_GROUP_EXISTED);

        CategoryGroup categoryGroup = categoryGroupMapper.toCategoryGroup(categoryGroupCreation);

        categoryGroup.setCategory(category);

        return categoryGroupMapper.toCategoryGroupResponse(categoryGroupRepository.save(categoryGroup));
    }

    public CategoryGroupResponse getCategoryGroupById(String id) {
        CategoryGroup categoryGroup = categoryGroupRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_GROUP_NOT_EXIST));

        CategoryGroupResponse categoryGroupResponse = categoryGroupMapper.toCategoryGroupResponse(categoryGroup);

        categoryGroupResponse.setSubcategories(subcategoryRepository.findByCategoryGroup_Id(id).stream()
                .map(subcategory -> SubcategoryResponse.builder()
                        .id(subcategory.getId())
                        .name(subcategory.getName())
                        .build())
                .collect(Collectors.toSet()));

        return categoryGroupResponse;
    }

    public CategoryGroupResponse updateCategoryGroup(String id, CategoryGroupCreation categoryGroupCreation) {
        CategoryGroup categoryGroup = categoryGroupRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_GROUP_NOT_EXIST));

        categoryGroupMapper.updateCategoryGroup(categoryGroup, categoryGroupCreation);

        CategoryGroupResponse categoryGroupResponse = categoryGroupMapper.toCategoryGroupResponse(categoryGroupRepository.save(categoryGroup));
        categoryGroupResponse.setSubcategories(subcategoryRepository.findByCategoryGroup_Id(id).stream()
                .map(subcategory -> SubcategoryResponse.builder()
                        .id(subcategory.getId())
                        .name(subcategory.getName())
                        .build())
                .collect(Collectors.toSet()));

        return categoryGroupResponse;
    }

    public void deleteCategoryGroup(String id) {
        if(!categoryGroupRepository.existsById(id))
            throw new AppException(ErrorCode.CATEGORY_GROUP_NOT_EXIST);

        categoryGroupRepository.deleteById(id);
    }
}
