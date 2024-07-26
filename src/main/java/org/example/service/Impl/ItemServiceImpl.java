package org.example.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.entity.ItemEntity;
import org.example.model.Item;
import org.example.repository.ItemRepository;
import org.example.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    final ItemRepository repository;
    final ObjectMapper objectMapper;

    @Override
    public void addItem(Item item) {
        ItemEntity itemEntity = objectMapper.convertValue(item, ItemEntity.class);
        repository.save(itemEntity);
    }

    @Override
    public Item getItemById(Integer id) {
        Optional<ItemEntity> itemEntity = repository.findById(id);
        return itemEntity.map(entity -> objectMapper.convertValue(entity, Item.class)).orElse(null);
    }

    @Override
    public List<Item> getAllItems() {
        List<ItemEntity> entities = repository.findAll();
        return entities.stream().map(entity -> objectMapper.convertValue(entity, Item.class)).collect(Collectors.toList());
    }

    @Override
    public void updateItem(Integer id, Item item) {
        Optional<ItemEntity> optionalEntity = repository.findById(id);
        if (optionalEntity.isPresent()) {
            ItemEntity itemEntity = optionalEntity.get();
            itemEntity.setName(item.getName());
            itemEntity.setFine_per_day(item.getFine_per_day());
            itemEntity.setAvailability(item.getAvailability());
            itemEntity.setRental_per_day(item.getRental_per_day());
            repository.save(itemEntity);
        }
    }

    @Override
    public void deleteItem(Integer id) {
        repository.deleteById(id);
    }
}
