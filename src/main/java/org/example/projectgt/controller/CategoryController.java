package org.example.projectgt.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.CategoryCreation;
import org.example.projectgt.dto.response.ApiResponse;
import org.example.projectgt.dto.response.CategoryResponse;
import org.example.projectgt.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getCategory(@PathVariable String id) {
        ApiResponse<CategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(categoryService.getCategory(id));

        return apiResponse;
    }

    @GetMapping("")
    public ApiResponse<List<CategoryResponse>> getAllCategories() {
        ApiResponse<List<CategoryResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(categoryService.getAllCategories());

        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoryResponse> updateCategory(@PathVariable String id, @Valid @RequestBody CategoryCreation categoryCreation) {
        ApiResponse<CategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(categoryService.updateCategory(id, categoryCreation));

        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCategory(@PathVariable String id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        categoryService.deleteCategory(id);

        apiResponse.setData("Category deleted successfully");

        return apiResponse;
    }
}
