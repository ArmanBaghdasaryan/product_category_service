package com.example.product_category_service.service;

import com.example.product_category_service.entity.Category;
import com.example.product_category_service.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CategoryServiceTest {

    @MockBean
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @Test
    void findAll() {
        categoryService.findAll();
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void save() {
        Category category = Category.builder()
                .id(1)
                .name("name").build();
        when(categoryRepository.save(any())).thenReturn(category);
        categoryService.save(Category.builder()
                .name("name")
                .build());
        verify(categoryRepository, times(1)).save(any());

    }

    @Test
    void update() {
        Category category = Category.builder()
                .id(1)
                .name("name").build();
        when(categoryRepository.save(any())).thenReturn(category);
        Category categoryForUpdate = Category.builder()
                .id(1)
                .name("name_update").build();
        assertSame(categoryService.update(categoryForUpdate), category);
        verify(categoryRepository, times(1)).save(any());

    }

    @Test
    void deleteById() {
        Category category = Category.builder()
                .id(1)
                .name("name").build();
        categoryService.deleteById(category.getId());
        verify(categoryRepository, times(1)).deleteById(any());
    }

    @Test
    void findCategoryById() {
        Category category = Category.builder()
                .id(1)
                .name("name").build();
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        categoryService.findCategoryById(category.getId());
        verify(categoryRepository).findById(any());
    }

}