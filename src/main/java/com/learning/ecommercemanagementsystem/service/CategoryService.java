package com.learning.ecommercemanagementsystem.service;

import com.learning.ecommercemanagementsystem.dto.AddCategoryRequestDto;
import com.learning.ecommercemanagementsystem.dto.UpdateCategoryRequestDto;
import com.learning.ecommercemanagementsystem.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAll();

    Category getById(UUID id);

    Category create(AddCategoryRequestDto categoryRequestDto);

    Category update(UUID categoryId, UpdateCategoryRequestDto categoryRequestDto);

    void delete(UUID categoryId);
}
