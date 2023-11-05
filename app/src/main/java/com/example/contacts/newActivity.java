//package com.example.contacts;
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
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
//                if (isInputValid()) {
//                    saveNewContact();
//                } else {
//                    // Handle the case where input is not valid (e.g., show an error message)
//                }
//            }
//        });
//    }
//
//    private boolean isInputValid() {
//        String firstName = firstNameEditText.getText().toString().trim();
//        String lastName = lastNameEditText.getText().toString().trim();
//        String phoneNumber = phoneNumberEditText.getText().toString().trim();
//
//        if (firstName.isEmpty()) {
//            showCustomToast("First Name is required");
//            //Toast.makeText(newActivity.this, "First Name is required", Toast.LENGTH_SHORT).show();
//            return false;
//        } else if (lastName.isEmpty()) {
//            showCustomToast("Last Name is required");
//            //Toast.makeText(newActivity.this, "Last Name is required", Toast.LENGTH_SHORT).show();
//            return false;
//        } else if (phoneNumber.length() != 10) {
//            showCustomToast("Phone Number must have 10 digits");
//            //Toast.makeText(newActivity.this, "Phone Number must have 10 digits", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return true;
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
//    private void showCustomToast(String message) {
//        LayoutInflater inflater = getLayoutInflater();
//        View customToastView = inflater.inflate(R.layout.custom_toast, null);
//        TextView messageTextView = customToastView.findViewById(R.id.messageTextView);
//        messageTextView.setText(message);
//
//        Toast customToast = new Toast(getApplicationContext());
//        customToast.setView(customToastView);
//        customToast.setDuration(Toast.LENGTH_SHORT);
//        customToast.show();
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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
    private Button back_btn;
    private boolean isFavorite = false;
    private int groupId = 0;
    private DatePicker datePickerBirthday;
    private String date;
    private ContactsDatabaseManager databaseManager;

    // Define a variable to store the contact ID
    private long contactId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        getSupportActionBar().hide();
        back_btn = findViewById(R.id.back_button);

        databaseManager = new ContactsDatabaseManager(this).open();

        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        phoneNumberEditText = findViewById(R.id.phoneNumber);
        emailEditText = findViewById(R.id.email);
        addressEditText = findViewById(R.id.address);
        notesEditText = findViewById(R.id.note);
        phoneTypeSpinner = findViewById(R.id.spinnerContactType);
        dateLabelSpinner = findViewById(R.id.dateTypes);

        // Check if a contact ID is passed via intent
        Intent intent = getIntent();
        if (intent.hasExtra("contact_id")) {
            // If contact ID is passed, fetch contact details and populate the fields
            contactId = intent.getLongExtra("contact_id", -1);
            if (contactId != -1) {
                populateContactFields(contactId);
            }
        }

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                finish();
            }
        });

        datePickerBirthday = findViewById(R.id.datePickerBirthday);

        datePickerBirthday.init(
                datePickerBirthday.getYear(),
                datePickerBirthday.getMonth(),
                datePickerBirthday.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                    }
                }
        );

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setText(contactId != -1 ? "Update" : "Save"); // Change button text based on the mode

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInputValid()) {
                    if (contactId != -1) {
                        // Update existing contact
                        updateContact(contactId);
                    } else {
                        // Save new contact
                        saveNewContact();
                    }
                } else {
                    // Handle the case where input is not valid (e.g., show an error message)
                }
            }
        });
    }

    // Function to populate the contact details in the fields
    private void populateContactFields(long contactId) {
        Contact contact = databaseManager.getContactById(contactId);
        if (contact != null) {
            firstNameEditText.setText(contact.getFirstName());
            lastNameEditText.setText(contact.getLastName());
            phoneNumberEditText.setText(contact.getPhoneNumber());
            emailEditText.setText(contact.getEmail());
            addressEditText.setText(contact.getAddress());
            notesEditText.setText(contact.getNotes());
            // You should handle the spinners accordingly
            // Example: phoneTypeSpinner.setSelection(getIndex(phoneTypeSpinner, contact.getPhoneType()));
            // Example: dateLabelSpinner.setSelection(getIndex(dateLabelSpinner, contact.getDateLabel()));
        }
    }

    // Function to update an existing contact
    private void updateContact(long contactId) {
        // Get updated field values
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String notes = notesEditText.getText().toString();
        String phoneType = phoneTypeSpinner.getSelectedItem().toString();
        String dateLabel = dateLabelSpinner.getSelectedItem().toString();

        // Update the contact in the database
        int result = databaseManager.updateContact(contactId, firstName, lastName, phoneNumber, phoneType, email, date, dateLabel, address, notes, isFavorite, groupId);

        if (result > 0) {
            finish();
        } else {
            Toast.makeText(this, "Error updating the contact", Toast.LENGTH_SHORT).show();
        }
    }

    // Function to check input validity
    private boolean isInputValid() {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String phoneNumber = phoneNumberEditText.getText().toString().trim();

        if (firstName.isEmpty()) {
            showCustomToast("First Name is required");
            return false;
        } else if (lastName.isEmpty()) {
            showCustomToast("Last Name is required");
            return false;
        } else if (phoneNumber.length() != 10) {
            showCustomToast("Phone Number must have 10 digits");
            return false;
        }
        return true;
    }

    // Function to save a new contact
    private void saveNewContact() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String notes = notesEditText.getText().toString();
        String phoneType = phoneTypeSpinner.getSelectedItem().toString();
        String dateLabel = dateLabelSpinner.getSelectedItem().toString();

        long result = databaseManager.insertContact(
                firstName, lastName, phoneNumber, phoneType, email, date, dateLabel,
                address, notes, isFavorite, groupId, null
        );

        if (result != -1) {
            finish();
        } else {
            Toast.makeText(this, "Error saving the contact", Toast.LENGTH_SHORT).show();
        }
    }

    // Function to show a custom toast
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
        databaseManager.close();
    }
}
