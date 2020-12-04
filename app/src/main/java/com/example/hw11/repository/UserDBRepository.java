package com.example.hw11.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.hw11.database.TaskDataBase;
import com.example.hw11.database.TaskDataBaseDAO;
import com.example.hw11.model.Task;
import com.example.hw11.model.User;

import java.util.List;

public class UserDBRepository implements IUserRepository {

    private static UserDBRepository sInstance;
    private TaskDataBaseDAO mTaskDAO;
    private Context mContext;

    public static UserDBRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new UserDBRepository(context);

        return sInstance;
    }

    private UserDBRepository(Context context) {
        mContext = context.getApplicationContext();
        TaskDataBase taskDatabase = Room.databaseBuilder(mContext,
                TaskDataBase.class,
                "task.db")
                .allowMainThreadQueries()
                .build();

        mTaskDAO = taskDatabase.getTaskDatabaseDAO();
    }

    @Override
    public List<User> getUsers() {
        return mTaskDAO.getUsers();
    }

    @Override
    public User getUser(String username,String password) {
        return mTaskDAO.getUser(username,password);
    }

    @Override
    public void insertUser(User user) {
        mTaskDAO.insertUser(user);
    }

    @Override
    public void deleteUser(User user) {
        mTaskDAO.deleteUser(user);
    }

    @Override
    public void deleteUserTasks(long userId) {
        mTaskDAO.deleteUserTasks(userId);
    }

    @Override
    public List<Task> getUserTasks(long userId) {
        return mTaskDAO.getUserTasks(userId);
    }

    @Override
    public int numberOfTask(long userId) {
        return mTaskDAO.numberOfTask(userId);
    }

}
