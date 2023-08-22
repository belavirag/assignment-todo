package dev.belavirag.assignment.todo.dao;

import dev.belavirag.assignment.todo.AppUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppUserDAOCollection implements IAppUserDAO {

    private ArrayList<AppUser> users;

    public AppUserDAOCollection() {
        this(new ArrayList<>());
    }

    public AppUserDAOCollection(ArrayList<AppUser> users) {
        this.users = users;
    }

    @Override
    public AppUser persist(AppUser appUser) {
        users.removeIf(a -> a.getUsername().equals(appUser.getUsername()));
        users.add(appUser);

        return appUser;
    }

    @Override
    public AppUser findByUsername(String username) {
        for (AppUser user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Collection<AppUser> findAll() {
        return List.copyOf(users);
    }

    @Override
    public void remove(String username) {
        this.users.removeIf(a -> a.getUsername().equals(username));
    }
}
