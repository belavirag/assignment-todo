package dev.belavirag.assignment.todo.dao;

import dev.belavirag.assignment.todo.AppUser;

import java.util.Collection;

public interface IAppUserDAO {
    AppUser persist(AppUser appUser);

    AppUser findByUsername(String username);

    Collection<AppUser> findAll();

    void remove(String username);
}
