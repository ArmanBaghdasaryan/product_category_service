package com.example.product_category_service.service;

import com.example.product_category_service.entity.Product;
import com.example.product_category_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(int productId) {
        productRepository.deleteById(productId);
    }

    public List<Product> getProductsByCategoryId(int id) {
        return productRepository.findProductByCategoryId(id);
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }
}
