//package com.example.contacts;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class groupcreation extends AppCompatActivity {
//
//    private EditText groupNameEditText;
//    private RecyclerView recyclerView;
//    private Button saveButton;
//    private MyAdapter adapter;
//    private ContactsDatabaseManager databaseManager;
//    private List<Contact> contacts;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_groupcreation);
//
//        groupNameEditText = findViewById(R.id.groupNameEditText);
//        recyclerView = findViewById(R.id.contactsRecyclerView);
//        saveButton = findViewById(R.id.saveButton);
//
//        // Initialize your database manager and open the connection
//        databaseManager = new ContactsDatabaseManager(this);
//        databaseManager.open();
//
//        // Fetch all contacts from the database
//        contacts = databaseManager.getAllContacts();
//
//        // Initialize the adapter with fetched contacts data using MyAdapter
//        adapter = new MyAdapter(this, contacts, R.layout.item_contact, new MyAdapter.OnContactClickListener() {
//            @Override
//            public void onContactClick(Contact contact) {
//                // Handle contact click here
//            }
//        });
//
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get the selected contacts
//                List<Contact> selectedContacts = adapter.getSelectedContacts();
//
//                // Get the group name from the EditText
//                String groupName = groupNameEditText.getText().toString().trim();
//
//                if (groupName.isEmpty()) {
//                    Toast.makeText(groupcreation.this, "Group name cannot be empty", Toast.LENGTH_SHORT).show();
//                } else if (selectedContacts.isEmpty()) {
//                    Toast.makeText(groupcreation.this, "Please select at least one contact", Toast.LENGTH_SHORT).show();
//                } else {
//                    // Save the group to the database and get the newly created group ID
//                    long groupId = saveGroupToDatabase(groupName);
//
//                    // Associate selected contacts with the group in the database
//                    saveContactsToGroup(selectedContacts, groupId);
//
//                    // Finish the activity or navigate to the main contacts list
//                    finish();
//                }
//            }
//        });
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // Close the database connection in onDestroy
//        databaseManager.close();
//    }
//
//    private long saveGroupToDatabase(String groupName) {
//        // Insert the group and get the newly created group ID
//        return databaseManager.insertGroup(groupName);
//    }
//
//    private void saveContactsToGroup(List<Contact> selectedContacts, long groupId) {
//        // Associate selected contacts with the group in the database
//        for (Contact contact : selectedContacts) {
//            long contactId = contact.getId();
//            databaseManager.insertContactToGroup(contactId, groupId);
//        }
//    }
//}




package com.example.contacts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class groupcreation extends AppCompatActivity {

    private EditText groupNameEditText;
    private RecyclerView recyclerView;
    private Button saveButton;
    private MyAdapter adapter;
    private ContactsDatabaseManager databaseManager;
    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupcreation);
        getSupportActionBar().hide();

        groupNameEditText = findViewById(R.id.groupNameEditText);
        recyclerView = findViewById(R.id.contactsRecyclerView);
        saveButton = findViewById(R.id.saveButton);

        // Initialize your database manager and open the connection
        databaseManager = new ContactsDatabaseManager(this);
        databaseManager.open();

        // Fetch all contacts from the database
        contacts = databaseManager.getAllContacts();


        ContactsDatabaseManager databaseManager = new ContactsDatabaseManager(this);
        databaseManager.open();


        // Initialize the adapter with fetched contacts data using MyAdapter
        adapter = new MyAdapter(this, contacts, R.layout.item_contact, new MyAdapter.OnContactClickListener() {
            @Override
            public void onContactClick(Contact contact) {
                // Handle contact click here
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Check if in edit mode
        boolean editMode = getIntent().getBooleanExtra("editMode", false);
        if (editMode) {
            long groupId = getIntent().getLongExtra("groupId", -1);
            String groupName = getIntent().getStringExtra("groupName");

            groupNameEditText.setText(groupName);
            // Retrieve existing members of the group
            List<Contact> existingGroupMembers = databaseManager.getExistingGroupMembers(groupId);

            // Update the isSelected property of existing members
            updateExistingMembersSelection(existingGroupMembers);

            // Notify the adapter that the data has changed
            adapter.notifyDataSetChanged();
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected contacts
                List<Contact> selectedContacts = adapter.getSelectedContacts();

                // Get the group name from the EditText
                String groupName = groupNameEditText.getText().toString().trim();

                if (groupName.isEmpty()) {
                    Toast.makeText(groupcreation.this, "Group name cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (selectedContacts.isEmpty()) {
                    Toast.makeText(groupcreation.this, "Please select at least one contact", Toast.LENGTH_SHORT).show();
                } else {
                    // Save the group to the database and get the newly created group ID
                    long groupId = saveGroupToDatabase(groupName);

                    // Associate selected contacts with the group in the database
                    saveContactsToGroup(selectedContacts, groupId);

                    // Finish the activity or navigate to the main contacts list
                    finish();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection in onDestroy
        databaseManager.close();
    }

    private long saveGroupToDatabase(String groupName) {
        // Insert the group and get the newly created group ID
        return databaseManager.insertGroup(groupName);
    }



    // Method to retrieve existing members of the group
    private List<Contact> getExistingGroupMembers(long groupId) {
        List<Contact> existingMembers = new ArrayList<>();

        // You should implement this method to query the database and retrieve existing group members based on groupId.

        return existingMembers;
    }

    // Method to update the isSelected property of existing members
    private void updateExistingMembersSelection(List<Contact> existingGroupMembers) {
        for (Contact existingMember : existingGroupMembers) {
            for (Contact contact : contacts) {
                if (existingMember.getId() == contact.getId()) {
                    contact.setSelected(true);
                }
            }
        }
    }
    private void saveContactsToGroup(List<Contact> selectedContacts, long groupId) {
        // Associate selected contacts with the group in the database
        for (Contact contact : selectedContacts) {
            long contactId = contact.getId();
            databaseManager.insertContactToGroup(contactId, groupId);
        }
    }



}
