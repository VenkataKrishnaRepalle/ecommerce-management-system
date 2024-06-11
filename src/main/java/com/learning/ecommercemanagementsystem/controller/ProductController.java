package com.learning.ecommercemanagementsystem.controller;

import com.learning.ecommercemanagementsystem.dto.AddProductRequestDto;
import com.learning.ecommercemanagementsystem.dto.UpdateProductRequestDto;
import com.learning.ecommercemanagementsystem.entity.Product;
import com.learning.ecommercemanagementsystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getById(@PathVariable UUID productId) {
        return new ResponseEntity<>(productService.getById(productId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody AddProductRequestDto productRequestDto) {
        return new ResponseEntity<>(productService.create(productRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> update(@PathVariable UUID productId, @RequestBody UpdateProductRequestDto productRequestDto) {
        return new ResponseEntity<>(productService.update(productId, productRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable UUID productId) {
        productService.delete(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
