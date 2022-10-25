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
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody CreateProductDto createProductDto) {
        return ResponseEntity.ok(productService.save(createProductDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id,
                                                 @RequestBody ProductResponseDto productDto) {
        Optional<Product> byId = productService.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.update(productDto));
    }

    @GetMapping("/byCategory/{id}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("id") int id) {
        if (productService.getProductsByCategoryId(id).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productService.getProductsByCategoryId(id));
    }


}
