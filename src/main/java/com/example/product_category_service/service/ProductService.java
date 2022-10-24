package com.example.product_category_service.service;

import com.example.product_category_service.dto.CreateProductDto;
import com.example.product_category_service.dto.ProductResponseDto;
import com.example.product_category_service.entity.Product;
import com.example.product_category_service.mapper.ProductMapper;
import com.example.product_category_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void save(CreateProductDto createProductDto) {
        productRepository.save(productMapper.map(createProductDto));
    }
    public void update(ProductResponseDto productResponseDto) {
        productRepository.save(productMapper.map(productResponseDto));
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategoryId(int id) {
        return productRepository.findProductByCategoryId(id);
    }
}
