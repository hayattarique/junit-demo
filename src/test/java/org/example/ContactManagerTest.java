package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
public class ContactManagerTest {


    private ContactManager manager;

    @BeforeEach
    void setup() {
        manager = new ContactManager();
    }

    @Test
    @DisplayName("creating contact successfully")
    void createContact() {
        manager.addContact("Hayat", "Tariq", "7979797800");
        assertEquals(1, manager.getAllContacts().size());
        assertFalse(manager.getAllContacts().isEmpty());
    }

    @Test
    @DisplayName("throwing exception if firstName is empty")
    void throwExceptionIfFirstNameIsEmpty() {

        assertThrows(RuntimeException.class, () -> {
            manager.addContact(null, "shahan", "7979797811");
        });
    }


    @Test
    @DisplayName("throwing exception if lastName is empty")
    void throwExceptionIfLastNameIsEmpty() {
        assertThrows(RuntimeException.class, () -> {
            manager.addContact("amish", null, "7979797822");
        });
    }

    @Test
    @DisplayName("throwing exception if contact is empty")
    void throwExceptionIfContactNoIsEmpty() {
        assertThrows(RuntimeException.class, () -> {
            manager.addContact("Tariq", "Hayat", null);
        });
    }

    @Test
    @DisplayName("throwing exception if contactNo length is mismatch")
    void throwExceptionIfContactLengthMisMatch() {
        assertThrows(RuntimeException.class, () -> manager.addContact("Hayat", "Tarique", "797979780011"));

    }

    @Test
    @DisplayName("throwing exception if contact already exist")
    void throwExceptionIfContactAlreadyExisted() {
        manager.addContact("Hayat", "Tarique", "7979797800");
        assertThrows(RuntimeException.class, () -> {
            manager.addContact("Hayat", "Tarique", "7979797800");
        });

    }

    @AfterEach
    void clean() {
        manager = null;
    }
}
