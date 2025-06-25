package org.example.projectgt.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.CategoryGroupCreation;
import org.example.projectgt.dto.response.ApiResponse;
import org.example.projectgt.dto.response.CategoryGroupResponse;
import org.example.projectgt.service.CategoryGroupService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/category-group")
public class CategoryGroupController {
    CategoryGroupService categoryGroupService;

    @PostMapping("")
    public ApiResponse<CategoryGroupResponse> createCategoryGroup(@Valid @RequestBody CategoryGroupCreation categoryGroupCreation) {
        ApiResponse<CategoryGroupResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(categoryGroupService.createCategoryGroup(categoryGroupCreation));

        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryGroupResponse> getCategoryGroup(@PathVariable String id) {
        ApiResponse<CategoryGroupResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(categoryGroupService.getCategoryGroupById(id));

        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoryGroupResponse> updateCategoryGroup(@PathVariable String id, @Valid @RequestBody CategoryGroupCreation categoryGroupCreation) {
        ApiResponse<CategoryGroupResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(categoryGroupService.updateCategoryGroup(id, categoryGroupCreation));

        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCategoryGroup(@PathVariable String id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        categoryGroupService.deleteCategoryGroup(id);
        apiResponse.setData("Category group deleted successfully");

        return apiResponse;
    }
}
