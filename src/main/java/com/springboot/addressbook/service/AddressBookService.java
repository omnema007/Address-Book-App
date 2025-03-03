package com.springboot.addressbook.service;

import com.springboot.addressbook.dto.AddressBookDTO;
import org.springframework.stereotype.Service;
import com.springboot.addressbook.model.AddressBook;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {
    private final List<AddressBook> addressBookList = new ArrayList<>();

    public List<AddressBook> getAllEntries() {
        return addressBookList;
    }

    public AddressBook getEntryById(int id) {
        return addressBookList.stream()
                .filter(entry -> entry.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public String addEntry(int id, AddressBookDTO dto) {
        addressBookList.add(new AddressBook(id, dto.getName()));
        return "Entry added successfully";
    }

    public String updateEntry(int id, AddressBookDTO dto) {
        Optional<AddressBook> entryOptional = addressBookList.stream()
                .filter(entry -> entry.getId() == id)
                .findFirst();

        if (entryOptional.isPresent()) {
            entryOptional.get().setName(dto.getName());
            return "Entry updated successfully";
        }
        return "Entry not found";
    }

    public String deleteEntry(int id) {
        Optional<AddressBook> entryOptional = addressBookList.stream()
                .filter(entry -> entry.getId() == id)
                .findFirst();

        if (entryOptional.isPresent()) {
            addressBookList.remove(entryOptional.get());
            return "Entry deleted successfully";
        }
        return "Entry not found";
    }
}


