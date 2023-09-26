package dev.belavirag.assignment.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TodoItemTest {

    Todo getExample() {
        return new Todo(1, "Test Todo", null, LocalDate.now(), false, null);
    }

    @Test
    void isOverdue() {
        Todo item = getExample();

        item.setDeadLine(LocalDate.now().plusDays(1));
        assertFalse(item.isOverdue());

        item.setDeadLine(LocalDate.now().minusDays(1));
        assertTrue(item.isOverdue());
    }

    @Test
    void hashCodeAndEquals() {
        Todo item1 = getExample();
        item1.setAssignee(new Person(2, "RandomFirstName", "RandomLastName")); // ignore Person objects
        Todo item2 = getExample();
        Todo item3 = new Todo(3, "A Random Title", "Something idk", LocalDate.now().plusDays(7), false, null);

        assertEquals(item1, item2);
        assertNotEquals(item1, item3);
        assertEquals(item1.hashCode(), item2.hashCode());
        assertNotEquals(item1.hashCode(), item3.hashCode());
    }
}