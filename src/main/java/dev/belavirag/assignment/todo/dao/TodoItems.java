package dev.belavirag.assignment.todo.dao;

import dev.belavirag.assignment.todo.Person;
import dev.belavirag.assignment.todo.Todo;

import java.util.Collection;

public interface TodoItems
{
    Todo create(Todo todo);
    Collection<Todo> findAll();
    Todo findById(int id);
    Collection<Todo> findByAssignee(int id);
    Collection<Todo> findByAssignee(Person person);
    Collection<Todo> findByUnassignedTodoItems();
    Todo update(Todo todo);
    boolean deleteById(int id);
}
