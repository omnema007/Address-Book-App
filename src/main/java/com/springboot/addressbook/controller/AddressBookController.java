package com.springboot.addressbook.controller;

import com.springboot.addressbook.dto.AddressBookDTO;
import com.springboot.addressbook.model.AddressBook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    private Map<Integer, AddressBook> addressBook = new HashMap<>();

    @GetMapping
    public ResponseEntity<Map<Integer, AddressBook>> getAllEntries() {
        return ResponseEntity.ok(addressBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBook> getEntryById(@PathVariable int id) {
        return addressBook.containsKey(id) ?
                ResponseEntity.ok(addressBook.get(id)) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> addEntry(@RequestParam int id, @RequestBody AddressBookDTO dto) {
        AddressBook entry = new AddressBook(id, dto.getName());
        addressBook.put(id, entry);
        return ResponseEntity.ok("Entry added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEntry(@PathVariable int id, @RequestBody AddressBookDTO dto) {
        if (addressBook.containsKey(id)) {
            addressBook.get(id).setName(dto.getName());
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


