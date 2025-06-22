package org.example.projectgt.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.ProductCreation;
import org.example.projectgt.dto.response.ApiResponse;
import org.example.projectgt.dto.response.ProductResponse;
import org.example.projectgt.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/product")
public class ProductController {
    ProductService productService;

    @PostMapping("")
    ApiResponse<ProductResponse> createProduct(@Valid @RequestBody ProductCreation productCreation) {
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(productService.createProduct(productCreation));
        return apiResponse;
    }

    @GetMapping("")
    ApiResponse<List<ProductResponse>> getAllProducts() {
        ApiResponse<List<ProductResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(productService.getAllProducts());
        return apiResponse;
    }

    @GetMapping("/{id}")
    ApiResponse<ProductResponse> getProductById(@PathVariable String id) {
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(productService.getProductById(id));
        return apiResponse;
    }

    @PutMapping("/{id}")
    ApiResponse<ProductResponse> updateProduct(@PathVariable String id, @Valid @RequestBody ProductCreation productCreation) {
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(productService.updateProduct(id, productCreation));
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteProduct(@PathVariable String id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        productService.deleteProduct(id);
        apiResponse.setData("Product deleted successfully");
        return apiResponse;
    }

    @GetMapping("/pageable")
    ApiResponse<Page<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ApiResponse<Page<ProductResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(productService.getAllProducts(PageRequest.of(page, size)));
        return apiResponse;
    }
}
