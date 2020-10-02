package com.example.hw11.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.hw11.database.TaskDBHelper;
import com.example.hw11.database.TaskDBSchema;
import com.example.hw11.model.Task;
import static com.example.hw11.database.TaskDBSchema.TaskTable.Cols;

import java.util.List;
import java.util.UUID;

public class TaskDBRepository implements IRepositry {

    private static TaskDBRepository sInstance;
    private SQLiteDatabase mDatabase;

    public static TaskDBRepository getInstance(Context context){
        if (sInstance == null)
            sInstance = new TaskDBRepository(context);
            return sInstance;
    }

    public TaskDBRepository(Context context) {
        TaskDBHelper taskDBHelper = new TaskDBHelper(context);
        taskDBHelper.getWritableDatabase();
    }

    @Override
    public List<Task> getTasks() {
        return null;
    }

    @Override
    public Task getTask(UUID id) {
        return null;
    }

    @Override
    public void insertTask(Task task) {
        ContentValues values = new ContentValues();
        values.put(Cols.UUID , task.getId().toString());
        values.put(Cols.TITLE , task.getTitle());
        values.put(Cols.STATE , task.getState());
        values.put(Cols.DATE , task.getDate().toString());
        values.put(Cols.DESCRIPTION , task.getDescription());
        mDatabase.insert(TaskDBSchema.TaskTable.NAME, null, values);

    }

    @Override
    public void deletTask(Task task) {

    }

    @Override
    public void updateTask(Task task) {

    }
}
