package com.tancom.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    //@NotBlank
    private String name;

    private String description;

    //@NotNull
    //@DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal price;

    //@NotNull
    //@Min(0)
    private Integer quantity;

    // Getters and Setters
}
