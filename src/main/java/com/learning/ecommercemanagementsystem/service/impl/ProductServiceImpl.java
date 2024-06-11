package com.learning.ecommercemanagementsystem.service.impl;

import com.learning.ecommercemanagementsystem.dto.AddProductRequestDto;
import com.learning.ecommercemanagementsystem.dto.UpdateProductRequestDto;
import com.learning.ecommercemanagementsystem.entity.Product;
import com.learning.ecommercemanagementsystem.exception.FoundException;
import com.learning.ecommercemanagementsystem.exception.InvalidInputException;
import com.learning.ecommercemanagementsystem.exception.NotFoundException;
import com.learning.ecommercemanagementsystem.repository.ProductRepository;
import com.learning.ecommercemanagementsystem.service.CategoryService;
import com.learning.ecommercemanagementsystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("PRODUCT_NOT_FOUND", "Product not found with id: " + productId));
    }

    @Override
    public Product create(AddProductRequestDto productRequestDto) {
        if (productRepository.existsByName(productRequestDto.getName())) {
            throw new FoundException("PRODUCT_FOUND", "Product already exists with name: " + productRequestDto.getName());
        }

        var category = categoryService.getById(productRequestDto.getCategoryUuid());

        return productRepository.save(Product.builder()
                .category(category)
                .name(productRequestDto.getName().strip())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .build());
    }

    @Override
    public Product update(UUID productId, UpdateProductRequestDto productRequestDto) {
        if (!productId.equals(productRequestDto.getUuid())) {
            throw new InvalidInputException("INVALID_PRODUCT_INPUT", "Invalid productId input");
        }
        var product = getById(productId);
        var category = categoryService.getById(productRequestDto.getCategoryUuid());
        product.setCategory(category);
        product.setName(productRequestDto.getName().strip());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());

        return productRepository.save(product);
    }

    @Override
    public void delete(UUID productId) {
        var product = getById(productId);
        product.getCategory().getProducts().remove(product);

        productRepository.deleteById(productId);

        if (productRepository.existsById(productId)) {
            throw new FoundException("PRODUCT_NOT_DELETED", "Product not deleted with id: " + productId);
        }
    }
}
