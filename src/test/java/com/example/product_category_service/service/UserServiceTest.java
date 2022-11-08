package com.example.product_category_service.service;

import com.example.product_category_service.entity.Role;
import com.example.product_category_service.entity.User;
import com.example.product_category_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    void register() {
        User user = User.builder()
                .id(1)
                .name("name")
                .role(Role.USER)
                .password("Arman")
                .email("email@.Email")
                .build();
        when(userRepository.save(any())).thenReturn(user);
        userService.register(User.builder()
                .name("name")
                .role(Role.USER)
                .password("Arman")
                .email("email@.Email")
                .build());
        verify(userRepository, times(1)).save(any());

    }

    @Test
    void findByEmail() {
        User user = User.builder()
                .id(1)
                .email("a@mail.com")
                .surname("jenew")
                .build();
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));
        userService.findByEmail(user.getEmail());
        verify(userRepository).findByEmail(any());
    }
}
