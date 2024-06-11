package com.learning.ecommercemanagementsystem.repository;

import com.learning.ecommercemanagementsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Boolean existsByName(String name);
}
