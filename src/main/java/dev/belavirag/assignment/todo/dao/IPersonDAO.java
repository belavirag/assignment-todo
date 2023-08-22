package dev.belavirag.assignment.todo.dao;

import dev.belavirag.assignment.todo.Person;

import java.util.Collection;

public interface IPersonDAO {
    Person persist(Person person);

    Person findById(int id);

    Person findByEmail(String email);

    Collection<Person> findAll();

    void remove(int id);
}
