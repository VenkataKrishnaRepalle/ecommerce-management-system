package com.learning.ecommercemanagementsystem.service.impl;

import com.learning.ecommercemanagementsystem.dto.CreateOrderRequestDto;
import com.learning.ecommercemanagementsystem.dto.SuccessResponseDto;
import com.learning.ecommercemanagementsystem.entity.Order;
import com.learning.ecommercemanagementsystem.entity.OrderProduct;
import com.learning.ecommercemanagementsystem.entity.OrderStatus;
import com.learning.ecommercemanagementsystem.entity.Product;
import com.learning.ecommercemanagementsystem.exception.NotFoundException;
import com.learning.ecommercemanagementsystem.repository.OrderProductRepository;
import com.learning.ecommercemanagementsystem.repository.OrderRepository;
import com.learning.ecommercemanagementsystem.repository.ProductRepository;
import com.learning.ecommercemanagementsystem.service.OrderService;
import com.learning.ecommercemanagementsystem.service.ProductService;
import com.learning.ecommercemanagementsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserService userService;

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("ORDER_NOT_FOUND", "Order not found with id: " + orderId));
    }

    @Override
    public Order create(CreateOrderRequestDto orderRequestDto) {
        var user = userService.getById(orderRequestDto.getUserUuid());
        orderRequestDto.getProducts().forEach(productRequestDto -> {
            var product = productService.getById(productRequestDto.getUuid());
            if (product.getQuantity() < productRequestDto.getQuantity()) {
                throw new NotFoundException("PRODUCT_NOT_ENOUGH_QUANTITY", "Product " + product.getName() + " has quantity only " + product.getQuantity());
            }
        });


        var order = orderRepository.save(Order.builder()
                .user(user)
                .orderStatus(OrderStatus.PLACED)
                .build());

        List<OrderProduct> orderProducts = new ArrayList<>();
        orderRequestDto.getProducts().forEach(productRequestDto -> {
            var product = productService.getById(productRequestDto.getUuid());
            product.setQuantity(product.getQuantity() - productRequestDto.getQuantity());
            productRepository.save(product);
            var orderProduct = OrderProduct.builder()
                    .product(product)
                    .quantity(productRequestDto.getQuantity())
                    .build();
            orderProducts.add(orderProduct);
        });
        order.setOrderProducts(orderProducts);
        orderRepository.save(order);
        orderProducts.forEach(orderProduct -> orderProduct.setOrder(order));
        orderProductRepository.saveAll(orderProducts);

        return order;
    }

    @Override
    public SuccessResponseDto cancelOrder(UUID orderId) {
        var order = getById(orderId);

        order.getOrderProducts().forEach(orderProduct -> {
            var product = orderProduct.getProduct();
            product.setQuantity(product.getQuantity() + orderProduct.getQuantity());
            productRepository.save(product);
        });
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

        return SuccessResponseDto.builder()
                .success(true)
                .message("Order Cancelled Successfully")
                .build();
    }
}
