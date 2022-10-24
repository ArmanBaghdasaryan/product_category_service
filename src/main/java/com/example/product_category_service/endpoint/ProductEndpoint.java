package com.example.product_category_service.endpoint;

import com.example.product_category_service.dto.CreateProductDto;
import com.example.product_category_service.dto.ProductResponseDto;
import com.example.product_category_service.entity.Product;
import com.example.product_category_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductEndpoint {

    private final ProductService productService;


    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping()
    public ResponseEntity<?> addNewProduct(@RequestBody CreateProductDto createProductDto) {
        productService.save(createProductDto);
        return ResponseEntity.ok(createProductDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") int id,
                                           @RequestBody ProductResponseDto productDto) {
        Optional<Product> byId = productService.findById(id);
        if (byId.isEmpty()) {
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
