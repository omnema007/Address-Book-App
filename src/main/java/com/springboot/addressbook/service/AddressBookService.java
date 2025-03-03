package com.springboot.addressbook.service;

import com.springboot.addressbook.dto.AddressBookDTO;
import org.springframework.stereotype.Service;
import com.springboot.addressbook.model.AddressBook;
import java.util.HashMap;
import java.util.Map;


@Service
public class AddressBookService {
    private final Map<Integer, AddressBook> addressBook = new HashMap<>();

    public Map<Integer, AddressBook> getAllEntries() {
        return addressBook;
    }

    public AddressBook getEntryById(int id) {
        return addressBook.get(id);
    }

    public String addEntry(int id, AddressBookDTO dto) {
        addressBook.put(id, new AddressBook(id, dto.getName()));
        return "Entry added successfully";
    }

    public String updateEntry(int id, AddressBookDTO dto) {
        if (addressBook.containsKey(id)) {
            addressBook.get(id).setName(dto.getName());
            return "Entry updated successfully";
        }
        return "Entry not found";
    }

    public String deleteEntry(int id) {
        if (addressBook.containsKey(id)) {
            addressBook.remove(id);
            return "Entry deleted successfully";
        }
        return "Entry not found";
    }
}

