package com.example.product_category_service.endpoint;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.example.product_category_service.entity.Category;
import com.example.product_category_service.entity.Product;
import com.example.product_category_service.entity.Role;
import com.example.product_category_service.entity.User;
import com.example.product_category_service.mapper.ProductMapper;
import com.example.product_category_service.service.ProductService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductEndpoint.class})
@ExtendWith(SpringExtension.class)
class ProductEndpointTest {
    @Autowired
    private ProductEndpoint productEndpoint;

    @MockBean
    private ProductMapper productMapper;

    @MockBean
    private ProductService productService;

    /**
     * Method under test: {@link ProductEndpoint#getProductsByCategory(int)}
     */
    @Test
    void testGetProductsByCategory() throws Exception {
        when(productService.getProductsByCategoryId(anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/byCategory/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productEndpoint)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link ProductEndpoint#getProductsByCategory(int)}
     */
    @Test
    void testGetProductsByCategory2() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("?");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("?");
        user.setPassword("iloveyou");
        user.setRole(Role.ADMIN);
        user.setSurname("Doe");

        Product product = new Product();
        product.setCategory(category);
        product.setCount(3);
        product.setId(1);
        product.setPrice(10.0d);
        product.setTitle("Dr");
        product.setUser(user);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productService.getProductsByCategoryId(anyInt())).thenReturn(productList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/byCategory/{id}", 1);
        MockMvcBuilders.standaloneSetup(productEndpoint)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"title\":\"Dr\",\"count\":3,\"price\":10.0,\"category\":{\"id\":1,\"name\":\"?\"},\"user\":{\"id\":1,\"name\":\"?"
                                        + "\",\"surname\":\"Doe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"role\":\"ADMIN\"}}]"));
    }
}

