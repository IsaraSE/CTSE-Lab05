package com.example.itemservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    // Simple in-memory list (no database needed)
    private List<String> items = new ArrayList<>(List.of("Book", "Laptop", "Phone"));

    @GetMapping
    public List<String> getItems() {
        return items;
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody ItemRequest itemRequest) {
        String item = itemRequest.getName();
        items.add(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item added: " + item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getItem(@PathVariable int id) {
        if (id < 0 || id >= items.size())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(items.get(id));
    }
}

class ItemRequest {
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
