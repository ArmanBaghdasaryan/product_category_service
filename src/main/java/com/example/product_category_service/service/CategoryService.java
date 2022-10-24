package com.example.product_category_service.service;

import com.example.product_category_service.dto.CategoryResponseDto;
import com.example.product_category_service.dto.CreateCategoryDto;
import com.example.product_category_service.entity.Category;
import com.example.product_category_service.mapper.CategoryMapper;
import com.example.product_category_service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    public Category save(CreateCategoryDto createCategoryDto) {
        return categoryRepository.save(categoryMapper.map(createCategoryDto));
    }
    public Category update(CategoryResponseDto categoryResponseDto) {
        return categoryRepository.save(categoryMapper.map(categoryResponseDto));
    }
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }
}
