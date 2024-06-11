package com.learning.ecommercemanagementsystem.repository;

import com.learning.ecommercemanagementsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Category findByName(String name);
}
