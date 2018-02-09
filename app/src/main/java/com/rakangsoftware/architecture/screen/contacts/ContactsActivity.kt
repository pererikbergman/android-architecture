package com.rakangsoftware.architecture.screen.contacts

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText

import com.rakangsoftware.architecture.R
import com.rakangsoftware.architecture.data.Contact

class ContactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        val viewModel = ViewModelProviders.of(this).get(ContactsViewModel::class.java)

        val contacts = findViewById<RecyclerView>(R.id.contact_list)
        contacts.layoutManager = LinearLayoutManager(this)
        val adapter = ContactAdapter(object : ContactAdapter.ViewHolder.OnContactClickListener {
            override fun onClicked(contact: Contact?) {
                viewModel.onContactClicked(contact)
            }

            override fun onLongClicked(contact: Contact?) {
                viewModel.onDeleteContact(contact)
            }
        })
        contacts.adapter = adapter
        viewModel.contacts.observe(this, Observer { contacts -> adapter.setData(contacts!!) })

        viewModel.showDialog.observe(this, Observer { show ->
            if (show != null && show) {
                showInputDialog(viewModel)
            }
        })

        findViewById<View>(R.id.add).setOnClickListener { viewModel.clickAddContact() }

        val root = findViewById<View>(R.id.root)
        viewModel.deletedContact.observe(this, Observer { contact -> showSnackbar(root, viewModel, contact!!) })
    }

    private fun showInputDialog(viewModel: ContactsViewModel) {
        val dialogBuilder = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.input_dialog, null)
        dialogBuilder.setView(view)

        val alertDialog = dialogBuilder.create()
        val nameInput = view.findViewById<EditText>(R.id.name_input)
        val surenameInput = view.findViewById<EditText>(R.id.surename_input)

        view.findViewById<View>(R.id.save_button).setOnClickListener {
            val contact = Contact()
            contact.name = nameInput.text.toString().trim { it <= ' ' }
            contact.surename = surenameInput.text.toString().trim { it <= ' ' }

            viewModel.createContact(contact)

            alertDialog.hide()
        }
        alertDialog.show()
    }

    private fun showSnackbar(view: View, viewModel: ContactsViewModel, contact: Contact) {
        Snackbar.make(view, contact.name!! + " deleted.", Snackbar.LENGTH_SHORT)
                .addCallback(
                        object : Snackbar.Callback() {
                            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                                super.onDismissed(transientBottomBar, event)
                                viewModel.doDelete(contact)
                            }
                        }
                )
                .setAction(
                        "Undo"
                ) { viewModel.restoreContact(contact) }
                .show()
    }
}
