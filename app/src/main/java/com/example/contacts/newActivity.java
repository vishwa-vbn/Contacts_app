package com.example.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.DatePicker;

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


    private boolean isFavorite = false; // Initialize isFavorite as false (not set)
    private int groupId = 0; // Initialize groupId as -1 (not set)

    private DatePicker datePickerBirthday;
    private String date;

    private ContactsDatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        getSupportActionBar().hide();
        back_btn= findViewById(R.id.back_button);



        databaseManager = new ContactsDatabaseManager(this).open();

        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        phoneNumberEditText = findViewById(R.id.phoneNumber);
        emailEditText = findViewById(R.id.email);
        addressEditText = findViewById(R.id.address);
        notesEditText = findViewById(R.id.note);
        phoneTypeSpinner = findViewById(R.id.spinnerContactType);
        dateLabelSpinner = findViewById(R.id.dateTypes);



        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        // Update selectedDate whenever the date is changed
                        date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                    }
                }
        );

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNewContact();
            }
        });
    }

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
                address, notes, isFavorite, groupId
        );

        if (result != -1) {


            finish();



        } else {
            // Error while saving the contact
            Toast.makeText(this, "Error saving the contact", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseManager.close();
    }
}
