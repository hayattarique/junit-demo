package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {
    private String firstName;
    private String lastName;
    private String contactNo;

    public void validateFirstName() {
        if (this.firstName.isBlank()) {
            throw new RuntimeException("First Name can't be empty");
        }
    }

    public void validateLastName() {
        if (this.lastName.isBlank()) {
            throw new RuntimeException("Last Name can't be empty");
        }
    }

    public void validateContactNo() {

        if (this.contactNo.length() != 10) {
            throw new RuntimeException("Contact Number must be 10 digit");
        }
    }
}
