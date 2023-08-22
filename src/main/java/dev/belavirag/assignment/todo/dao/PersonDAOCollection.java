package dev.belavirag.assignment.todo.dao;

import dev.belavirag.assignment.todo.Person;
import dev.belavirag.assignment.todo.sequencer.PersonIdSequencer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonDAOCollection implements IPersonDAO {
    private ArrayList<Person> people;

    public PersonDAOCollection(ArrayList<Person> people) {
        this.people = people;
    }

    public PersonDAOCollection() {
        this(new ArrayList<>());
    }

    @Override
    public Person persist(Person person) {
        if (person.getId() == -1) {
            // generate new id
            person.setId(PersonIdSequencer.nextId());
        }

        people.removeIf(a -> a.getId() == person.getId());
        people.add(person);

        return person;
    }

    @Override
    public Person findById(int id) {
        for (Person p : people) {
            if (p.getId() == id) {
                return p;
            }
        }

        return null;
    }

    @Override
    public Person findByEmail(String email) {
        for (Person p : people) {
            if (p.getEmail().equals(email)) {
                return p;
            }
        }

        return null;
    }

    @Override
    public Collection<Person> findAll() {
        return List.copyOf(people);
    }

    @Override
    public void remove(int id) {
        this.people.removeIf(a -> a.getId() == id);
    }
}
