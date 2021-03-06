package com.example.hw11.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.hw11.model.Task;
import com.example.hw11.model.User;

@Database(entities = {Task.class, User.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class TaskDataBase extends RoomDatabase {
    public abstract TaskDataBaseDAO getTaskDatabaseDAO();
}
