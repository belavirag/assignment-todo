package dev.belavirag.assignment.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TodoItemTest {

    TodoItem getExample() {
        return new TodoItem(1, "Test Todo", null, LocalDate.now(), false, null);
    }

    @Test
    void setTitle() {
        TodoItem item = getExample();
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            item.setTitle(null);
        });
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            item.setTitle("");
        });

        assertEquals("title cannot be null!", thrown.getMessage());
        assertEquals("title is not allowed to be empty!", thrown2.getMessage());
    }

    @Test
    void setDeadLine() {
        TodoItem item = getExample();
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            item.setDeadLine(null);
        });
        assertEquals("deadLine cannot be null!", thrown.getMessage());
    }

    @Test
    void isOverdue() {
        TodoItem item = getExample();

        item.setDeadLine(LocalDate.now().plusDays(1));
        assertFalse(item.isOverdue());

        item.setDeadLine(LocalDate.now().minusDays(1));
        assertTrue(item.isOverdue());
    }
}