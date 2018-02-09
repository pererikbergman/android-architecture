package com.rakangsoftware.architecture.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface ContactDao {

    @get:Query("SELECT * FROM contacts")
    val allContacts: LiveData<List<Contact>>

    @Insert
    fun insert(vararg contacts: Contact)

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getContactById(id: Int): LiveData<Contact>

    @Update
    fun update(vararg contacts: Contact)

    @Delete
    fun delete(vararg contacts: Contact)
}
