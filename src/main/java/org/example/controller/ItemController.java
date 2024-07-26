package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.Item;
import org.example.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin
@RequiredArgsConstructor
public class ItemController {

    final ItemService service;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody Item item){
        service.addItem(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Integer id) {
        Item item = service.getItemById(id);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public List<Item> getAllItems() {
        return service.getAllItems();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(@PathVariable Integer id, @RequestBody Item item) {
        service.updateItem(id, item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Integer id) {
        service.deleteItem(id);
    }
}
