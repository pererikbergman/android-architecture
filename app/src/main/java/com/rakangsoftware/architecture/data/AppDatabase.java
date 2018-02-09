package com.rakangsoftware.architecture.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ContactDao getContactDao();

    private static AppDatabase sDatabase;

    public static AppDatabase getDB(Context context) {
        synchronized (sDatabase) {
            if (sDatabase == null) {
                sDatabase = Room.databaseBuilder(
                        context,
                        AppDatabase.class,
                        "database.db")
                        .allowMainThreadQueries()
                        .build();
            }
        }

        return sDatabase;
    }
}
