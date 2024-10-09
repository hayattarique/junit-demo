package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.ValueSources;

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
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 0)
    void addNewContactTest(String firstName, String contactNo) {
        manager.addContact(firstName, "Hayat", contactNo);
        assertEquals(1, manager.getAllContacts().size());
        assertFalse(manager.getAllContacts().isEmpty());
    }
    //parameterizedTest
    @ParameterizedTest
    @CsvSource({"Hayat,Tariq,0987654321", "Shahan,Malik,6098765678", "Amish,malik,9090876543"})
    void addNewContactTest(String firstName, String lastName, String contactNo) {
        manager.addContact(firstName, lastName, contactNo);
        assertEquals(1, manager.getAllContacts().size());
        assertFalse(manager.getAllContacts().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"7979797979", "9876543210"})
    void addNewContactTest(String contactNo) {
        manager.addContact("Hayat", "Tariq", contactNo);
        assertEquals(1, manager.getAllContacts().size());
        assertFalse(manager.getAllContacts().isEmpty());
    }


    @AfterEach
    void clean() {
        manager = null;
    }
}
