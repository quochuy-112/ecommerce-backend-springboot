package org.example.projectgt.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.CategoryCreation;
import org.example.projectgt.dto.response.CategoryGroupResponse;
import org.example.projectgt.dto.response.CategoryResponse;
import org.example.projectgt.dto.response.SubcategoryResponse;
import org.example.projectgt.entity.Category;
import org.example.projectgt.exception.AppException;
import org.example.projectgt.exception.ErrorCode;
import org.example.projectgt.mapper.CategoryMapper;
import org.example.projectgt.repository.CategoryGroupRepository;
import org.example.projectgt.repository.CategoryRepository;
import org.example.projectgt.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryGroupRepository categoryGroupRepository;
    SubcategoryRepository subcategoryRepository;
    CategoryMapper categoryMapper;

    public CategoryResponse createCategory(CategoryCreation categoryCreation) {
        if(categoryRepository.existsByName(categoryCreation.getName())) {
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }

        Category category = categoryMapper.toCategory(categoryCreation);

        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public CategoryResponse getCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXIST));

        CategoryResponse categoryResponse = categoryMapper.toCategoryResponse(category);

        Set<CategoryGroupResponse> categoryGroupResponses = categoryGroupRepository.findByCategory_Id(id).stream()
                .map(categoryGroup -> {

                    Set<SubcategoryResponse> subcategoryResponses = subcategoryRepository.findByCategoryGroup_Id(categoryGroup.getId()).stream()
                            .map(subcategory -> SubcategoryResponse.builder()
                                    .id(subcategory.getId())
                                    .name(subcategory.getName())
                                    .build())
                            .collect(Collectors.toSet());

                    return CategoryGroupResponse.builder()
                            .id(categoryGroup.getId())
                            .name(categoryGroup.getName())
                            .subcategories(subcategoryResponses)
                            .build();
                })

                .collect(Collectors.toSet());

        categoryResponse.setCategoryGroups(categoryGroupResponses);

        return categoryResponse;
    }

    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = categories.stream()
                .map(categoryMapper::toCategoryResponse)
                .collect(Collectors.toList());

        categoryResponses.forEach(categoryResponse -> {
            Set<CategoryGroupResponse> categoryGroupResponses = categoryGroupRepository.findByCategory_Id(categoryResponse.getId()).stream()
                    .map(categoryGroup -> {

                        Set<SubcategoryResponse> subcategoryResponses = subcategoryRepository.findByCategoryGroup_Id(categoryGroup.getId()).stream()
                                .map(subcategory -> SubcategoryResponse.builder()
                                        .id(subcategory.getId())
                                        .name(subcategory.getName())
                                        .build())
                                .collect(Collectors.toSet());

                        return CategoryGroupResponse.builder()
                                .id(categoryGroup.getId())
                                .name(categoryGroup.getName())
                                .subcategories(subcategoryResponses)
                                .build();
                    })
                    .collect(Collectors.toSet());

            categoryResponse.setCategoryGroups(categoryGroupResponses);
        });

        return categoryResponses;
    }

    public CategoryResponse updateCategory(String id, CategoryCreation categoryCreation) {
        if(!categoryRepository.existsById(id)) {
            throw new AppException(ErrorCode.CATEGORY_NOT_EXIST);
        }

        Category category = categoryMapper.updateCategory(categoryRepository.findById(id).get(), categoryCreation);
        categoryRepository.save(category);

        return getCategory(id);
    }

    public void deleteCategory(String id) {
        if(!categoryRepository.existsById(id)) {
            throw new AppException(ErrorCode.CATEGORY_NOT_EXIST);
        }
        categoryRepository.deleteById(id);
    }
}
