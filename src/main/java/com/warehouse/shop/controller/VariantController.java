package com.warehouse.shop.controller;

import com.warehouse.shop.dto.request.VariantRequest;
import com.warehouse.shop.dto.response.VariantResponse;
import com.warehouse.shop.entity.Variant;
import com.warehouse.shop.service.VariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/variants")
@RequiredArgsConstructor
public class VariantController {

    private final VariantService variantService;

    @PostMapping("/item/{itemId}")
    public VariantResponse create(
            @PathVariable Long itemId,
            @RequestBody @Valid VariantRequest request
    ) {
        Variant variant = new Variant();
        variant.setName(request.getName());
        variant.setPrice(request.getPrice());
        variant.setStock(request.getStock());

        Variant saved = variantService.create(itemId, variant);

        return mapToResponse(saved);
    }

    @GetMapping("/item/{itemId}")
    public List<VariantResponse> getByItem(@PathVariable Long itemId) {
        return variantService.getByItem(itemId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @PostMapping("/{id}/purchase")
    public VariantResponse purchase(
            @PathVariable Long id,
            @RequestParam Integer qty
    ) {
        Variant v = variantService.purchase(id, qty);
        return mapToResponse(v);
    }

    // helper untuk mapping response
    private VariantResponse mapToResponse(Variant v) {
        return VariantResponse.builder()
                .id(v.getId())
                .name(v.getName())
                .price(v.getPrice())
                .stock(v.getStock())
                .itemId(v.getItem().getId())
                .build();
    }
}