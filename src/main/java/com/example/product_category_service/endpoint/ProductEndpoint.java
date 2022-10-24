package com.example.product_category_service.endpoint;

import com.example.product_category_service.dto.CreateProductDto;
import com.example.product_category_service.dto.ProductResponseDto;
import com.example.product_category_service.entity.Product;
import com.example.product_category_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductEndpoint {

    private final ProductService productService;


    @GetMapping("/product")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping("/product")
    public ResponseEntity<?> addNewProduct(@RequestBody CreateProductDto createProductDto) {
        productService.save(createProductDto);
        return ResponseEntity.ok(createProductDto);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/product")
    public ResponseEntity<?> updateProduct(@RequestBody ProductResponseDto productDto) {
        if (productDto.getId() == 0) {
            return ResponseEntity.badRequest().build();
        }
        productService.update(productDto);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/byCategory/{id}")
    public List<Product> getProductsByCategory(@PathVariable("id") int id) {
        return productService.getProductsByCategoryId(id);
    }

}
