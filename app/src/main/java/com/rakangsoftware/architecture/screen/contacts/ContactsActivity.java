package com.rakangsoftware.architecture.screen.contacts;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rakangsoftware.architecture.R;
import com.rakangsoftware.architecture.data.Contact;

import java.util.zip.Inflater;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        RecyclerView contacts = findViewById(R.id.contact_list);
        contacts.setLayoutManager(new LinearLayoutManager(this));
        contacts.setAdapter(new ContactAdapter(new ContactAdapter.ViewHolder.OnContactClickListener() {
            @Override
            public void onClicked(final Contact contact) {
                // Call on ViewModel
            }

            @Override
            public void onLongClicked(final Contact contact) {
                // Call on ViewModel
            }
        }));

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                showInputDialog();
            }
        });
    }

    private void showInputDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.input_dialog, null);
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

                alertDialog.hide();
            }
        });
        alertDialog.show();
    }

    private void showSnackbar(View view) {
        Snackbar.make(view, "Hellu Snackbar!", Snackbar.LENGTH_SHORT).show();
    }
}
