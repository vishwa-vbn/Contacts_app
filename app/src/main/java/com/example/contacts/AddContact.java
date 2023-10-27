//package com.example.contacts;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.List;
//
//public class AddContact extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    private MyAdapter3 adapter;
//    private List<Contact> contactList;
//    private ContactsDatabaseManager databaseManager;
//    private long groupId; // Store the group ID to add selected contacts
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_contact);
//
//        Button cancelBtn = findViewById(R.id.cancelButton);
//        Button saveBtn = findViewById(R.id.createButton);
//
//        recyclerView = findViewById(R.id.addContactRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        databaseManager = new ContactsDatabaseManager(this);
//        databaseManager.open();
//
//        // Fetch contacts from the database
//        contactList = databaseManager.getAllContacts();
//
//        // Initialize the adapter and set it to the RecyclerView
//        adapter = new MyAdapter3(this, contactList);
//        recyclerView.setAdapter(adapter);
//
//        saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Save selected contacts to the group
//                saveSelectedContactsToGroup();
//                finish();
//            }
//        });
//
//        cancelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    private void saveSelectedContactsToGroup() {
//        // Create a new group and get its ID
//        groupId = databaseManager.insertContactToGroup("My New Group");
//
//        // Get the selected contact IDs from the adapter
//        List<Integer> selectedContactIds = adapter.getSelectedContactIds();
//
//        // Save selected contacts to the group
//        for (int contactId : selectedContactIds) {
//            databaseManager.insertContactToGroup(contactId, groupId);
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        databaseManager.close();
//    }
//}
