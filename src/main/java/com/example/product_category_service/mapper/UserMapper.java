package com.example.product_category_service.mapper;

import com.example.product_category_service.dto.CreateUserDto;
import com.example.product_category_service.dto.UserDto;
import com.example.product_category_service.dto.UserProductsDto;
import com.example.product_category_service.entity.User;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(CreateUserDto createUserDto);

    UserDto map(User user);

    UserProductsDto mapToProductsDto(User user);

    List<UserProductsDto> map(List<User> all);

}