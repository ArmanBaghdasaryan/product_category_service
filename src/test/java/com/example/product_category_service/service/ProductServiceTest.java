package com.example.product_category_service.service;

import com.example.product_category_service.entity.Category;
import com.example.product_category_service.entity.Product;
import com.example.product_category_service.entity.Role;
import com.example.product_category_service.entity.User;
import com.example.product_category_service.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductServiceTest {


    @Autowired
    ProductService productService;
    @MockBean
    private ProductRepository productRepository;


    @Test
    void findAll() {
        productService.findAll();
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void save() {
        when(productRepository.save(any())).thenReturn(product());
        productService.save(Product.builder()
                .title("Tv")
                .price(15.55)
                .count(1)
                .category(new Category())
                .user(new User())
                .build());
        verify(productRepository, times(1)).save(any());

    }

    @Test
    void saveNull() {
        when(productRepository.save(any())).thenReturn(product());
        assertThrows(RuntimeException.class, () -> {
            productService.save(null);

        });
        verify(productRepository, times(0)).save(any());
    }

    @Test
    void update() {
        Product product = new Product();
        product.setCategory(new Category(1, "name"));
        product.setCount(3);
        product.setId(1);
        product.setPrice(10.0d);
        product.setTitle("watch");
        product.setUser(new User(1, "Name", "Sur", "a@mail.com", "123", Role.ADMIN));
        when(productRepository.save(any())).thenReturn(product);
        Product productForUpdate = new Product();
        product.setCategory(new Category(2, "name"));
        product.setCount(3);
        product.setPrice(10.0d);
        product.setTitle("tv");
        product.setUser(new User(3, "Name", "Sur", "a@mail.com", "123", Role.ADMIN));
        assertSame(product, productService.update(productForUpdate,product.getId()));
        verify(productRepository).save(any());

    }

    @Test
    void deleteById() {
        productService.deleteById(product().getId());
        verify(productRepository, times(1)).deleteById(any());
    }

    @Test
    void getProductsByCategoryId() {
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findProductByCategoryId(anyInt())).thenReturn(productList);
        productService.getProductsByCategoryId(product().getCategory().getId());
        verify(productRepository, times(1)).findProductByCategoryId(anyInt());
    }


    @Test
    void findById() {
        when(productRepository.findById(any())).thenReturn(Optional.of(product()));
        productService.findById(product().getId());
        verify(productRepository).findById(any());
    }

    public Product product() {
        return Product.builder()
                .id(1)
                .title("Tv")
                .price(15.55)
                .count(1)
                .category(new Category(1, "name"))
                .user(new User(1, "Name", "Sur", "a@mail.com", "123", Role.ADMIN))
                .build();
    }

}