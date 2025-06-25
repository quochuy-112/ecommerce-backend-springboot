package org.example.projectgt.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.SubcategoryCreation;
import org.example.projectgt.dto.response.ApiResponse;
import org.example.projectgt.dto.response.SubcategoryResponse;
import org.example.projectgt.service.SubcategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/subcategory")
public class SubcategoryController {
    SubcategoryService subcategoryService;

    @PostMapping("")
    public ApiResponse<SubcategoryResponse> createSubcategory(@Valid @RequestBody SubcategoryCreation subcategoryCreation) {
        ApiResponse<SubcategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(subcategoryService.createSubcategory(subcategoryCreation));

        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<SubcategoryResponse> getSubcategory(@PathVariable String id) {
        ApiResponse<SubcategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(subcategoryService.getSubcategoryById(id));

        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<SubcategoryResponse> updateSubcategory(@PathVariable String id, @Valid @RequestBody SubcategoryCreation subcategoryCreation) {
        ApiResponse<SubcategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(subcategoryService.updateSubcategory(id, subcategoryCreation));

        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteSubcategory(@PathVariable String id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        subcategoryService.deleteSubcategory(id);
        apiResponse.setData("Subcategory deleted successfully");

        return apiResponse;
    }

    @PostMapping("/add-product/{id}")
    public ApiResponse<SubcategoryResponse> addProductToSubcategory(@PathVariable String id, @RequestBody List<String> productIds) {
        ApiResponse<SubcategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(subcategoryService.addProductToSubcategory(id, productIds));

        return apiResponse;
    }
}
