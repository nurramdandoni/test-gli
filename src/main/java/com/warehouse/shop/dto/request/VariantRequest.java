package com.warehouse.shop.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class VariantRequest {
    @NotBlank
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    @Min(0)
    private Integer stock;
}
