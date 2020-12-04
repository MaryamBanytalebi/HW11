package com.example.hw11.repository;

import com.example.hw11.model.Task;
import com.example.hw11.model.User;

import java.util.List;

public interface IUserRepository {
    List<User> getUsers();
    User getUser(String username,String password);
    void deleteUser(User user);
    void deleteUserTasks(long userId);
    List<Task> getUserTasks (long userId);
    int numberOfTask(long userId);
    void insertUser(User user);
}
