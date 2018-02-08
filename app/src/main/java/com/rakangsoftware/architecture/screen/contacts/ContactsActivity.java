package com.rakangsoftware.architecture.screen.contacts;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.rakangsoftware.architecture.R;
import com.rakangsoftware.architecture.data.Contact;

import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        final ContactsViewModel viewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);

        RecyclerView contacts = findViewById(R.id.contact_list);
        contacts.setLayoutManager(new LinearLayoutManager(this));
        final ContactAdapter adapter = new ContactAdapter(new ContactAdapter.ViewHolder.OnContactClickListener() {
            @Override
            public void onClicked(final Contact contact) {
                viewModel.onContactClicked(contact);
            }

            @Override
            public void onLongClicked(final Contact contact) {
                viewModel.onDeleteContact(contact);
            }
        });
        contacts.setAdapter(adapter);
        viewModel.getContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable final List<Contact> contacts) {
                adapter.setData(contacts);
            }
        });

        viewModel.getShowDialog().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean show) {
                if (show != null && show) {
                    showInputDialog(viewModel);
                }
            }
        });

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                viewModel.clickAddContact();
            }
        });

        final View root = findViewById(R.id.root);
        viewModel.getDeletedContact().observe(this, new Observer<Contact>() {
            @Override
            public void onChanged(@Nullable final Contact contact) {
                showSnackbar(root, viewModel, contact);
            }
        });
    }

    private void showInputDialog(final ContactsViewModel viewModel) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View                view          = LayoutInflater.from(this).inflate(R.layout.input_dialog, null);
        dialogBuilder.setView(view);

        final AlertDialog alertDialog   = dialogBuilder.create();
        final EditText    nameInput     = view.findViewById(R.id.name_input);
        final EditText    surenameInput = view.findViewById(R.id.surename_input);

        view.findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Contact contact = new Contact();
                contact.setName(nameInput.getText().toString().trim());
                contact.setSurename(surenameInput.getText().toString().trim());

                viewModel.createContact(contact);

                alertDialog.hide();
            }
        });
        alertDialog.show();
    }

    private void showSnackbar(View view, final ContactsViewModel viewModel, final Contact contact) {
        Snackbar.make(view, contact.getName() + " deleted.", Snackbar.LENGTH_SHORT)
                .addCallback(
                        new Snackbar.Callback() {
                            @Override
                            public void onDismissed(final Snackbar transientBottomBar, final int event) {
                                super.onDismissed(transientBottomBar, event);
                                viewModel.doDelete(contact);
                            }
                        }
                )
                .setAction(
                        "Undo",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(final View v) {
                                viewModel.restoreContact(contact);
                            }
                        })
                .show();
    }
}
