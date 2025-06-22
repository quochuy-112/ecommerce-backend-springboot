package org.example.projectgt.service;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.ProductCreation;
import org.example.projectgt.dto.response.ProductHardwareResponse;
import org.example.projectgt.dto.response.ProductImgResponse;
import org.example.projectgt.dto.response.ProductResponse;
import org.example.projectgt.entity.Hardware;
import org.example.projectgt.entity.Product;
import org.example.projectgt.entity.ProductHardware;
import org.example.projectgt.entity.ProductImg;
import org.example.projectgt.exception.AppException;
import org.example.projectgt.exception.ErrorCode;
import org.example.projectgt.mapper.ProductMapper;
import org.example.projectgt.repository.HardwareRepository;
import org.example.projectgt.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductService {
    ProductRepository productRepository;
    HardwareRepository hardwareRepository;
    ProductMapper productMapper;

    public ProductResponse createProduct(ProductCreation productCreation) {
        if(productRepository.existsByName(productCreation.getName()))
            throw new AppException(ErrorCode.PRODUCT_EXISTED);

        Product product = productMapper.toProduct(productCreation);

        Set<ProductHardware> productHardwares = getProductHardwares(product, productCreation);
        Set<ProductImg> productImgs = getProductImgs(product, productCreation);

        product.setProductHardwares(productHardwares);
        product.setProductImgs(productImgs);

        Product savedProduct = productRepository.save(product);

        ProductResponse productResponse = productMapper.toProductResponse(savedProduct);

        Set<ProductHardwareResponse> productHardwareResponses = getProductHardwareResponses(savedProduct);
        Set<ProductImgResponse> productImgResponses = getProductImgResponses(savedProduct);

        productResponse.setProductHardwares(productHardwareResponses);
        productResponse.setProductImgs(productImgResponses);

        return productResponse;
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();

        products.forEach(product -> {
            ProductResponse productResponse = productMapper.toProductResponse(product);

            Set<ProductHardwareResponse> productHardwareResponses = getProductHardwareResponses(product);
            Set<ProductImgResponse> productImgResponses = getProductImgResponses(product);
            productResponse.setProductHardwares(productHardwareResponses);
            productResponse.setProductImgs(productImgResponses);

            productResponses.add(productResponse);
        });

        return productResponses;
    }

    public ProductResponse getProductById(String id) {
        if(!productRepository.existsById(id))
            throw new AppException(ErrorCode.PRODUCT_NOT_EXIST);

        Product product = productRepository.findById(id).get();
        ProductResponse productResponse = productMapper.toProductResponse(product);

        Set<ProductHardwareResponse> productHardwareResponses = getProductHardwareResponses(product);
        Set<ProductImgResponse> productImgResponses = getProductImgResponses(product);
        productResponse.setProductHardwares(productHardwareResponses);
        productResponse.setProductImgs(productImgResponses);

        return productResponse;
    }

    public ProductResponse updateProduct(String id, ProductCreation productCreation) {
        if(!productRepository.existsById(id))
            throw new AppException(ErrorCode.PRODUCT_NOT_EXIST);

        Product product = productMapper.updateProduct(productRepository.findById(id).get(), productCreation);

        Set<ProductHardware> productHardwares = getProductHardwares(product, productCreation);
        Set<ProductImg> productImgs = getProductImgs(product, productCreation);

        product.getProductHardwares().clear();
        product.getProductHardwares().addAll(productHardwares);
        product.getProductImgs().clear();
        product.getProductImgs().addAll(productImgs);

        Product savedProduct = productRepository.save(product);

        ProductResponse productResponse = productMapper.toProductResponse(savedProduct);

        Set<ProductHardwareResponse> productHardwareResponses = getProductHardwareResponses(savedProduct);
        Set<ProductImgResponse> productImgResponses = getProductImgResponses(savedProduct);

        productResponse.setProductHardwares(productHardwareResponses);
        productResponse.setProductImgs(productImgResponses);

        return productResponse;
    }

    public void deleteProduct(String id) {
        if(!productRepository.existsById(id))
            throw new AppException(ErrorCode.PRODUCT_NOT_EXIST);

        productRepository.deleteById(id);
    }

    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);

        List<ProductResponse> productResponses = new ArrayList<>();

        products.getContent().forEach(product -> {
            ProductResponse productResponse = productMapper.toProductResponse(product);

            Set<ProductHardwareResponse> productHardwareResponses = getProductHardwareResponses(product);
            Set<ProductImgResponse> productImgResponses = getProductImgResponses(product);

            productResponse.setProductHardwares(productHardwareResponses);
            productResponse.setProductImgs(productImgResponses);

            productResponses.add(productResponse);
        });

        return new PageImpl<>(
                productResponses,
                pageable,
                products.getTotalElements()
        );
    }

    Set<ProductHardware> getProductHardwares(Product product, ProductCreation productCreation){
        Set<ProductHardware> productHardwares = new HashSet<>();

        productCreation.getProductHardwares().forEach(productHardware -> {
            if(!hardwareRepository.existsById(productHardware.getHardwareId()))
                throw new AppException(ErrorCode.HARDWARE_NOT_EXIST);

            String value = productHardware.getValue();
            Hardware hardware = hardwareRepository.findById(productHardware.getHardwareId()).get();

            productHardwares.add(ProductHardware.builder()
                    .value(value)
                    .hardware(hardware)
                    .product(product)
                    .build());
        });

        return productHardwares;
    }

    Set<ProductImg> getProductImgs(Product product, ProductCreation productCreation) {
        Set<ProductImg> productImgs = new HashSet<>();

        productCreation.getProductImgs().forEach(productImg -> {
            productImgs.add(ProductImg.builder()
                    .imgUrl(productImg.getImgUrl())
                    .product(product)
                    .build());
        });
        return productImgs;
    }

    Set<ProductHardwareResponse> getProductHardwareResponses(Product product) {
        Set<ProductHardwareResponse> productResponses = new HashSet<>();

        product.getProductHardwares().forEach(productHardware -> {
            productResponses.add(ProductHardwareResponse.builder()
                    .id(productHardware.getId())
                    .value(productHardware.getValue())
                    .hardwareImgUrl(productHardware.getHardware().getImgUrl())
                    .build());
        });

        return productResponses;
    }

    Set<ProductImgResponse> getProductImgResponses(Product product) {
        Set<ProductImgResponse> productImgResponses = new HashSet<>();

        product.getProductImgs().forEach(productImg -> {
            productImgResponses.add(ProductImgResponse.builder()
                    .id(productImg.getId())
                    .imgUrl(productImg.getImgUrl())
                    .build());
        });

        return productImgResponses;
    }
}
