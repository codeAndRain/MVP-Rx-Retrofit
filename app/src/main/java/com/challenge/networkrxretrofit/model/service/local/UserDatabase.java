package com.challenge.networkrxretrofit.model.service.local;

import android.content.Context;

import com.challenge.networkrxretrofit.model.User;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = User.class, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static final String DB_NAME = "user.db";

    private static UserDatabase instance;

    public abstract UserDao getUserDao();


    public static UserDatabase getInstance(Context context) {
        if (instance == null)  {
            instance = Room.databaseBuilder(context, UserDatabase.class, DB_NAME).build();
        }

        return instance;
    }

    public void destroyDbInstance() {
        instance = null;
    }
}
