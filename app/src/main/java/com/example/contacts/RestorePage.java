package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class RestorePage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private ContactsDatabaseManager databaseManager;
    Button saveButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_page);
        recyclerView = findViewById(R.id.recyclerViewRestorePage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseManager = new ContactsDatabaseManager(this);
        databaseManager.open();

        List<Contact> deletedContacts = databaseManager.getDeletedContacts(); // Modify this method in your database manager.

//        adapter = new MyAdapter(this, deletedContacts);


        // Initialize the adapter with fetched contacts data using MyAdapter
        adapter = new MyAdapter(this, deletedContacts,R.layout.item_contacts, new MyAdapter.OnContactClickListener() {
            @Override
            public void onContactClick(Contact contact) {
                // Handle contact click here
            }
        });
        recyclerView.setAdapter(adapter);

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Contact> selectedContacts = adapter.getSelectedContacts();
                List<Long> selectedContactIds = new ArrayList<>();

                for (Contact contact : selectedContacts) {
                    selectedContactIds.add(contact.getId());
                }

                for (Long contactId : selectedContactIds) {
                    databaseManager.restoreContact(contactId);
                    Log.d("SelectedContactID", "Contact ID: " + contactId);
                }
                finish();
            }
        });
        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RestorePage.this,MainActivity.class);
                startActivity(i);
            }
        });
        databaseManager.close();
    }
}