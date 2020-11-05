package com.example.hw11.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hw11.model.Task;
import com.example.hw11.model.User;

import java.util.List;
import java.util.UUID;
@Dao
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

   /* @Query("SELECT * FROM taskTable WHERE state LIKE :state ")
    List<Task> getTasks(String state);*/

    @Query("SELECT * FROM taskTable WHERE state ='Todo'")
    List<Task> getTodoTask();

    @Query("SELECT * FROM taskTable WHERE state ='Doing'")
    List<Task> getDoingTask();

    @Query("SELECT * FROM taskTable WHERE state ='Done'")
    List<Task> getDoneTask();

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM userTable")
    List<User> getUsers();

    @Query("SELECT * FROM userTable WHERE  username=:name")
    User getUser(String name);


}
