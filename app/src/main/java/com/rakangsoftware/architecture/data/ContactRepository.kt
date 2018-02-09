package com.rakangsoftware.architecture.data

import android.arch.lifecycle.LiveData

class ContactRepository(private val mContactDao: ContactDao) {

    val all: LiveData<List<Contact>>
        get() = mContactDao.allContacts

    fun create(contact: Contact) {
        mContactDao.insert(contact)
    }

    fun delete(contact: Contact) {
        mContactDao.delete(contact)
    }
}
