package com.learning.ecommercemanagementsystem.repository;

import com.learning.ecommercemanagementsystem.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
}
