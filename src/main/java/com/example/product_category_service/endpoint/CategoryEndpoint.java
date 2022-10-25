package com.example.product_category_service.endpoint;

import com.example.product_category_service.dto.CategoryResponseDto;
import com.example.product_category_service.dto.CreateCategoryDto;
import com.example.product_category_service.entity.Category;
import com.example.product_category_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryEndpoint {

    private final CategoryService categoryService;


    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        return ResponseEntity.ok(categoryService.save(createCategoryDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") int id,
                                            @RequestBody CategoryResponseDto categoryDto) {
        Optional<Category> byId = categoryService.findCategoryById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(categoryService.update(categoryDto));
    }

}
