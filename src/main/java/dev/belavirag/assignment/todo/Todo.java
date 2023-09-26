package dev.belavirag.assignment.todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class Todo {
    private int id;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private Person assignee;

    public Todo(int id, String title, String description, LocalDate deadLine, boolean done, Person assignee) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
        this.assignee = assignee;
    }

    public Todo(String title, String description, LocalDate deadLine, boolean done, Person assignee) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
        this.assignee = assignee;
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
        this.deadLine = deadLine;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(this.deadLine);
    }

    public static Todo fromResultSet(ResultSet resultSet, Person p) throws SQLException {
        return new Todo(resultSet.getInt("todo_id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getDate("deadline").toLocalDate(),
                resultSet.getBoolean("done"),
                p);
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
        if (obj instanceof Todo otherTodoItem) {
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
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                ", assignee=" + assignee +
                '}';
    }
}
