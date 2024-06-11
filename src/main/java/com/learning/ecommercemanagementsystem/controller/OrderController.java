package com.learning.ecommercemanagementsystem.controller;

import com.learning.ecommercemanagementsystem.dto.CreateOrderRequestDto;
import com.learning.ecommercemanagementsystem.dto.SuccessResponseDto;
import com.learning.ecommercemanagementsystem.entity.Order;
import com.learning.ecommercemanagementsystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Order>> getAll() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getById(@PathVariable UUID orderId) {
        return new ResponseEntity<>(orderService.getById(orderId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody CreateOrderRequestDto orderRequestDto) {
        return new ResponseEntity<>(orderService.create(orderRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<SuccessResponseDto> cancelOrder(@PathVariable UUID orderId) {
        return new ResponseEntity<>(orderService.cancelOrder(orderId), HttpStatus.OK);
    }
}
