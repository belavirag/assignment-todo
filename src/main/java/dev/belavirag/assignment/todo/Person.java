package dev.belavirag.assignment.todo;

import java.util.Objects;

public class Person {
    private int id;
    private String firstName, lastName;
    private String email;
    private AppUser credentials;

    public Person(int id, String firstName, String lastName, String email, AppUser credentials) {
        this.id = id;
        this.credentials = credentials;
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    public Person(int id, String firstName, String lastName, String email) {
        this(id, firstName, lastName, email, null);
    }

    public AppUser getCredentials() {
        return credentials;
    }

    public void setCredentials(AppUser credentials) {
        this.credentials = credentials;
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
        Objects.requireNonNull(firstName, "first name cannot be null!");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        Objects.requireNonNull(lastName, "last name cannot be null!");
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Objects.requireNonNull(email, "email cannot be null!");
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person otherPerson) {
            return  otherPerson.getEmail().equals(this.email) &&
                    otherPerson.getFirstName().equals(this.firstName) &&
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
        result = 524287 * result + email.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
