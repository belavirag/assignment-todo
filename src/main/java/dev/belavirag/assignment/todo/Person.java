package dev.belavirag.assignment.todo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Person {
    private int id;
    private String firstName, lastName;

    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static Person fromResultSet(ResultSet set) throws SQLException {
        return new Person(set.getInt("person_id"), set.getString("first_name"), set.getString("last_name"));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person otherPerson) {
            return  otherPerson.getFirstName().equals(this.firstName) &&
                    otherPerson.getLastName().equals(this.lastName) &&
                    otherPerson.getId() == this.id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 524287 * result + firstName.hashCode();
        result = 524287 * result + lastName.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
