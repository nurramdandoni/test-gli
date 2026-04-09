package com.warehouse.shop.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class VariantResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private Long itemId;
}
