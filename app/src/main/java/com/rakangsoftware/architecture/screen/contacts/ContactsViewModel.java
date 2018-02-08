package com.rakangsoftware.architecture.screen.contacts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rakangsoftware.architecture.data.AppDatabase;
import com.rakangsoftware.architecture.data.Contact;
import com.rakangsoftware.architecture.data.ContactRepository;
import com.rakangsoftware.architecture.utils.SingleLiveEvent;

import java.util.List;


public class ContactsViewModel extends AndroidViewModel {

    private final ContactRepository mContactRepository;

    private final SingleLiveEvent<Boolean> mShowDialog     = new SingleLiveEvent<>();
    private final SingleLiveEvent<Contact> mDeletedContact = new SingleLiveEvent<>();
    private Contact mDelContact;

    public ContactsViewModel(@NonNull final Application application) {
        super(application);

        mContactRepository = new ContactRepository(
                AppDatabase.getDB(application).getContactDao()
        );
    }

    public LiveData<List<Contact>> getContacts() {
        return mContactRepository.getAll();
    }

    public LiveData<Boolean> getShowDialog() {
        return mShowDialog;
    }

    public SingleLiveEvent<Contact> getDeletedContact() {
        return mDeletedContact;
    }

    public void onContactClicked(final Contact contact) {
        // Open edit!!!
    }

    public void onDeleteContact(final Contact contact) {
        mDelContact = contact;
        mContactRepository.delete(contact);
        mDeletedContact.setValue(contact);
    }

    public void doDelete(final Contact contact) {
        mDelContact = null;
    }

    public void restoreContact(final Contact contact) {
        mContactRepository.create(contact);
    }

    public void clickAddContact() {
        mShowDialog.postValue(true);
    }

    public void createContact(final Contact contact) {
        mContactRepository.create(contact);
    }


}
