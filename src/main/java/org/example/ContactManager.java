package org.example;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactManager {
    Map<String, Contact> contactMap = new ConcurrentHashMap<>();


    public void addContact(String firstName, String lastName, String contactNo) {
        Contact contact = new Contact(firstName, lastName, contactNo);
        validateContact(contact);
        checkIfContactAlreadyExist(contact);
        contactMap.put(generateKey(contact), contact);
        System.out.println(contactMap);
    }

    public Collection<Contact> getAllContacts() {
        return contactMap.values();
    }

    private void checkIfContactAlreadyExist(Contact contact) {
        if (contactMap.containsKey(generateKey(contact))) {
            throw new RuntimeException("contact already exist");
        }
    }


    private void validateContact(Contact contact) {
        contact.validateFirstName();
        contact.validateLastName();
        contact.validateContactNo();
    }

    private String generateKey(Contact contact) {

        return String.format("%s-%s", contact.getFirstName(), contact.getLastName());
    }
}
