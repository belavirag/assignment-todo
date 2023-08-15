package dev.belavirag.assignment.todo;

import java.time.LocalDate;
import java.util.Objects;

public class TodoItem {
    private int id;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private Person creator;

    public TodoItem(int id, String title, String description, LocalDate deadLine, boolean done, Person creator) {
        this.id = id;
        setTitle(title);
        setDescription(description);
        setDeadLine(deadLine);
        this.done = done;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        Objects.requireNonNull(title, "title cannot be null!");
        if (title.isEmpty()) {
            throw new IllegalArgumentException("title is not allowed to be empty!");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        Objects.requireNonNull(deadLine, "deadLine cannot be null!");
        this.deadLine = deadLine;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(this.deadLine);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 524287 * result + title.hashCode();
        result = 524287 * result + (description == null ? 0 : description.hashCode());
        result = 524287 * result + deadLine.hashCode();
        result = 524287 * result + (done ? 1 : 0);

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TodoItem otherTodoItem) {
            return  Objects.equals(otherTodoItem.getDescription(), this.description) &&
                    otherTodoItem.getTitle().equals(this.title) &&
                    otherTodoItem.getId() == this.id &&
                    otherTodoItem.deadLine.equals(this.deadLine) &&
                    otherTodoItem.done == this.done;
        }

        return false;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                '}';
    }
}
