//package com.example.contacts;
//
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import android.content.Intent;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class IndividualCallHistory extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private CallHistoryAdapter adapter;
//    private List<CallHistoryItem> callHistoryList;
//    String contactPhone;
//    String contactName;
//    long contactId;
//
//    private ImageView callIcon;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_individual_call_history);
//        recyclerView = findViewById(R.id.recyclerView);
//        TextView nameTextView = findViewById(R.id.contactNameTextView);
//        TextView phoneTextView = findViewById(R.id.contactNumberTextView);
//
//        TextView contactIdTextView = findViewById(R.id.contact_id_text_view);
//        callIcon = findViewById(R.id.callIconImageView);
//
//
//        // Get the call logs for the contact (replace with the actual contact ID)
//        List<CallHistoryItem> callLogs = contactsDatabaseManager.getCallLogsForContact(contactId);
//
//        // Create and set the adapter
//        CallHistoryAdapter adapter = new CallHistoryAdapter(this, callLogs);
//        recyclerView.setAdapter(adapter);
//
//
//
//
//
//        // Retrieve contact information from the intent
//        Intent intent = getIntent();
//        if (intent != null) {
//            contactName = intent.getStringExtra("contact_name");
//            contactPhone = intent.getStringExtra("contact_phone");
//
//
//            contactId = getIntent().getLongExtra("contact_id", -1);
//
//            // Find the TextView in your XML layout
//
//            // Set the text to display the contact ID
//            contactIdTextView.setText("Contact ID: " + contactId);
//
//            // Display the contact information in your layout
//            if (contactName != null) {
//                nameTextView.setText(contactName);
//            }
//
//            if (contactPhone != null) {
//                phoneTextView.setText(contactPhone);
//            }
//        }
//
//
//        callIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Intent intent = new Intent(IndividualCallHistory.this, CallOutGoing.class);
//
//                // Pass the contact details to the IndividualCallHistory activity
//                intent.putExtra("contact_id", contactId);
//                intent.putExtra("contact_name", contactName);
//                intent.putExtra("contact_phone", contactPhone);
//
//                startActivity(intent);
//
//            }
//        });
//    }
//}
//









package com.example.contacts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class IndividualCallHistory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CallHistoryAdapter adapter;
    private List<CallHistoryItem> callHistoryList;
    String contactPhone;
    String contactName;
    long contactId;
    private ImageView callIcon;
    private ImageButton deleteButton;
    private Button back_btn;

    int currentIsDeleted;

    private ImageView profileImage;
    private ContactsDatabaseManager contactsDatabaseManager;
    private ContactsDatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_call_history);
        getSupportActionBar().hide();
        back_btn= findViewById(R.id.back_button);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Initialize the ContactsDatabaseManager
        contactsDatabaseManager = new ContactsDatabaseManager(this).open();

        recyclerView = findViewById(R.id.recyclerView);
        TextView nameTextView = findViewById(R.id.contactNameTextView);
        TextView phoneTextView = findViewById(R.id.contactNumberTextView);
        //TextView contactIdTextView = findViewById(R.id.contact_id_text_view);
        callIcon = findViewById(R.id.callIconImageView);

        profileImage = findViewById(R.id.profileImage);

        databaseManager = new ContactsDatabaseManager(this);
        databaseManager.open();

        currentIsDeleted = databaseManager.getCurrentIsDeleted(contactId);
        Log.d("ContactInfosss","ID: " + currentIsDeleted);
        //delete button code
        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteConfirmationDialog();
            }
        });

        // Retrieve contact information from the intent
        Intent intent = getIntent();
        if (intent != null) {
            contactName = intent.getStringExtra("contact_name");
            contactPhone = intent.getStringExtra("contact_phone");
            //contactId = getIntent().getLongExtra("contact_id", -1);

            //NEW,fecthing the is_deleted using contact id
            currentIsDeleted = databaseManager.getCurrentIsDeleted(contactId);

            // Create a circular profile image and set it to the ImageView
            Drawable circularDrawable = getCircularTextDrawable(contactName.substring(0, 1));
            profileImage.setImageDrawable(circularDrawable);

            // Find the TextView in your XML layout

            // Set the text to display the contact ID
            //contactIdTextView.setText("Contact ID: " + contactId);

            // Display the contact information in your layout
            if (contactName != null) {
                nameTextView.setText(contactName);
            }

            if (contactPhone != null) {
                phoneTextView.setText(contactPhone);
            }
        }

        callIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndividualCallHistory.this, CallOutGoing.class);

                // Pass the contact details to the IndividualCallHistory activity
                intent.putExtra("contact_id", contactId);
                intent.putExtra("contact_name", contactName);
                intent.putExtra("contact_phone", contactPhone);
                //NEW
                intent.putExtra("contact_is_deleted", currentIsDeleted);

                startActivity(intent);
            }
        });

        // Get the call logs for the contact (replace with the actual contact ID)
        List<CallHistoryItem> callLogs = contactsDatabaseManager.getCallLogsForContact(contactId);


        // Initialize the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create and set the adapter
        adapter = new CallHistoryAdapter(this, callLogs);
        recyclerView.setAdapter(adapter);
    }

    //NEW,user-defined function for delete button
    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this contact?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        long contactIdToDelete = contactId;
                        databaseManager.updateIsDeleted(contactIdToDelete,currentIsDeleted);
                        databaseManager.close();
                        //Toast.makeText(IndividualCallHistory.this,"the contact id is = "+contactId,Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User canceled the dialog, do nothing
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    private Drawable getCircularTextDrawable(String text) {
        Bitmap bitmap = Bitmap.createBitmap(120, 120, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(getRandomColor());
        canvas.drawCircle(60, 60, 60, paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(60);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);

        float x = canvas.getWidth() / 2f;
        float y = (canvas.getHeight() / 2f) - ((paint.descent() + paint.ascent()) / 2);
        canvas.drawText(text, x, y, paint);

        return new BitmapDrawable(getResources(), bitmap);
    }

    private int getRandomColor() {
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseManager != null) {
            databaseManager.close();
        }

        // Close the database connection when the activity is destroyed
        contactsDatabaseManager.close();
    }
}
