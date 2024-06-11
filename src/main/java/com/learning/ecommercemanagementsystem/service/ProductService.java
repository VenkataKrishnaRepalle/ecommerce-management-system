package com.learning.ecommercemanagementsystem.service;

import com.learning.ecommercemanagementsystem.dto.AddProductRequestDto;
import com.learning.ecommercemanagementsystem.dto.UpdateProductRequestDto;
import com.learning.ecommercemanagementsystem.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getAll();

    Product getById(UUID productId);

    Product create(AddProductRequestDto productRequestDto);

    Product update(UUID productId, UpdateProductRequestDto productRequestDto);

    void delete(UUID productId);
}
