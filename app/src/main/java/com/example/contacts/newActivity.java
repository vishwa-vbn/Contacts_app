package com.example.contacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
    private static final int PICK_IMAGE_REQUEST = 1;
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

        //NEW , PROFILE PHOTO RETRIVING PART
        FrameLayout frameLayout = findViewById(R.id.profile_photo);
        frameLayout.setClickable(true);

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*"); // Limit selection to image files
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });




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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            // Check if the request code matches the one you used and if the result is successful

            if (data != null) {
                // The selected image is returned in the 'data' intent
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    ImageView imageView4 = findViewById(R.id.imageView4);
                    imageView4.setImageURI(selectedImageUri);

                    ImageView imageView7 = findViewById(R.id.imageView7);
                    imageView7.setVisibility(View.GONE);
                }
            }
        }
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
            // Contact saved successfully
            Toast.makeText(this, "Contact saved successfully", Toast.LENGTH_SHORT).show();
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
