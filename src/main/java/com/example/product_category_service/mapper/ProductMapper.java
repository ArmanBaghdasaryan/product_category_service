package com.example.product_category_service.mapper;

import com.example.product_category_service.dto.CreateProductDto;
import com.example.product_category_service.dto.ProductResponseDto;
import com.example.product_category_service.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product map(CreateProductDto createProductdto);

    Product map(ProductResponseDto productDto);

    List<ProductResponseDto> map(List<Product> products);


}



