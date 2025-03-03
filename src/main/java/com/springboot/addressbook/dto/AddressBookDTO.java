package com.springboot.addressbook.dto;

public class AddressBookDTO {
    private String name;

    public AddressBookDTO() {}

    public AddressBookDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

