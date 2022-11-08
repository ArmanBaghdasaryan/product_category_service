package com.example.product_category_service.endpoint;

import com.example.product_category_service.dto.CreateUserDto;
import com.example.product_category_service.dto.UserAuthDto;
import com.example.product_category_service.dto.UserAuthResponseDto;
import com.example.product_category_service.entity.User;
import com.example.product_category_service.mapper.UserMapper;
import com.example.product_category_service.service.UserService;
import com.example.product_category_service.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserEndpoint {

    private final UserService userService;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;


    @PostMapping()
    public ResponseEntity<?> register(@RequestBody CreateUserDto createUserDto) {
        Optional<User> existingUser = userService.findByEmail(createUserDto.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User user = mapper.map(createUserDto);
        return ResponseEntity.ok(mapper.map(userService.register(user)));
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody UserAuthDto userAuthDto) {
        Optional<User> byEmail = userService.findByEmail(userAuthDto.getEmail());
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword())) {
                log.info("User with  username {} get auth token", user.getEmail());
                return ResponseEntity.ok(UserAuthResponseDto.builder()
                        .token(jwtTokenUtil.generateToken(user.getEmail()))
                        .user(mapper.map(user))
                        .build()
                );
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
