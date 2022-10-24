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
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @PostMapping()
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        categoryService.save(createCategoryDto);
        return ResponseEntity.ok(createCategoryDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") int id,
                                            @RequestBody CategoryResponseDto categoryDto) {
        Optional<Category> byId = categoryService.findCategoryById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        categoryService.update(categoryDto);
        return ResponseEntity.ok(categoryDto);
    }

}
