package com.learning.ecommercemanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductRequestDto {

    private UUID uuid;

    private UUID categoryUuid;

    private String name;

    private BigDecimal price;

    private Long quantity;
}
