package com.example.hw11.repository;

import com.example.hw11.model.Task;

import java.util.List;
import java.util.UUID;

public interface IRepositry {
    List<Task> getTasks();
    Task getTask(UUID id);
    void insertTask(Task task);
    void deletTask(Task task);
    void updateTask(Task task);
    List<Task> searchTasks(String searchValue);


}
