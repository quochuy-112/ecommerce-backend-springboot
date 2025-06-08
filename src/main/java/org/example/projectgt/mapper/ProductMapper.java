package org.example.projectgt.mapper;

import org.example.projectgt.dto.request.ProductCreationRequest;
import org.example.projectgt.dto.request.ProductUpdateRequest;
import org.example.projectgt.dto.response.ProductResponse;
import org.example.projectgt.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductCreationRequest request);

    ProductResponse toProductResponse(Product product);

    void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);
}
