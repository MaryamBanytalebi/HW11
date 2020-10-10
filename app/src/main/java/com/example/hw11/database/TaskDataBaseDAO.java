package com.example.hw11.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hw11.model.Task;

import java.util.List;
import java.util.UUID;

public interface TaskDataBaseDAO {

    @Update
    void updateTask(Task task);

    @Insert
    void insertTask(Task task);

    @Insert
    void insertTasks(Task... tasks);

    @Delete
    void deletTask(Task task);

    @Query("DELETE FROM taskTable")
    void deleteAllTask();

    @Query("SELECT * FROM taskTable")
    List<Task> getTasks();

    @Query("SELECT * FROM taskTable WHERE uuid =:inputUUID")
    Task getTask(UUID inputUUID);

    @Query("SELECT * FROM taskTable WHERE title LIKE :searchValue OR description LIKE :searchValue OR date LIKE :searchValue")
    List<Task> searchTasks(String searchValue);

}
