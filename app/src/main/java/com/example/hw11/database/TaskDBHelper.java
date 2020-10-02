package com.example.hw11.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TaskDBHelper extends SQLiteOpenHelper {

    public TaskDBHelper(@Nullable Context context) {
        super(context, TaskDBSchema.NAME, null, TaskDBSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("CREATE TABLE " + TaskDBSchema.TaskTable.NAME + " (");
        sbQuery.append(TaskDBSchema.TaskTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sbQuery.append(TaskDBSchema.TaskTable.Cols.UUID + "TEXT NOT NULL");
        sbQuery.append(TaskDBSchema.TaskTable.Cols.TITLE);
        sbQuery.append(TaskDBSchema.TaskTable.Cols.STATE);
        sbQuery.append(TaskDBSchema.TaskTable.Cols.DESCRIPTION);
        sbQuery.append(TaskDBSchema.TaskTable.Cols.DATE);
        sqLiteDatabase.execSQL(sbQuery.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
