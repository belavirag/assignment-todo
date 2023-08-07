package dev.belavirag.assignment.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TodoItemTaskTest {

    TodoItemTask getExample() {
        return new TodoItemTask(1, false, new TodoItem(1, "Test Title", null, LocalDate.now(), false, null), null);
    }

    @Test
    void isAssigned() {
        TodoItemTask itemTask = getExample();

        itemTask.setAssignee(null);
        assertFalse(itemTask.isAssigned());
        itemTask.setAssignee(new Person(1, "FirstName", "LastName", "email"));
        assertTrue(itemTask.isAssigned());
    }

    @Test
    void setTodoItem() {
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            TodoItemTask itemTask = getExample();
            itemTask.setTodoItem(null);
        });

        assertEquals("todoItem cannot be null!", thrown.getMessage());
    }
}