package com.rakangsoftware.architecture.screen.contacts

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText

import com.rakangsoftware.architecture.R
import com.rakangsoftware.architecture.data.Contact
import kotlinx.android.synthetic.main.input_dialog.*

class ContactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        val contacts = findViewById<RecyclerView>(R.id.contact_list)
        contacts.layoutManager = LinearLayoutManager(this)
        contacts.adapter = ContactAdapter(object : ContactAdapter.ViewHolder.OnContactClickListener {
            override fun onClicked(contact: Contact?) {
                // Call on ViewModel
            }

            override fun onLongClicked(contact: Contact?) {
                // Call on ViewModel
            }
        })

        findViewById<View>(R.id.add).setOnClickListener { showInputDialog() }
    }

    private fun showInputDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.input_dialog, null)
        dialogBuilder.setView(view)

        val alertDialog = dialogBuilder.create()
        view.findViewById<View>(R.id.save_button).setOnClickListener {
            val contact = Contact()
            contact.name = name_input.text.toString().trim { it <= ' ' }
            contact.surename = surename_input.text.toString().trim { it <= ' ' }

            alertDialog.hide()
        }
        alertDialog.show()
    }

    private fun showSnackbar(view: View) {
        Snackbar.make(view, "Hellu Snackbar!", Snackbar.LENGTH_SHORT).show()
    }
}
