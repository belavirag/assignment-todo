package dev.belavirag.assignment.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person getExample() {
        return new Person(1, "FirstName", "LastName");
    }

    @Test
    void hashCodeAndEquals() {
        Person p1 = getExample();
        Person p2 = getExample();
        Person p3 = new Person(2, "first", "last");

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1.hashCode(), p3.hashCode());
    }
}