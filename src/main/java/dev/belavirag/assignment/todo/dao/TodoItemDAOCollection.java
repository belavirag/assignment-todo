package dev.belavirag.assignment.todo.dao;

import dev.belavirag.assignment.todo.TodoItem;
import dev.belavirag.assignment.todo.sequencer.TodoItemIdSequencer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TodoItemDAOCollection implements ITodoItemDAO {
    private ArrayList<TodoItem> items;

    public TodoItemDAOCollection(ArrayList<TodoItem> items) {
        this.items = items;
    }

    public TodoItemDAOCollection() {
        this(new ArrayList<>());
    }

    @Override
    public TodoItem persist(TodoItem item) {
        if (item.getId() == -1) {
            item.setId(TodoItemIdSequencer.nextId());
        }

        items.removeIf(a -> a.getId() == item.getId());
        items.add(item);

        return item;
    }

    @Override
    public TodoItem findById(int id) {
        for (TodoItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    @Override
    public Collection<TodoItem> findAll() {
        return List.copyOf(items);
    }

    @Override
    public Collection<TodoItem> findAllByDoneStatus(boolean done) {
        return this.findAll()
                .stream()
                .filter(a -> a.isDone())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findByTitleContains(String title) {
        return this.findAll()
                .stream()
                .filter(a -> a.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findByPersonId(int personId) {
        return this.findAll()
                .stream()
                .filter(a -> a.getCreator() != null)
                .filter(a -> a.getCreator().getId() == personId)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findByDeadlineBefore(LocalDate date) {
        return this.findAll()
                .stream()
                .filter(a -> a.getDeadLine().isBefore(date))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findByDeadlineAfter(LocalDate date) {
        return this.findAll()
                .stream()
                .filter(a -> a.getDeadLine().isAfter(date))
                .collect(Collectors.toList());
    }

    @Override
    public void remove(int id) {
        this.items.removeIf(a -> a.getId() == id);
    }
}
