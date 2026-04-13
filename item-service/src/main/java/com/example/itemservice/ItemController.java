package com.example.itemservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    // Simple in-memory list (no database needed)
    private List<String> items = new ArrayList<>(List.of("Book", "Laptop", "Phone"));

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "item-service");
        response.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/items")
    public List<String> getItems() {
        logger.info("Fetching all items");
        return items;
    }

    @PostMapping("/items")
    public ResponseEntity<String> addItem(@RequestBody ItemRequest itemRequest) {
        String item = itemRequest.getName();
        if (item == null || item.isEmpty()) {
            logger.warn("Attempted to add an empty item");
            return ResponseEntity.badRequest().body("Item name is required");
        }
        items.add(item);
        logger.info("New item added: {}", item);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item added: " + item);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<String> getItem(@PathVariable int id) {
        logger.info("Fetching item with id: {}", id);
        if (id < 0 || id >= items.size()) {
            logger.error("Item not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items.get(id));
    }
}

class ItemRequest {
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
