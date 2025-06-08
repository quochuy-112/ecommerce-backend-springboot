package org.example.projectgt.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.ProductCreationRequest;
import org.example.projectgt.dto.request.ProductUpdateRequest;
import org.example.projectgt.dto.response.ProductResponse;
import org.example.projectgt.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    ProductService productService;

    @PostMapping("")
    ProductResponse createProduct(@RequestBody ProductCreationRequest request) {
        return productService.createRequest(request);
    }

    @GetMapping("")
    List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    ProductResponse getProduct(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    ProductResponse updateProduct(@PathVariable String id,@RequestBody ProductUpdateRequest request){
        return productService.updateProduct(id,request);
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable String id) {
        productService.deleteUser(id);
    }
}
