package com.warehouse.shop.service;
import com.warehouse.shop.entity.Item;
import com.warehouse.shop.entity.Variant;
import com.warehouse.shop.repository.ItemRepository;


import com.warehouse.shop.repository.VariantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VariantService {
    private final VariantRepository variantRepository;
    private final ItemRepository itemRepository;

    // create variant
    public Variant create(Long itemId, Variant variant) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        variant.setItem(item);
        return variantRepository.save(variant);
    }

    // variant by item
    public List<Variant> getByItem(Long itemId) {
        return variantRepository.findAll()
                .stream()
                .filter(v -> v.getItem().getId().equals(itemId))
                .toList();
    }

    // purchase variant
    @Transactional
    public Variant purchase(Long variantId, Integer quantity) {
        Variant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        if (variant.getStock() < quantity) {
            throw new RuntimeException("Out of stock");
        }

        variant.setStock(variant.getStock() - quantity);

        return variantRepository.save(variant);
    }
}
