package dev.belavirag.assignment.todo;

import java.util.Objects;

public class TodoItemTask {
    private int id;
    private boolean assigned;
    private Todo todoItem;
    private Person assignee;

    public TodoItemTask(int id, boolean assigned, Todo todoItem, Person assignee) {
        this.id = id;
        this.assigned = assigned;
        setTodoItem(todoItem);
        setAssignee(assignee);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public Todo getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(Todo todoItem) {
        Objects.requireNonNull(todoItem, "todoItem cannot be null!");
        this.todoItem = todoItem;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assigned = assignee != null;
        this.assignee = assignee;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TodoItemTask otherItemTask) {
            return  otherItemTask.getTodoItem().equals(this.todoItem) &&
                    otherItemTask.isAssigned() == this.assigned &&
                    otherItemTask.getId() == this.id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 524287 * result + (assigned ? 1 : 0);
        result = 524287 * result + (todoItem != null ? todoItem.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "TodoItemTask{" +
                "id=" + id +
                ", assigned=" + assigned +
                ", todoItem=" + todoItem +
                '}';
    }
}
