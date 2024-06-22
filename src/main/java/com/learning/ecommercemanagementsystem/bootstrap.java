package com.learning.ecommercemanagementsystem;

import com.learning.ecommercemanagementsystem.entity.Category;
import com.learning.ecommercemanagementsystem.entity.Product;
import com.learning.ecommercemanagementsystem.entity.User;
import com.learning.ecommercemanagementsystem.repository.CategoryRepository;
import com.learning.ecommercemanagementsystem.repository.ProductRepository;
import com.learning.ecommercemanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class bootstrap implements CommandLineRunner {

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(User.builder()
                .uuid(UUID.fromString("7093e8b6-8e6a-44d9-b75d-9c5338e4a309"))
                .firstName("venky")
                .lastName("krishna")
                .email("rvkrishna13052001@gmail.com")
                .build());

        var category = categoryRepository.save(Category.builder()
                .uuid(UUID.fromString("1d3e1ec2-32bd-47a9-8496-8731010a5cd4"))
                .name("Mobile")
                .build());

        productRepository.save(Product.builder()
                .uuid(UUID.fromString("fa8aede5-9f78-4c6b-8da4-4a22948eee92"))
                .category(category)
                .name("One Plus Nord CE")
                .price(BigDecimal.valueOf(1000))
                .quantity(10L)
                .build());

        productRepository.save(Product.builder()
                .uuid(UUID.fromString("7a827fdf-6a94-4a7e-a210-e1b89636aa6e"))
                .category(category)
                .name("One Plus Nord CE 2")
                .price(BigDecimal.valueOf(1000))
                .quantity(10L)
                .build());
    }
}
