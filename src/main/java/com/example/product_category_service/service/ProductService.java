package com.example.product_category_service.service;

import com.example.product_category_service.dto.CreateProductDto;
import com.example.product_category_service.dto.ProductResponseDto;
import com.example.product_category_service.entity.Product;
import com.example.product_category_service.mapper.ProductMapper;
import com.example.product_category_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(CreateProductDto createProductDto) {
       return productRepository.save(productMapper.map(createProductDto));
    }
    public Product update(ProductResponseDto productResponseDto) {
      return   productRepository.save(productMapper.map(productResponseDto));
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategoryId(int id) {
        return productRepository.findProductByCategoryId(id);
    }

    public Optional<Product> findById(int id) {
       return productRepository.findById(id);
    }
}
