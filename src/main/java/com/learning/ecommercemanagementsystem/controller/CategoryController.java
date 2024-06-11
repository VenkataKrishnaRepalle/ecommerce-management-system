package com.learning.ecommercemanagementsystem.controller;

import com.learning.ecommercemanagementsystem.dto.AddCategoryRequestDto;
import com.learning.ecommercemanagementsystem.dto.UpdateCategoryRequestDto;
import com.learning.ecommercemanagementsystem.entity.Category;
import com.learning.ecommercemanagementsystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(categoryService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody AddCategoryRequestDto categoryRequestDto) {
        return new ResponseEntity<>(categoryService.create(categoryRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> update(@PathVariable UUID categoryId, @RequestBody UpdateCategoryRequestDto categoryRequestDto) {
        return new ResponseEntity<>(categoryService.update(categoryId, categoryRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable UUID categoryId) {
        categoryService.delete(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
