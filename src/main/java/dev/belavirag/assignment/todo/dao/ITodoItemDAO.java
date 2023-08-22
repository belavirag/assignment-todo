package dev.belavirag.assignment.todo.dao;

import dev.belavirag.assignment.todo.TodoItem;

import java.time.LocalDate;
import java.util.Collection;

public interface ITodoItemDAO {
    TodoItem persist(TodoItem item);

    TodoItem findById(int id);

    Collection<TodoItem> findAll();

    Collection<TodoItem> findAllByDoneStatus(boolean done);

    Collection<TodoItem> findByTitleContains(String title);

    Collection<TodoItem> findByPersonId(int personId);

    Collection<TodoItem> findByDeadlineBefore(LocalDate date);

    Collection<TodoItem> findByDeadlineAfter(LocalDate date);

    void remove(int id);
}
