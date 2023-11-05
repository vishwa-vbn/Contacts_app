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



//
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
//import java.util.ArrayList;
//import java.util.List;
//
//public class groupcreation extends AppCompatActivity {
//
//    private EditText groupNameEditText;
//    private RecyclerView recyclerView;
//    private Button saveButton,back_btn;
//    private MyAdapter adapter;
//    private ContactsDatabaseManager databaseManager;
//    private List<Contact> contacts;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_groupcreation);
//        getSupportActionBar().hide();
//
//        groupNameEditText = findViewById(R.id.groupNameEditText);
//        recyclerView = findViewById(R.id.contactsRecyclerView);
//        saveButton = findViewById(R.id.saveButton);
//        back_btn= findViewById(R.id.back_button);
//
//
//
//        back_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//
//        // Initialize your database manager and open the connection
//        databaseManager = new ContactsDatabaseManager(this);
//        databaseManager.open();
//
//        // Fetch all contacts from the database
//        contacts = databaseManager.getAllContacts();
//
//
//        ContactsDatabaseManager databaseManager = new ContactsDatabaseManager(this);
//        databaseManager.open();
//
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
//        // Check if in edit mode
//        boolean editMode = getIntent().getBooleanExtra("editMode", false);
//        if (editMode) {
//            long groupId = getIntent().getLongExtra("groupId", -1);
//            String groupName = getIntent().getStringExtra("groupName");
//
//            groupNameEditText.setText(groupName);
//            // Retrieve existing members of the group
//            List<Contact> existingGroupMembers = databaseManager.getExistingGroupMembers(groupId);
//
//            // Update the isSelected property of existing members
//            updateExistingMembersSelection(existingGroupMembers);
//
//            // Notify the adapter that the data has changed
//            adapter.notifyDataSetChanged();
//        }
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
//
//
//    // Method to retrieve existing members of the group
//    private List<Contact> getExistingGroupMembers(long groupId) {
//        List<Contact> existingMembers = new ArrayList<>();
//
//        // You should implement this method to query the database and retrieve existing group members based on groupId.
//
//        return existingMembers;
//    }
//
//    // Method to update the isSelected property of existing members
//    private void updateExistingMembersSelection(List<Contact> existingGroupMembers) {
//        for (Contact existingMember : existingGroupMembers) {
//            for (Contact contact : contacts) {
//                if (existingMember.getId() == contact.getId()) {
//                    contact.setSelected(true);
//                }
//            }
//        }
//    }
//    private void saveContactsToGroup(List<Contact> selectedContacts, long groupId) {
//        // Associate selected contacts with the group in the database
//        for (Contact contact : selectedContacts) {
//            long contactId = contact.getId();
//            databaseManager.insertOrUpdateContactToGroup(contactId, groupId);
//        }
//    }
//
//
//
//}




package com.example.contacts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class groupcreation extends AppCompatActivity {

    private EditText groupNameEditText;
    private RecyclerView recyclerView;
    private Button saveButton, backButton,cancelButton;
    private MyAdapter adapter;
    private ContactsDatabaseManager databaseManager;
    private List<Contact> contacts;
    private long groupId;  // Store the group ID if in edit mode

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupcreation);
        getSupportActionBar().hide();

        groupNameEditText = findViewById(R.id.groupNameEditText);
        recyclerView = findViewById(R.id.contactsRecyclerView);
        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.back_button);
        cancelButton = findViewById(R.id.cancelButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Initialize your database manager and open the connection
        databaseManager = new ContactsDatabaseManager(this);
        databaseManager.open();

        // Fetch all contacts from the database
        contacts = databaseManager.getAllContacts();

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
            groupId = getIntent().getLongExtra("groupId", -1);
            String groupName = getIntent().getStringExtra("groupName");

            groupNameEditText.setText(groupName);
            List<Contact> existingGroupMembers = databaseManager.getExistingGroupMembers(groupId);
            updateExistingMembersSelection(existingGroupMembers);
            adapter.notifyDataSetChanged();
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupName = groupNameEditText.getText().toString().trim();
                List<Contact> selectedContacts = adapter.getSelectedContacts();

                if (groupName.isEmpty()) {
                    showCustomToast("Group Name required");
                }
                else if (selectedContacts.size() < 2) {
                    showCustomToast("Select atleast 2 contacts");
                }
                else {
                    if (editMode) {
                        // Update the existing group
                        updateGroupInDatabase(groupId, groupName, adapter.getSelectedContacts());
                    } else {
                        long newGroupId = saveGroupToDatabase(groupName);
//                        List<Contact> selectedContacts = adapter.getSelectedContacts();
                        associateContactsToGroup(newGroupId, selectedContacts);//changed this line paramenter
                        // Create a new group
                        if (groupName.isEmpty()) {
                            Toast.makeText(groupcreation.this, "Group name cannot be empty", Toast.LENGTH_SHORT).show();
                        } else if (selectedContacts.size() < 2) {
                            Toast.makeText(groupcreation.this, "Please select at least 2 contacts", Toast.LENGTH_SHORT).show();
                        } else {
                            // Create a new group
//                            long newGroupId = saveGroupToDatabase(groupName);
                            associateContactsToGroup(newGroupId, selectedContacts);

                            showCustomToast("Group Created Successfully");

                            // Finish the activity or navigate to the main contacts list
                            finish();
                        }


                        showCustomToast("Group Created Successfully");
                    }
                    finish();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showCustomToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View customToastView = inflater.inflate(R.layout.custom_toast, null);
        TextView messageTextView = customToastView.findViewById(R.id.messageTextView);
        messageTextView.setText(message);

        Toast customToast = new Toast(getApplicationContext());
        customToast.setView(customToastView);
        customToast.setDuration(Toast.LENGTH_SHORT);
        customToast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection in onDestroy
        databaseManager.close();
    }

    private long saveGroupToDatabase(String groupName) {
        return databaseManager.insertGroup(groupName);
    }

    private void updateGroupInDatabase(long groupId, String groupName, List<Contact> selectedContacts) {
        databaseManager.updateGroup(groupId, groupName);
        databaseManager.clearGroupMembers(groupId);
        associateContactsToGroup(groupId, selectedContacts);
    }

    private void associateContactsToGroup(long groupId, List<Contact> selectedContacts) {
        for (Contact contact : selectedContacts) {
            databaseManager.insertContactToGroup(contact.getId(), groupId);
        }
    }

    private void updateExistingMembersSelection(List<Contact> existingGroupMembers) {
        for (Contact existingMember : existingGroupMembers) {
            for (Contact contact : contacts) {
                if (existingMember.getId() == contact.getId()) {
                    contact.setSelected(true);
                }
            }
        }
    }
}
