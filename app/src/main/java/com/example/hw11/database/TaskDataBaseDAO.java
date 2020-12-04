package com.example.hw11.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
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
    void insertTasks(List<Task> tasks);

    @Delete
    void deleteTask(Task task);

    @Query("DELETE FROM taskTable")
    void deleteAllTask();

    @Query("SELECT * FROM taskTable")
    List<Task> getTasks();

    @Query("SELECT * FROM taskTable WHERE state ='Todo' AND user_id_fk=:userId")
    List<Task> getTodoTask(long userId);

    @Query("SELECT * FROM taskTable WHERE state ='Doing' AND user_id_fk=:userId")
    List<Task> getDoingTask(long userId);

    @Query("SELECT * FROM taskTable WHERE state ='Done' AND user_id_fk=:userId")
    List<Task> getDoneTask(long userId);

    @Query("SELECT * FROM taskTable WHERE uuid =:inputUUID")
    Task getTask(UUID inputUUID);

    @Query("SELECT * FROM taskTable WHERE user_id_fk=:userId AND title LIKE :searchValue OR description LIKE :searchValue OR date LIKE :searchValue")
    List<Task> searchTasks(String searchValue,long userId);

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM taskTable WHERE user_id_fk=:userId")
    void deleteUserTasks(long userId);

    @Query("SELECT * FROM taskTable WHERE user_id_fk=:userId")
    List<Task> getUserTasks (long userId);

    @Query("SELECT * FROM userTable")
    List<User> getUsers();

    @Query("SELECT * FROM userTable WHERE  username=:name AND password=:pass")
    User getUser(String name,String pass);

    @Query("SELECT COUNT(*) FROM taskTable WHERE user_id_fk=:userId GROUP BY user_id_fk")
    int numberOfTask(long userId);


}
