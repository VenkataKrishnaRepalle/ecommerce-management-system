package com.learning.ecommercemanagementsystem.service.impl;

import com.learning.ecommercemanagementsystem.dto.AddCategoryRequestDto;
import com.learning.ecommercemanagementsystem.dto.UpdateCategoryRequestDto;
import com.learning.ecommercemanagementsystem.entity.Category;
import com.learning.ecommercemanagementsystem.exception.FoundException;
import com.learning.ecommercemanagementsystem.exception.InvalidInputException;
import com.learning.ecommercemanagementsystem.exception.NotFoundException;
import com.learning.ecommercemanagementsystem.repository.CategoryRepository;
import com.learning.ecommercemanagementsystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CATEGORY_NOT_FOUND", "Category not found with id: " + id));
    }

    @Override
    public Category create(AddCategoryRequestDto categoryRequestDto) {
        var category = categoryRepository.findByName(categoryRequestDto.getName().strip());

        if (category != null) {
            throw new FoundException("CATEGORY_FOUND", "Category already exists with name: " + categoryRequestDto.getName());
        }

        return categoryRepository.save(Category.builder()
                .name(categoryRequestDto.getName().strip())
                .build());
    }

    @Override
    public Category update(UUID categoryId, UpdateCategoryRequestDto categoryRequestDto) {
        if (!categoryId.equals(categoryRequestDto.getUuid())) {
            throw new InvalidInputException("INVALID_CATEGORY_INPUT", "Invalid CategoryId input");
        }

        var category = getById(categoryId);
        category.setName(categoryRequestDto.getName().strip());

        return categoryRepository.save(category);
    }

    @Override
    public void delete(UUID categoryId) {
        getById(categoryId);
        categoryRepository.deleteById(categoryId);

        if (categoryRepository.existsById(categoryId)) {
            throw new FoundException("CATEGORY_NOT_DELETED", "Category not deleted with id: " + categoryId);
        }
    }
}
