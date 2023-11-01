package com.example.contacts;

import android.app.Activity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeContactsActivity extends Activity {

    private RecyclerView mergeContactsRecyclerView;
    ContactsDatabaseManager databaseManager;
    private Button back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_contacts);
        back_btn= findViewById(R.id.back_button);




        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mergeContactsRecyclerView = findViewById(R.id.mergeContactsRecyclerView);

        databaseManager = new ContactsDatabaseManager(this);
        databaseManager.open();

        List<Contact> allContacts = databaseManager.getAllContacts();
        List<Pair<Contact, Contact>> contactPairs = GroupMatchingContacts(allContacts);

        MergeContactsAdapter adapter = new MergeContactsAdapter(this, contactPairs,databaseManager);
        mergeContactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mergeContactsRecyclerView.setAdapter(adapter);

//        databaseManager.close();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseManager != null) {
            databaseManager.close();
        }
    }




    private List<Pair<Contact, Contact>> GroupMatchingContacts(List<Contact> contacts) {
        Map<String, List<Contact>> groupedContacts = new HashMap<>();
        List<Pair<Contact, Contact>> matchingContactPairs = new ArrayList<>();

        // Group contacts by either name or phone number
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact1 = contacts.get(i);

            for (int j = i + 1; j < contacts.size(); j++) {
                Contact contact2 = contacts.get(j);

                // Check if contact1 and contact2 have the same name or phone number or both same first name and phonenomber
                if (contact1.getName().equalsIgnoreCase(contact2.getName()) ||
                        contact1.getPhoneNumber().equals(contact2.getPhoneNumber()) ||
                        (contact1.getFirstName().equalsIgnoreCase(contact2.getFirstName()) &&
                                contact1.getPhoneNumber().equals(contact2.getPhoneNumber()))) {
                    matchingContactPairs.add(new Pair<>(contact1, contact2));
                }
            }
        }
        return matchingContactPairs;
    }
}
