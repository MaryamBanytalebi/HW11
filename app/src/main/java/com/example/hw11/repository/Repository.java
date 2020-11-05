package com.example.hw11.repository;

import com.example.hw11.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Repository implements IRepositry{
    private static Repository sInstance;
    private List<Task> mTasks;

    public List<Task> getTasks() {
        return mTasks;
    }

    private Repository() {
        mTasks = new ArrayList<>();
    }

    public static Repository getInstance() {
        if (sInstance == null)
            sInstance = new Repository();
        return sInstance;
    }

    public Task getTask(UUID id){
        for (Task task : mTasks){
            if (task.getId().equals(id))
                return task;
        }
        return null;
    }

    public void insertTask(Task task){
        mTasks.add(task);
    }
    public void updateTask(Task task){
        Task selectTask = getTask(task.getId());
        selectTask.setTitle(task.getTitle());
        selectTask.setDescription(task.getDescription());
        selectTask.setDate(task.getDate());
        selectTask.setState(task.getState());

    }

    @Override
    public List<Task> searchTasks(String searchValue) {
        return null;
    }

    @Override
    public List<Task> getTodoTask() {
        return null;
    }

    @Override
    public List<Task> getDoingTask() {
        return null;
    }

    @Override
    public List<Task> getDoneTask() {
        return null;
    }

    public void deletTask(Task task){
        for (int i = 0; i < mTasks.size(); i++) {
            if (mTasks.get(i).getId().equals(task.getId()));
            mTasks.remove(i);
            return;
        }
    }

}
