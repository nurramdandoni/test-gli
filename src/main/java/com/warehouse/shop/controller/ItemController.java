package com.warehouse.shop.controller;

import com.warehouse.shop.dto.request.ItemRequest;
import com.warehouse.shop.dto.response.ItemResponse;
import com.warehouse.shop.entity.Item;
import com.warehouse.shop.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ItemResponse create(@RequestBody @Valid ItemRequest request) {
        Item item = new Item();
        item.setName(request.getName());
        item.setDescription(request.getDescription());

        Item saved = itemService.create(item);

        return mapToResponse(saved);
    }

    @GetMapping
    public List<ItemResponse> getAll() {
        return itemService.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ItemResponse getById(@PathVariable Long id) {
        Item item = itemService.findById(id);
        return mapToResponse(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        itemService.findById(id);
        itemService.delete(id);
    }

    // helper mapping
    private ItemResponse mapToResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .build();
    }
}