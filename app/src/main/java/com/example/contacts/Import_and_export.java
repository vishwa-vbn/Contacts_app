package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Import_and_export extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_and_export);
        getSupportActionBar().hide();
    }
    public void exportContacts(String filePath, List<Contact> contacts) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            for (Contact contact : contacts) {
                // Format the contact information as CSV and write it to the file
                String csvLine = contact.getName() + "," + contact.getPhoneNumber();
                writer.write(csvLine);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}