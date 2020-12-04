package com.example.hw11.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.hw11.model.User;

import java.util.List;

public class UserWithTask {

    @Embedded
    public User user;
    @Relation(
            parentColumn = "user_id_fk",
            entityColumn = "id"
    )
    public List<User> mUserList;
}
