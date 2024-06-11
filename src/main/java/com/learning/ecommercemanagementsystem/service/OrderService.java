package com.learning.ecommercemanagementsystem.service;

import com.learning.ecommercemanagementsystem.dto.CreateOrderRequestDto;
import com.learning.ecommercemanagementsystem.dto.SuccessResponseDto;
import com.learning.ecommercemanagementsystem.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<Order> getAll();

    Order getById(UUID orderId);

    Order create(CreateOrderRequestDto orderRequestDto);

    SuccessResponseDto cancelOrder(UUID orderId);
}
