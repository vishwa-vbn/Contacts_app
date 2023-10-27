package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TrashPage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactsAdapter adapter;
    private ContactsDatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash_page);

        recyclerView = findViewById(R.id.recyclerViewTrashPage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseManager = new ContactsDatabaseManager(this);
        databaseManager.open();

        List<Contact> deletedContacts = databaseManager.getDeletedContacts();

        adapter = new ContactsAdapter(this, deletedContacts, R.layout.activity_trash_page,null);

        recyclerView.setAdapter(adapter);

        databaseManager.close();
    }
}