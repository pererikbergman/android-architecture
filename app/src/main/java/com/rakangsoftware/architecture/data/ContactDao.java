package com.rakangsoftware.architecture.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert
    void insert(Contact... contacts);

    @Query("SELECT * FROM contacts")
    LiveData<List<Contact>> getAllContacts();

    @Query("SELECT * FROM contacts WHERE id = :id")
    LiveData<Contact> getContactById(int id);

    @Update
    void update(Contact... contacts);

    @Delete
    void delete(Contact... contacts);
}
