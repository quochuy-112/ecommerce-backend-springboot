package org.example.projectgt.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.SubcategoryCreation;
import org.example.projectgt.dto.response.ProductSubcategoryResponse;
import org.example.projectgt.dto.response.SubcategoryResponse;
import org.example.projectgt.entity.CategoryGroup;
import org.example.projectgt.entity.ProductSubcategory;
import org.example.projectgt.entity.Subcategory;
import org.example.projectgt.exception.AppException;
import org.example.projectgt.exception.ErrorCode;
import org.example.projectgt.mapper.SubcategoryMapper;
import org.example.projectgt.repository.CategoryGroupRepository;
import org.example.projectgt.repository.ProductRepository;
import org.example.projectgt.repository.ProductSubcategoryRepository;
import org.example.projectgt.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SubcategoryService {
    SubcategoryRepository subcategoryRepository;
    CategoryGroupRepository categoryGroupRepository;
    ProductSubcategoryRepository productSubcategoryRepository;
    ProductRepository productRepository;
    ProductService productService;
    SubcategoryMapper subcategoryMapper;

    public SubcategoryResponse createSubcategory(SubcategoryCreation subcategoryCreation) {
        CategoryGroup categoryGroup = categoryGroupRepository.findById(subcategoryCreation.getSubcategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_GROUP_NOT_EXIST));

        if(subcategoryRepository.existsByNameAndCategoryGroup_Id(subcategoryCreation.getName(), categoryGroup.getId())) {
            throw new AppException(ErrorCode.SUBCATEGORY_EXISTED);
        }

        Subcategory subcategory = subcategoryMapper.toSubcategory(subcategoryCreation);
        subcategory.setCategoryGroup(categoryGroup);

        return subcategoryMapper.toSubcategoryResponse(subcategoryRepository.save(subcategory));
    }

    public SubcategoryResponse getSubcategoryById(String id) {
        Subcategory subcategory = subcategoryRepository.findById(id).
                orElseThrow(() -> new AppException(ErrorCode.SUBCATEGORY_NOT_EXIST));

        SubcategoryResponse subcategoryResponse = subcategoryMapper.toSubcategoryResponse(subcategory);

        Set<ProductSubcategoryResponse> productSubcategoryResponses = productSubcategoryRepository.findBySubcategory_Id(id).stream()
                .map(productSubcategory -> ProductSubcategoryResponse.builder()
                        .id(productSubcategory.getId())
                        .product(productService.getProductById(productSubcategory.getProduct().getId()))
                        .build())
                .collect(Collectors.toSet());

        subcategoryResponse.setProductSubcategories(productSubcategoryResponses);

        return subcategoryResponse;
    }

    public SubcategoryResponse updateSubcategory(String id, SubcategoryCreation subcategoryCreation) {
        Subcategory subcategory = subcategoryRepository.findById(id).
                orElseThrow(() -> new AppException(ErrorCode.SUBCATEGORY_NOT_EXIST));

        subcategoryMapper.updateSubcategory(subcategory, subcategoryCreation);

        SubcategoryResponse subcategoryResponse = subcategoryMapper.toSubcategoryResponse(subcategoryRepository.save(subcategory));

        Set<ProductSubcategoryResponse> productSubcategoryResponses = productSubcategoryRepository.findBySubcategory_Id(id).stream()
                .map(productSubcategory -> ProductSubcategoryResponse.builder()
                        .id(productSubcategory.getId())
                        .product(productService.getProductById(productSubcategory.getProduct().getId()))
                        .build())
                .collect(Collectors.toSet());

        subcategoryResponse.setProductSubcategories(productSubcategoryResponses);

        return subcategoryResponse;
    }

    public void deleteSubcategory(String id) {
        if(!subcategoryRepository.existsById(id)) {
            throw new AppException(ErrorCode.SUBCATEGORY_NOT_EXIST);
        }
        subcategoryRepository.deleteById(id);
    }

    public SubcategoryResponse addProductToSubcategory(String subcategoryId, List<String> productIds) {
        if(!subcategoryRepository.existsById(subcategoryId)) {
            throw new AppException(ErrorCode.SUBCATEGORY_NOT_EXIST);
        }

        List<ProductSubcategory> productSubcategories = new ArrayList<>();

        for(String productId : productIds) {
            if(!productRepository.existsById(productId)) {
                throw new AppException(ErrorCode.PRODUCT_NOT_EXIST);
            }

            if(productSubcategoryRepository.existsBySubcategory_IdAndProduct_Id(subcategoryId, productId)) {
                throw new AppException(ErrorCode.PRODUCT_EXISTED_IN_SUBCATEGORY);
            }

            ProductSubcategory productSubcategory = ProductSubcategory.builder()
                    .product(productRepository.findById(productId).get())
                    .subcategory(subcategoryRepository.findById(subcategoryId).get())
                    .build();

            productSubcategories.add(productSubcategory);
        }

        productSubcategoryRepository.saveAll(productSubcategories);

        return getSubcategoryById(subcategoryId);
    }
}
