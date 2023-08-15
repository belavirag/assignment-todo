package dev.belavirag.assignment.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person getExample() {
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

    @Test
    void hashCodeAndEquals() {
        Person p1 = getExample();
        Person p2 = getExample();
        p1.setCredentials(new AppUser("username", "password", AppRole.ROLE_APP_USER)); // exclude credentials
        Person p3 = new Person(2, "first", "last", "mail@example.com");

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1.hashCode(), p3.hashCode());
    }
}