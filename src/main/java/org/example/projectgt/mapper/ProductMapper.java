package org.example.projectgt.mapper;

import org.example.projectgt.dto.request.ProductCreation;
import org.example.projectgt.dto.response.ProductResponse;
import org.example.projectgt.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "productHardwares", ignore = true)
    @Mapping(target = "productImgs", ignore = true)
    Product toProduct(ProductCreation productCreation);

    @Mapping(target = "productHardwares", ignore = true)
    @Mapping(target = "productImgs", ignore = true)
    ProductResponse toProductResponse(Product product);

    @Mapping(target = "productHardwares", ignore = true)
    @Mapping(target = "productImgs", ignore = true)
    @Mapping(target = "id", ignore = true)
    Product updateProduct(@MappingTarget Product product, ProductCreation productCreation);
}
