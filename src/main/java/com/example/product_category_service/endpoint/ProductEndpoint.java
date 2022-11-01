package com.example.product_category_service.endpoint;

import com.example.product_category_service.dto.CreateProductDto;
import com.example.product_category_service.dto.ProductResponseDto;
import com.example.product_category_service.entity.Product;
import com.example.product_category_service.entity.User;
import com.example.product_category_service.mapper.ProductMapper;
import com.example.product_category_service.security.CurrentUser;
import com.example.product_category_service.security.CurrentUserDetailServiceImpl;
import com.example.product_category_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductEndpoint {

    private final ProductService productService;
    private final ProductMapper productMapper;

    private final CurrentUserDetailServiceImpl userDetailService;


    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productMapper.map(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<Product> addNewProduct(@RequestBody CreateProductDto createProductDto) {
        return ResponseEntity.ok(productService.save(productMapper.map(createProductDto)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        User loggedInUser = userDetailService.getLoggedInUser();
        Optional<Product> byId = productService.findById(id);
        if (byId.isPresent()) {
            Product product = byId.get();
            if (product.getUser().getId() == loggedInUser.getId()) {
                productService.deleteById(id);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductResponseDto productResponseDto,
                                           @PathVariable("id") int id) {
        Optional<Product> byId = productService.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok((productService.update(productMapper.map(productResponseDto))));
    }

    @GetMapping("/byCategory/{id}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("id") int id) {
        if (productService.getProductsByCategoryId(id).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productService.getProductsByCategoryId(id));
    }


}
