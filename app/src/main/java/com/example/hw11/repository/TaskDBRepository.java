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

import java.io.File;
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
    public List<Task> searchTasks(String searchValue,long userId) {
        return mTaskDAO.searchTasks(searchValue,userId);
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
    public void insertTasks(List<Task> tasks) {
        mTaskDAO.insertTasks(tasks);
    }

    @Override
    public void updateTask(Task task) {
        mTaskDAO.updateTask(task);
    }

    @Override
    public void deletTask(Task task) {
        mTaskDAO.deleteTask(task);
    }

    @Override
    public void deleteAllTask() {
        mTaskDAO.deleteAllTask();
    }

    @Override
    public List<Task> getTodoTask(long userId) {
        return mTaskDAO.getTodoTask(userId);
    }

    @Override
    public List<Task> getDoingTask(long userId) {
        return mTaskDAO.getDoingTask(userId);
    }

    @Override
    public List<Task> getDoneTask(long userId) {
        return mTaskDAO.getDoneTask(userId);
    }

    @Override
    public File getPhotoFile(Task task) {
        // /data/data/com.example.criminalintent/files/
        File filesDir = mContext.getFilesDir();

        // /data/data/com.example.criminalintent/files/IMG_ktui4u544nmkfuy48485.jpg
        File photoFile = new File(filesDir, task.getPhotoFileName());
        return photoFile;
    }


}
