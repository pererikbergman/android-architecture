package com.rakangsoftware.architecture.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(Contact::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val contactDao: ContactDao

    companion object {

        private var sDatabase: AppDatabase? = null

        fun getDB(context: Context): AppDatabase {
            if (sDatabase == null) {
                sDatabase = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "database.db")
                        .allowMainThreadQueries()
                        .build()
            }

            return sDatabase
        }
    }
}
