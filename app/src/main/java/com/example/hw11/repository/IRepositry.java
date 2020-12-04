package com.example.hw11.repository;

import com.example.hw11.model.Task;

import java.io.File;
import java.util.List;
import java.util.UUID;

public interface IRepositry {
    List<Task> getTasks();
    Task getTask(UUID id);
    void insertTask(Task task);
    void insertTasks(List<Task> tasks);
    void deletTask(Task task);
    void deleteAllTask();
    void updateTask(Task task);
    //List<Task> searchTasks(String searchValue);
    List<Task> searchTasks(String searchValue,long userId);
    List<Task> getTodoTask(long userId);
    List<Task> getDoingTask(long userId);
    List<Task> getDoneTask(long userId);
    File getPhotoFile(Task task);

}
