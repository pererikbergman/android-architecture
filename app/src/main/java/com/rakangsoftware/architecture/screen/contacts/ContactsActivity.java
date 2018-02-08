package com.rakangsoftware.architecture.screen.contacts;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rakangsoftware.architecture.R;
import com.rakangsoftware.architecture.data.Contact;

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
                // Call on ViewModel
            }
        });
    }

    private void showSnackbar(View view) {
        Snackbar.make(view, "Hellu Snackbar!", Snackbar.LENGTH_SHORT).show();
    }
}
