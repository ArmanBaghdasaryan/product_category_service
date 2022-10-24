package com.example.product_category_service.endpoint;

import com.example.product_category_service.dto.CategoryResponseDto;
import com.example.product_category_service.dto.CreateCategoryDto;
import com.example.product_category_service.entity.Category;
import com.example.product_category_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryEndpoint {

    private final CategoryService categoryService;


    @GetMapping("/category")
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        categoryService.save(createCategoryDto);
        return ResponseEntity.ok(createCategoryDto);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/category")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryResponseDto categoryDto) {
        if (categoryDto.getId() == 0) {
            return ResponseEntity.badRequest().build();
        }
        categoryService.update(categoryDto);
        return ResponseEntity.ok(categoryDto);
    }

}
