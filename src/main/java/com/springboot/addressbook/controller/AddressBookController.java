package com.springboot.addressbook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    private Map<Integer, String> addressBook = new HashMap<>();

    @GetMapping
    public ResponseEntity<Map<Integer, String>> getAllEntries() {
        return ResponseEntity.ok(addressBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getEntryById(@PathVariable int id) {
        return addressBook.containsKey(id) ?
                ResponseEntity.ok(addressBook.get(id)) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> addEntry(@RequestParam int id, @RequestParam String name) {
        addressBook.put(id, name);
        return ResponseEntity.ok("Entry added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEntry(@PathVariable int id, @RequestParam String name) {
        if (addressBook.containsKey(id)) {
            addressBook.put(id, name);
            return ResponseEntity.ok("Entry updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable int id) {
        if (addressBook.containsKey(id)) {
            addressBook.remove(id);
            return ResponseEntity.ok("Entry deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}

