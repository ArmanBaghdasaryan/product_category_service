package com.example.product_category_service.mapper;

import com.example.product_category_service.dto.CreateProductDto;
import com.example.product_category_service.dto.ProductResponseDto;
import com.example.product_category_service.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product map(CreateProductDto createProductdto);

    Product map(ProductResponseDto productDto);
}



