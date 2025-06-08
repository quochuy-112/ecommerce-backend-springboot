package org.example.projectgt.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.TopProductCreationRequest;
import org.example.projectgt.dto.request.TopProductUpdateRequest;
import org.example.projectgt.dto.response.TopProductResponse;
import org.example.projectgt.service.TopProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/top-products")
@RequiredArgsConstructor
public class TopProductController {
    TopProductService topProductService;

    @PostMapping("")
    TopProductResponse createTopProduct(@RequestBody TopProductCreationRequest request) {
        return topProductService.createRequest(request);
    }

    @GetMapping("")
    List<TopProductResponse> getAllTopProducts() {
        return topProductService.getAllTopProducts();
    }

    @GetMapping("/{id}")
    TopProductResponse getTopProduct(@PathVariable String id) {
        return topProductService.getTopProductById(id);
    }

    @PutMapping("/{id}")
    TopProductResponse updateTopProduct(@PathVariable String id,@RequestBody TopProductUpdateRequest request){
        return topProductService.updateTopProduct(id, request);
    }

    @DeleteMapping("/{id}")
    void deleteTopProduct(@PathVariable String id) {
        topProductService.deleteTopProduct(id);
    }
}
