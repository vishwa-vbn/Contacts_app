package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TrashPage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactsAdapter adapter;
    private ContactsDatabaseManager databaseManager;
    Button cancelButton,back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash_page);
        getSupportActionBar().hide();
        back_btn= findViewById(R.id.back_button);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView = findViewById(R.id.recyclerViewTrashPage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseManager = new ContactsDatabaseManager(this);
        databaseManager.open();

        List<Contact> deletedContacts = databaseManager.getDeletedContacts();

        adapter = new ContactsAdapter(this, deletedContacts, R.layout.activity_trash_page,null);

        recyclerView.setAdapter(adapter);

        cancelButton = findViewById(R.id.Delete_Button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TrashPage.this,MainActivity.class);
                startActivity(i);
            }
        });

        databaseManager.close();
    }
}