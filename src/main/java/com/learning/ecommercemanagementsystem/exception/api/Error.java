package com.learning.ecommercemanagementsystem.exception.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {
    private String code;
    private String message;
}
