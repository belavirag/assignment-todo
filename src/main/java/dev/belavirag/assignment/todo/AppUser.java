package dev.belavirag.assignment.todo;

import java.util.Objects;

public class AppUser {
    private String username;
    private String password;
    private AppRole role;

    public AppUser(String username, String password, AppRole role) {
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Objects.requireNonNull(username, "username cannot be null!");
        if (username.isEmpty()) {
            throw new IllegalArgumentException("username cannot be empty!");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Objects.requireNonNull(password, "password cannot be null!");
        if (password.isEmpty()) {
            throw new IllegalArgumentException("password cannot be empty!");
        }
        this.password = password;
    }

    public AppRole getRole() {
        return role;
    }

    public void setRole(AppRole role) {
        Objects.requireNonNull(role, "role cannot be null!");
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AppUser otherUser) {
            return otherUser.getUsername().equals(this.username) &&
                    otherUser.getRole().equals(this.role);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 524287 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
