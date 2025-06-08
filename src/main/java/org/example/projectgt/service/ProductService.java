package org.example.projectgt.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.ProductCreationRequest;
import org.example.projectgt.dto.request.ProductUpdateRequest;
import org.example.projectgt.dto.response.ProductResponse;
import org.example.projectgt.entity.Product;
import org.example.projectgt.mapper.ProductMapper;
import org.example.projectgt.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    public ProductResponse createRequest(ProductCreationRequest request){
        if(productRepository.existsByName(request.getName()))
            throw new RuntimeException("Product name already exists");

        Product product = productMapper.toProduct(request);

        return productMapper.toProductResponse(productRepository.save(product));
    }

    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    public ProductResponse getProductById(String id){
        return productMapper.toProductResponse(productRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Email not found")));
    }

    public ProductResponse updateProduct(String id, ProductUpdateRequest request){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Email not found"));

        productMapper.updateProduct(product, request);

        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Transactional
    public void deleteUser(String id){
        productRepository.deleteById(id);
    }
}
