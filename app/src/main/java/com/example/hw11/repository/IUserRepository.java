package com.example.hw11.repository;

import com.example.hw11.model.User;

import java.util.List;

public interface IUserRepository {
    List<User> getUsers();
    User getUser(String username);
    void insertUser(User user);
}
