package dev.belavirag.assignment.todo.dao;

import dev.belavirag.assignment.todo.TodoItemTask;
import dev.belavirag.assignment.todo.sequencer.TodoItemTaskIdSequencer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TodoItemTaskDAOCollection implements ITodoItemTaskDAO {
    private ArrayList<TodoItemTask> itemTasks;

    public TodoItemTaskDAOCollection(ArrayList<TodoItemTask> itemTasks) {
        this.itemTasks = itemTasks;
    }

    public TodoItemTaskDAOCollection() {
        this(new ArrayList<>());
    }

    @Override
    public TodoItemTask persist(TodoItemTask itemTask) {
        if (itemTask.getId() == -1) {
            itemTask.setId(TodoItemTaskIdSequencer.nextId());
        }

        itemTasks.removeIf(a -> a.getId() == itemTask.getId());
        itemTasks.add(itemTask);

        return itemTask;
    }

    @Override
    public TodoItemTask findById(int id) {
        for (TodoItemTask itemTask : itemTasks) {
            if (itemTask.getId() == id) {
                return itemTask;
            }
        }

        return null;
    }

    @Override
    public Collection<TodoItemTask> findAll() {
        return List.copyOf(itemTasks);
    }

    @Override
    public Collection<TodoItemTask> findByAssignedStatus(boolean status) {
        return this.itemTasks
                .stream()
                .filter(a -> a.isAssigned() == status)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItemTask> findByPersonId(int personId) {
        return this.itemTasks.stream()
                .filter(a -> a.getAssignee() != null)
                .filter(a -> a.getAssignee().getId() == personId)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(int id) {
        this.itemTasks.removeIf(a -> a.getId() == id);
    }
}
