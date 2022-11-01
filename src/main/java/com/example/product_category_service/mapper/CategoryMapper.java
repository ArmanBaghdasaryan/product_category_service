package com.example.product_category_service.mapper;

import com.example.product_category_service.dto.CategoryResponseDto;
import com.example.product_category_service.dto.CreateCategoryDto;
import com.example.product_category_service.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category map(CreateCategoryDto createCategoryDto);

    Category map(CategoryResponseDto categoryDto);

    CreateCategoryDto map(Category category);

    List<CategoryResponseDto> map(List<Category> products);

}



