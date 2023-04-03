package com.wilp.samplepostapidemo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AuthToken.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    private static final String DB_NAME = "my_database";
    private static volatile MyDatabase instance;

    public abstract AuthTokenDao authTokenDao();

    public static synchronized MyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}

