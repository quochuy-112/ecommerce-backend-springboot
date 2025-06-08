package org.example.projectgt.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.TopProductCreationRequest;
import org.example.projectgt.dto.request.TopProductUpdateRequest;
import org.example.projectgt.dto.response.TopProductResponse;
import org.example.projectgt.entity.Product;
import org.example.projectgt.entity.TopProduct;
import org.example.projectgt.mapper.TopProductMapper;
import org.example.projectgt.repository.TopProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TopProductService {
    TopProductRepository topProductRepository;
    TopProductMapper topProductMapper;

    public TopProductResponse createRequest(TopProductCreationRequest request){
        if(topProductRepository.existsByName(request.getName()))
            throw new RuntimeException("Product name already exists");

        TopProduct topProduct = topProductMapper.toTopProduct(request);

        return topProductMapper.toTopProductResponse(topProductRepository.save(topProduct));
    }

    public List<TopProductResponse> getAllTopProducts(){
        return topProductRepository.findAll()
                .stream()
                .map(topProductMapper::toTopProductResponse)
                .toList();
    }

    public TopProductResponse getTopProductById(String id){
        return topProductMapper.toTopProductResponse(topProductRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Email not found")));
    }

    public TopProductResponse updateTopProduct(String id, TopProductUpdateRequest request){
        TopProduct topProduct = topProductRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Email not found"));

        topProductMapper.updateTopProduct(topProduct, request);

        return topProductMapper.toTopProductResponse(topProductRepository.save(topProduct));
    }

    @Transactional
    public void deleteTopProduct(String id){
        topProductRepository.deleteById(id);
    }
}
