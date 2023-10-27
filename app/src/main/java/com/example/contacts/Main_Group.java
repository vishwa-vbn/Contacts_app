////package com.example.contacts;
////
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.recyclerview.widget.LinearLayoutManager;
////import androidx.recyclerview.widget.RecyclerView;
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.view.View;
////import android.widget.Button;
////import android.widget.ImageView;
////
////import java.util.List;
////
////public class Main_Group extends AppCompatActivity {
////
////    private RecyclerView recyclerView;
////    private MyAdapter2 adapter; // Use MyAdapter2 here
////    private ImageView editIcon;
////    private Button    create_btn;
////
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main_group);
////
////        // Initialize the RecyclerView
////        recyclerView = findViewById(R.id.recycler_view);
////        create_btn = findViewById(R.id.Create_group_btn);
////        editIcon = findViewById(R.id.editIcon);
////
////
////        create_btn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////
////                Intent i= new Intent(Main_Group.this, groupcreation.class);
////                startActivity(i);
////            }
////        });
////
////
////
////        // Create an instance of ContactsDatabaseManager and open the database
////        ContactsDatabaseManager databaseManager = new ContactsDatabaseManager(this);
////        databaseManager.open();
////
////        List<Group> groups = databaseManager.getGroups();
////
////        // Initialize the adapter and set it on the RecyclerView
////        adapter = new MyAdapter2(this, groups, new MyAdapter2.OnGroupClickListener() {
////            @Override
////            public void onGroupClick(String groupName) {
////                // Handle group item click here
////                // You can use the groupName as needed
////            }
////        });
////
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        recyclerView.setAdapter(adapter);
////
////        // Close the database when done
////        databaseManager.close();
////    }
////
////
////
////}
//
//
//
//
//
//
//
//
//
//
//package com.example.contacts;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import java.util.List;
//
//public class Main_Group extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private MyAdapter2 adapter;
//    private Button create_btn;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_group);
//
//        recyclerView = findViewById(R.id.recycler_view);
//        create_btn = findViewById(R.id.Create_group_btn);
//
//        create_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Main_Group.this, groupcreation.class);
//                startActivity(i);
//            }
//        });
//
//        ContactsDatabaseManager databaseManager = new ContactsDatabaseManager(this);
//        databaseManager.open();
//
//        List<Group> groups = databaseManager.getGroups();
//
//        adapter = new MyAdapter2(this, groups, new MyAdapter2.OnGroupClickListener() {
//            @Override
//            public void onGroupClick(String groupName) {
//                // Handle group item click here
//                // You can use the groupName as needed
//            }
//        }, new MyAdapter2.OnEditIconClickListener() {
//            @Override
//            public void onEditIconClick(Group group) {
//                // Handle the edit icon click here
//                launchGroupCreationActivityForEdit(group);
//            }
//        }, new MyAdapter2.OnDeleteIconClickListener() {
//            @Override
//            public void onDeleteIconClick(Group group) {
//                showDeleteGroupConfirmationDialog(group);
//            }
//        });
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//
//        // Close the database when done
//        databaseManager.close();
//    }
//
//
//    private void showDeleteGroupConfirmationDialog(Group group) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Delete Group");
//        builder.setMessage("Are you sure you want to delete this group?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // Call the deleteGroup method with the group's ID to delete it
//                deleteGroup(group.getId());
//                // Update the RecyclerView to reflect the changes
//                updateRecyclerView();
//            }
//        });
//        builder.setNegativeButton("Cancel", null);
//        builder.show();
//    }
//
//    private void deleteGroup(long groupId) {
//        ContactsDatabaseManager databaseManager = new ContactsDatabaseManager(this);
//        databaseManager.open();
//        databaseManager.deleteGroup(groupId);
//        databaseManager.close();
//    }
//
//    private void updateRecyclerView() {
//        // Fetch the updated list of groups from the database and update the RecyclerView
//        List<Group> updatedGroups = getUpdatedGroups();
//        adapter.updateData(updatedGroups);
//        adapter.notifyDataSetChanged();
//    }
//
//    private List<Group> getUpdatedGroups() {
//        // Fetch the updated list of groups from the database
//        ContactsDatabaseManager databaseManager = new ContactsDatabaseManager(this);
//        databaseManager.open();
//        List<Group> updatedGroups = databaseManager.getGroups();
//        databaseManager.close();
//        return updatedGroups;
//    }
//    private void launchGroupCreationActivityForEdit(Group group) {
//        Intent intent = new Intent(Main_Group.this, groupcreation.class);
//        intent.putExtra("editMode", true);
//        intent.putExtra("groupId", group.getId());
//        intent.putExtra("groupName", group.getGroupName());
//        startActivity(intent);
//    }
//}




package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Main_Group extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter2 adapter;
    private Button create_btn,back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_group);
        getSupportActionBar().hide();


        recyclerView = findViewById(R.id.recycler_view);
        create_btn = findViewById(R.id.create_group_btn);
        back_btn= findViewById(R.id.back_button);


        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main_Group.this, groupcreation.class);
                startActivity(i);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ContactsDatabaseManager databaseManager = new ContactsDatabaseManager(this);
        databaseManager.open();

        List<Group> groups = databaseManager.getGroups();

        adapter = new MyAdapter2(this, groups, new MyAdapter2.OnGroupClickListener() {
            @Override
            public void onGroupClick(String groupName) {
                // Handle group item click here
                // You can use the groupName as needed
            }
        }, new MyAdapter2.OnEditIconClickListener() {
            @Override
            public void onEditIconClick(Group group) {
                // Handle the edit icon click here
                launchGroupCreationActivityForEdit(group);
            }
        }, new MyAdapter2.OnDeleteIconClickListener() {
            @Override
            public void onDeleteIconClick(Group group) {
                showDeleteGroupConfirmationDialog(group);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Close the database when done
        databaseManager.close();
    }

//    private void showDeleteGroupConfirmationDialog(Group group) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Delete Group");
//        builder.setMessage("Are you sure you want to delete this group?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // Call the deleteGroup method with the group's ID to delete it
//                deleteGroup(group.getId());
//                // Update the RecyclerView to reflect the changes
//                updateRecyclerView();
//            }
//        });
//        builder.setNegativeButton("Cancel", null);
//        builder.show();
//    }



    private void showDeleteGroupConfirmationDialog(Group group) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the custom layout for the dialog
        View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        builder.setView(dialogView);

        TextView titleTextView = dialogView.findViewById(R.id.dialog_title);
        TextView messageTextView = dialogView.findViewById(R.id.dialog_message);
        Button positiveButton = dialogView.findViewById(R.id.dialog_button_positive);
        Button negativeButton = dialogView.findViewById(R.id.dialog_button_negative);

        titleTextView.setText("Delete Group");
        messageTextView.setText("Are you sure you want to delete this group?");

        // Create the dialog
        AlertDialog dialog = builder.create();

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the deleteGroup method with the group's ID to delete it
                deleteGroup(group.getId());
                // Update the RecyclerView to reflect the changes
                updateRecyclerView();

                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Show the dialog
        dialog.show();
    }


    private void deleteGroup(long groupId) {
        ContactsDatabaseManager databaseManager = new ContactsDatabaseManager(this);
        databaseManager.open();
        databaseManager.deleteGroup(groupId);
        databaseManager.close();
    }

    private void updateRecyclerView() {
        // Fetch the updated list of groups from the database and update the RecyclerView
        List<Group> updatedGroups = getUpdatedGroups();
        adapter.updateData(updatedGroups);
        adapter.notifyDataSetChanged();
    }

    private List<Group> getUpdatedGroups() {
        // Fetch the updated list of groups from the database
        ContactsDatabaseManager databaseManager = new ContactsDatabaseManager(this);
        databaseManager.open();
        List<Group> updatedGroups = databaseManager.getGroups();
        databaseManager.close();
        return updatedGroups;
    }

    private void launchGroupCreationActivityForEdit(Group group) {
        Intent intent = new Intent(Main_Group.this, groupcreation.class);
        intent.putExtra("editMode", true);
        intent.putExtra("groupId", group.getId());
        intent.putExtra("groupName", group.getGroupName());
        startActivity(intent);
    }
}
