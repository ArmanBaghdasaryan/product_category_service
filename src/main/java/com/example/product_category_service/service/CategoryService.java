package com.example.product_category_service.service;

import com.example.product_category_service.entity.Category;
import com.example.product_category_service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }
}