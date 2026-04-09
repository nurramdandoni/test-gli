package com.warehouse.shop.service;

import com.warehouse.shop.entity.Item;
import com.warehouse.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    // create item
    public Item create(Item item) {
        return itemRepository.save(item);
    }

    // get all items
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    // get item by id
    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    // delete item
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}
