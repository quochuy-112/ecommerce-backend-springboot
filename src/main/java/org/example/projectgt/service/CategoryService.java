package org.example.projectgt.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.CategoryCreation;
import org.example.projectgt.dto.response.CategoryResponse;
import org.example.projectgt.entity.Category;
import org.example.projectgt.exception.AppException;
import org.example.projectgt.exception.ErrorCode;
import org.example.projectgt.mapper.CategoryMapper;
import org.example.projectgt.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public CategoryResponse createCategory(CategoryCreation categoryCreation) {
        if(categoryRepository.existsByName(categoryCreation.getName())) {
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }

        Category category = categoryMapper.toCategory(categoryCreation);

        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }
}
