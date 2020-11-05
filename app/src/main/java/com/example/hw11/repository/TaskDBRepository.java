package com.example.hw11.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Room;

import com.example.hw11.database.TaskDBHelper;
import com.example.hw11.database.TaskDBSchema;
import com.example.hw11.database.TaskDataBase;
import com.example.hw11.database.TaskDataBaseDAO;
import com.example.hw11.model.Task;
import static com.example.hw11.database.TaskDBSchema.TaskTable.Cols;

import java.util.List;
import java.util.UUID;

public class TaskDBRepository implements IRepositry {

    private static TaskDBRepository sInstance;

    private TaskDataBaseDAO mTaskDAO;
    private Context mContext;
    private List<Task> mTasks;

    public static TaskDBRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new TaskDBRepository(context);

        return sInstance;
    }

    private TaskDBRepository(Context context) {
        mContext = context.getApplicationContext();
        TaskDataBase taskDatabase = Room.databaseBuilder(mContext,
                TaskDataBase.class,
                "task.db")
                .allowMainThreadQueries()
                .build();

        mTaskDAO = taskDatabase.getTaskDatabaseDAO();
    }

    @Override
    public List<Task> getTasks() {
        return mTaskDAO.getTasks();
    }

    @Override
    public List<Task> searchTasks(String searchValue) {
        return mTaskDAO.searchTasks(searchValue);
    }

    @Override
    public List<Task> getTodoTask() { return mTaskDAO.getTodoTask(); }

    @Override
    public List<Task> getDoingTask() {
        return mTaskDAO.getDoingTask();
    }

    @Override
    public List<Task> getDoneTask() {
        return mTaskDAO.getDoneTask();
    }

    @Override
    public Task getTask(UUID taskId) {
        return mTaskDAO.getTask(taskId);
    }

    @Override
    public void insertTask(Task task) {
        mTaskDAO.insertTask(task);
    }

    @Override
    public void updateTask(Task task) {
        mTaskDAO.updateTask(task);
    }

    @Override
    public void deletTask(Task task) {
        mTaskDAO.deletTask(task);
    }

}
