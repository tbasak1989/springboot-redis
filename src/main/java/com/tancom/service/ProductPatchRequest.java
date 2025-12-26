package com.tancom.service;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductPatchRequest {

    @Positive
    private BigDecimal price;

    @PositiveOrZero
    private Integer quantity;

    private String description;

    // getters & setters
}

