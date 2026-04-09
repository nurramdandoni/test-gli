package com.warehouse.shop.controller;

import com.warehouse.shop.entity.Variant;
import com.warehouse.shop.service.VariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/variants")
@RequiredArgsConstructor
public class VariantController {
    private final VariantService variantService;

    @PostMapping("/item/{itemId}")
    public Variant create(@PathVariable Long itemId, @RequestBody Variant variant) {
        return variantService.create(itemId, variant);
    }

    @GetMapping("/item/{itemId}")
    public List<Variant> getByItem(@PathVariable Long itemId) {
        return variantService.getByItem(itemId);
    }

    @PostMapping("/{id}/purchase")
    public Variant purchase(@PathVariable Long id, @RequestParam Integer qty) {
        return variantService.purchase(id, qty);
    }
}
