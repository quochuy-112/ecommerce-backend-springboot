package org.example.projectgt.mapper;

import org.example.projectgt.dto.request.TopProductCreationRequest;
import org.example.projectgt.dto.request.TopProductUpdateRequest;
import org.example.projectgt.dto.response.TopProductResponse;
import org.example.projectgt.entity.TopProduct;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TopProductMapper {
    TopProduct toTopProduct(TopProductCreationRequest request);

    TopProductResponse toTopProductResponse(TopProduct topProduct);

    void updateTopProduct(@MappingTarget TopProduct topProduct, TopProductUpdateRequest request);
}