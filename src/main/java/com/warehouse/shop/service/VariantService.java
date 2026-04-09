package com.warehouse.shop.service;

import com.warehouse.shop.entity.Item;
import com.warehouse.shop.entity.Variant;
import com.warehouse.shop.exception.BadRequestException;
import com.warehouse.shop.exception.NotFoundException;
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
                .orElseThrow(() -> new NotFoundException("Item not found"));

        variant.setItem(item);
        return variantRepository.save(variant);
    }

    // get variant by item
    public List<Variant> getByItem(Long itemId) {
        itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item not found"));

        return variantRepository.findAll()
                .stream()
                .filter(v -> v.getItem().getId().equals(itemId))
                .toList();
    }

    // purchase variant
    @Transactional
    public Variant purchase(Long variantId, Integer quantity) {
        Variant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new NotFoundException("Variant not found"));

        if (quantity == null || quantity <= 0) {
            throw new BadRequestException("Quantity must be greater than 0");
        }

        if (variant.getStock() < quantity) {
            throw new BadRequestException("Out of stock");
        }

        variant.setStock(variant.getStock() - quantity);

        return variantRepository.save(variant);
    }
}