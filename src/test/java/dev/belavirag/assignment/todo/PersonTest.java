package dev.belavirag.assignment.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person getExample()
    {
        return new Person(1, "FirstName", "LastName", "email");
    }

    @Test
    void setFirstName() {
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            Person p = getExample();
            p.setFirstName(null);
        });

        assertEquals("first name cannot be null!", thrown.getMessage());
    }

    @Test
    void setLastName() {
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            Person p = getExample();
            p.setLastName(null);
        });

        assertEquals("last name cannot be null!", thrown.getMessage());
    }

    @Test
    void setEmail() {
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            Person p = getExample();
            p.setEmail(null);
        });

        assertEquals("email cannot be null!", thrown.getMessage());
    }
}