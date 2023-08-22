package dev.belavirag.assignment.todo.dao;

import dev.belavirag.assignment.todo.TodoItemTask;

import java.util.Collection;

public interface ITodoItemTaskDAO {
    TodoItemTask persist(TodoItemTask itemTask);

    TodoItemTask findById(int id);

    Collection<TodoItemTask> findAll();

    Collection<TodoItemTask> findByAssignedStatus(boolean status);

    Collection<TodoItemTask> findByPersonId(int personId);

    void remove(int id);
}
