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

    @Test
    void hashCodeAndEquals() {
        TodoItemTask item1 = getExample();
        item1.setAssignee(new Person(2, "RandomFirstName", "RandomLastName", "hello@example.org")); // ignore Person objects
        TodoItemTask item2 = getExample();
        item2.setAssigned(true);
        TodoItemTask item3 = new TodoItemTask(3, false, new TodoItem(2, "A Todo Item", null, LocalDate.now().plusDays(4), false, null), null);

        assertEquals(item1, item2);
        assertNotEquals(item1, item3);
        assertEquals(item1.hashCode(), item2.hashCode());
        assertNotEquals(item1.hashCode(), item3.hashCode());
    }
}