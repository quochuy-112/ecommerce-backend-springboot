package org.example.projectgt.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.CategoryCreation;
import org.example.projectgt.dto.response.ApiResponse;
import org.example.projectgt.dto.response.CategoryResponse;
import org.example.projectgt.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/category")
public class CategoryController {
    CategoryService categoryService;

    @PostMapping("")
    public ApiResponse<CategoryResponse> createCategory(@Valid @RequestBody CategoryCreation categoryCreation) {
        ApiResponse<CategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(categoryService.createCategory(categoryCreation));

        return apiResponse;
    }
}
