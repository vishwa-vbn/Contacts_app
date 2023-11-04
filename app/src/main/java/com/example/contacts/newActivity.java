//package com.example.contacts;
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.Toast;
//import android.widget.DatePicker;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class newActivity extends AppCompatActivity {
//    private EditText firstNameEditText;
//    private EditText lastNameEditText;
//    private EditText phoneNumberEditText;
//    private EditText emailEditText;
//    private EditText addressEditText;
//    private EditText notesEditText;
//    private Spinner phoneTypeSpinner;
//    private Spinner dateLabelSpinner;
//    private Button back_btn;
//    private boolean isFavorite = false;
//    private int groupId = 0;
//    private DatePicker datePickerBirthday;
//    private String date;
//    private ContactsDatabaseManager databaseManager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_new);
//        getSupportActionBar().hide();
//        back_btn = findViewById(R.id.back_button);
//
//        databaseManager = new ContactsDatabaseManager(this).open();
//
//        firstNameEditText = findViewById(R.id.firstName);
//        lastNameEditText = findViewById(R.id.lastName);
//        phoneNumberEditText = findViewById(R.id.phoneNumber);
//        emailEditText = findViewById(R.id.email);
//        addressEditText = findViewById(R.id.address);
//        notesEditText = findViewById(R.id.note);
//        phoneTypeSpinner = findViewById(R.id.spinnerContactType);
//        dateLabelSpinner = findViewById(R.id.dateTypes);
//
//        back_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        datePickerBirthday = findViewById(R.id.datePickerBirthday);
//
//        datePickerBirthday.init(
//                datePickerBirthday.getYear(),
//                datePickerBirthday.getMonth(),
//                datePickerBirthday.getDayOfMonth(),
//                new DatePicker.OnDateChangedListener() {
//                    @Override
//                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
//                    }
//                }
//        );
//
//        Button saveButton = findViewById(R.id.saveButton);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                saveNewContact();
//            }
//        });
//    }
//
//    private void saveNewContact() {
//        String firstName = firstNameEditText.getText().toString();
//        String lastName = lastNameEditText.getText().toString();
//        String phoneNumber = phoneNumberEditText.getText().toString();
//        String email = emailEditText.getText().toString();
//        String address = addressEditText.getText().toString();
//        String notes = notesEditText.getText().toString();
//        String phoneType = phoneTypeSpinner.getSelectedItem().toString();
//        String dateLabel = dateLabelSpinner.getSelectedItem().toString();
//
//        long result = databaseManager.insertContact(
//                firstName, lastName, phoneNumber, phoneType, email, date, dateLabel,
//                address, notes, isFavorite, groupId, null // Remove profilePicturePath
//        );
//
//        if (result != -1) {
//            finish();
//        } else {
//            Toast.makeText(this, "Error saving the contact", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        databaseManager.close();
//    }
//}



package com.example.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.DatePicker;
import androidx.appcompat.app.AppCompatActivity;

public class newActivity extends AppCompatActivity {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneNumberEditText;
    private EditText emailEditText;
    private EditText addressEditText;
    private EditText notesEditText;
    private Spinner phoneTypeSpinner;
    private Spinner dateLabelSpinner;
    private Button saveButton;  // Renamed the button to saveButton
    private Button back_btn;
    private boolean isFavorite = false;
    private int groupId = 0;
    private DatePicker datePickerBirthday;
    private String date;
    private ContactsDatabaseManager databaseManager;
    private boolean isEditing = false; // Flag to check if editing an existing contact

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        getSupportActionBar().hide();
        back_btn = findViewById(R.id.back_button);

        // Open the database manager
        databaseManager = new ContactsDatabaseManager(this).open();

        // Find views for UI components
        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        phoneNumberEditText = findViewById(R.id.phoneNumber);
        emailEditText = findViewById(R.id.email);
        addressEditText = findViewById(R.id.address);
        notesEditText = findViewById(R.id.note);
        phoneTypeSpinner = findViewById(R.id.spinnerContactType);
        dateLabelSpinner = findViewById(R.id.dateTypes);
        datePickerBirthday = findViewById(R.id.datePickerBirthday);

        // Handle the back button
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Retrieve the contact ID from the intent
        Intent intent = getIntent();
        long contactId = intent.getLongExtra("contact_id", -1);

        if (contactId != -1) {
            isEditing = true; // Set the flag to indicate editing an existing contact
            // Fetch the existing contact details using the contact ID
            Contact contact = databaseManager.getContactById(contactId);

            // Populate the UI components with the existing contact details
            firstNameEditText.setText(contact.getFirstName());
            lastNameEditText.setText(contact.getLastName());
            phoneNumberEditText.setText(contact.getPhoneNumber());
            emailEditText.setText(contact.getEmail());
            addressEditText.setText(contact.getAddress());
            notesEditText.setText(contact.getNotes());

            // You may need to implement a method to set the appropriate selection in the spinners
            // based on the existing contact details.

            // Handle the date if available in the contact
            if (contact.getDate() != null) {
                String[] dateParts = contact.getDate().split("-");
                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]) - 1;
                int day = Integer.parseInt(dateParts[2]);
                datePickerBirthday.init(year, month, day, null);
            }
        }

        // Handle the save/update button click
        saveButton = findViewById(R.id.saveButton);
        if (isEditing) {
            saveButton.setText("Update"); // Change the button text to "Update" in edit mode
        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEditing) {
                    updateContact(contactId); // Call the update method if in edit mode
                } else {
                    saveNewContact(); // Call the save method if creating a new contact
                }
            }
        });
    }

    private void updateContact(long contactId) {
        // Code to update an existing contact goes here

        // Extract the updated contact information from the UI components
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String notes = notesEditText.getText().toString();
        String phoneType = phoneTypeSpinner.getSelectedItem().toString();
        String dateLabel = dateLabelSpinner.getSelectedItem().toString();

        // Extract the date from the DatePicker
        int year = datePickerBirthday.getYear();
        int month = datePickerBirthday.getMonth() + 1;
        int day = datePickerBirthday.getDayOfMonth();
        String date = year + "-" + month + "-" + day;

        // Update the contact in the database
        int rowsUpdated = databaseManager.updateContact(contactId, firstName, lastName, phoneNumber, phoneType, email, date, dateLabel, address, notes, isFavorite, groupId);

        if (rowsUpdated > 0) {
            Toast.makeText(this, "Contact updated successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error updating the contact", Toast.LENGTH_SHORT).show();
        }

    }

    private void saveNewContact() {
        // Code to save a new contact goes here

        // Extract the contact information from the UI components
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String notes = notesEditText.getText().toString();
        String phoneType = phoneTypeSpinner.getSelectedItem().toString();
        String dateLabel = dateLabelSpinner.getSelectedItem().toString();

        // Extract the date from the DatePicker
        int year = datePickerBirthday.getYear();
        int month = datePickerBirthday.getMonth() + 1;
        int day = datePickerBirthday.getDayOfMonth();
        String date = year + "-" + month + "-" + day;

        // Save the new contact to the database
        long result = databaseManager.insertContact(
                firstName, lastName, phoneNumber, phoneType, email, date, dateLabel,
                address, notes, isFavorite, groupId, null // Remove profilePicturePath
        );

        if (result != -1) {
            Toast.makeText(this, "Contact saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error saving the contact", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database manager
        databaseManager.close();
    }
}
