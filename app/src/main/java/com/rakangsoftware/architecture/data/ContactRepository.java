package com.rakangsoftware.architecture.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

public class ContactRepository {

    private final ContactDao mContactDao;

    public ContactRepository(final ContactDao contactDao) {
        mContactDao = contactDao;
    }

    public LiveData<List<Contact>> getAll() {
        return mContactDao.getAllContacts();
    }

    public void create(final Contact contact) {
        mContactDao.insert(contact);
    }

    public void delete(final Contact contact) {
        mContactDao.delete(contact);
    }
}
