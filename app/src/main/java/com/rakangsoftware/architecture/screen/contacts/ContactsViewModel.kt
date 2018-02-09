package com.rakangsoftware.architecture.screen.contacts

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import com.rakangsoftware.architecture.data.AppDatabase
import com.rakangsoftware.architecture.data.Contact
import com.rakangsoftware.architecture.data.ContactRepository
import com.rakangsoftware.architecture.utils.SingleLiveEvent


class ContactsViewModel(application: Application) : AndroidViewModel(application) {

    private val mContactRepository: ContactRepository

    private val mShowDialog = SingleLiveEvent<Boolean>()
    val deletedContact = SingleLiveEvent<Contact>()
    private var mDelContact: Contact? = null

    val contacts: LiveData<List<Contact>>
        get() = mContactRepository.all

    val showDialog: LiveData<Boolean>
        get() = mShowDialog

    init {

        mContactRepository = ContactRepository(
                AppDatabase.getDB(application).contactDao
        )
    }

    fun onContactClicked(contact: Contact) {
        // Open edit!!!
    }

    fun onDeleteContact(contact: Contact) {
        mDelContact = contact
        mContactRepository.delete(contact)
        deletedContact.value = contact
    }

    fun doDelete(contact: Contact) {
        mDelContact = null
    }

    fun restoreContact(contact: Contact) {
        mContactRepository.create(contact)
    }

    fun clickAddContact() {
        mShowDialog.postValue(true)
    }

    fun createContact(contact: Contact) {
        mContactRepository.create(contact)
    }


}
