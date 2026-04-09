package com.warehouse.shop.service;

import com.warehouse.shop.entity.Item;
import com.warehouse.shop.exception.NotFoundException;
import com.warehouse.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found"));
    }

    public void delete(Long id) {
        Item item = findById(id);
        itemRepository.delete(item);
    }
}