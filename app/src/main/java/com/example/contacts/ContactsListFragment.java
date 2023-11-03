package com.example.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactsListFragment extends Fragment implements ContactsAdapter.OnContactClickListener  {

    private List<Contact> contacts;
    private ContactsAdapter adapter;
    private ContactsDatabaseManager databaseManager;

    public void updateContactList(List<Contact> updatedContacts) {
        adapter.updateContacts(updatedContacts);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts_list, container, false);

        RecyclerView contactsContainer = view.findViewById(R.id.contacts_recycler_view);
        contactsContainer.setLayoutManager(new LinearLayoutManager(getContext()));




        // Initialize your contact list by querying data from the database
        databaseManager = new ContactsDatabaseManager(getContext()).open();
        contacts = getContactsFromDatabase();

        // Set up contacts adapter
         adapter = new ContactsAdapter(getContext(), contacts,R.layout.fragment_contacts_list,this);

        contactsContainer.setAdapter(adapter);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        // Reload contacts when the fragment is resumed
        contacts = getContactsFromDatabase();
        updateContactList(contacts);
    }
    public void addOrUpdateContact(Contact contact) {
        // Add or update the contact in your database
        // ...

        // Get the updated list of contacts
        List<Contact> updatedContacts = getContactsFromDatabase();

        // Update the dataset in the fragment
        updateContactList(updatedContacts);
    }


    public void searchContacts(String query) {
        // Filter the contacts based on the query
        List<Contact> filteredContacts = filterContacts(query);
        // Update the RecyclerView with the filtered data
        adapter.setContacts(filteredContacts);
        adapter.notifyDataSetChanged();
    }

    private List<Contact> filterContacts(String query) {
        List<Contact> filteredContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredContacts.add(contact);
            }
        }
        return filteredContacts;
    }




    private List<Contact> getContactsFromDatabase() {
        // Use the database manager to fetch contact records
        return databaseManager.getAllContacts();
    }


    public void onContactClick(Contact contact) {
        // Handle the click event for a contact
        // You can define what happens when a contact is clicked here
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // Close the database connection when the fragment is destroyed
        databaseManager.close();
    }
}
