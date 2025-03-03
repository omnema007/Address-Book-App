package com.springboot.addressbook.controller;

import com.springboot.addressbook.dto.AddressBookDTO;
import com.springboot.addressbook.model.AddressBook;
import org.springframework.http.ResponseEntity;
import com.springboot.addressbook.service.AddressBookService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    private final AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @GetMapping
    public ResponseEntity<Map<Integer, AddressBook>> getAllEntries() {
        return ResponseEntity.ok(addressBookService.getAllEntries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBook> getEntryById(@PathVariable int id) {
        AddressBook entry = addressBookService.getEntryById(id);
        return entry != null ? ResponseEntity.ok(entry) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> addEntry(@RequestParam int id, @RequestBody AddressBookDTO dto) {
        return ResponseEntity.ok(addressBookService.addEntry(id, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEntry(@PathVariable int id, @RequestBody AddressBookDTO dto) {
        String response = addressBookService.updateEntry(id, dto);
        return response.equals("Entry not found") ? ResponseEntity.notFound().build() : ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable int id) {
        String response = addressBookService.deleteEntry(id);
        return response.equals("Entry not found") ? ResponseEntity.notFound().build() : ResponseEntity.ok(response);
    }
}



