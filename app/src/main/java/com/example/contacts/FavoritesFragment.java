package com.example.contacts;//package com.example.contacts;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class FavoritesFragment extends Fragment  implements ContactsAdapter.OnContactClickListener {
//
//    private List<Contact> favoriteContacts;
//    private List<Contact> frequentContacts; // Add a list for frequent contacts
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
//
//        RecyclerView favoritesContainer = view.findViewById(R.id.favorites_recycler_view);
//
//        // Initialize your favorite contacts list here (use your existing data source)
//        favoriteContacts = getFavoriteContacts(5);
//
//        // Create the ContactsAdapter after initializing the favoriteContacts list
//        ContactsAdapter favoritesAdapter = new ContactsAdapter(getContext(), favoriteContacts, R.layout.item_favorite_contact,this);
//
//        favoritesContainer.setAdapter(favoritesAdapter);
//
//        // Use GridLayoutManager with 3 columns for favorites RecyclerView
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3); // Adjust the number of columns as needed
//        favoritesContainer.setLayoutManager(gridLayoutManager);
//
//        // Initialize your frequent contacts list here (use your existing data source)
//      // For example, 10 frequent contacts
//
//        // Set up frequent contacts adapter and RecyclerView
//        RecyclerView frequentsContainer = view.findViewById(R.id.frequents_recycler_view);
//
//        frequentContacts = getFrequentContacts(10);
//
//        ContactsAdapter frequentsAdapter= new ContactsAdapter(getContext(), frequentContacts, R.layout.tem_frequent_contact,this);
//        frequentsContainer.setAdapter(frequentsAdapter);
//        frequentsContainer.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        return view;
//    }
//
//
//    @Override
//    public void onContactClick(Contact contact) {
//        // Handle the click event for a contact
//        // You can define what happens when a contact is clicked here
//    }
//    private List<Contact> getFavoriteContacts(int count) {
//        // Your existing code for generating favorite contacts
//        // You can modify this as needed
//        List<Contact> contacts = new ArrayList<>();
//        String[] availableNames = {
//                "Alice", "Bob", "Charlie", "David", "Eve",
//                "Frank", "Grace", "Hannah", "Ivy", "Jack",
//                "Kate", "Liam", "Mia", "Noah", "Olivia"
//        };
//
//        Random random = new Random();
//        for (int i = 0; i < count; i++) {
//            int index = random.nextInt(availableNames.length);
//            String name = availableNames[index];
//            String phoneNumber = "123-456-789" + i; // Replace with actual phone numbers
//            String callLogDate = "2023-10-01"; // Replace with actual call log dates
//            boolean isFavorite = random.nextBoolean();
//            String category = "Friends"; // Replace with actual categories
//            String group = "Family"; // Replace with actual groups
//
//            String label;
//        }
//
//        return contacts;
//    }
//
//    private List<Contact> getFrequentContacts(int count) {
//        // Your existing code for generating frequent contacts
//        // You can modify this as needed
//        List<Contact> contacts = new ArrayList<>();
//        String[] availableNames = {
//                "Frequent Contact 1", "Frequent Contact 2", "Frequent Contact 3"
//        };
//
//        Random random = new Random();
//        for (int i = 0; i < count; i++) {
//            int index = random.nextInt(availableNames.length);
//            String name = availableNames[index];
//            String phoneNumber = "987-654-321" + i; // Replace with actual phone numbers
//            String callLogDate = "2023-10-02"; // Replace with actual call log dates
//            boolean isFavorite = true; // Frequent contacts are favorites in this example
//            String category = "Friends"; // Replace with actual categories
//            String group = "Family"; // Replace with actual groups
//
//        }
//
//        return contacts;
//    }
//}













//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import com.example.contacts.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FavoritesFragment extends Fragment implements ContactsAdapter.OnContactClickListener {
//
//    private List<Contact> favoriteContacts;
//    private List<Contact> frequentContacts;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
//
//        RecyclerView favoritesContainer = view.findViewById(R.id.favorites_recycler_view);
//        RecyclerView frequentsContainer = view.findViewById(R.id.frequents_recycler_view);
//
//        // Initialize your favorite contacts list
//        favoriteContacts = getFavoriteContacts();
//        ContactsAdapter favoritesAdapter = new ContactsAdapter(getContext(), favoriteContacts, R.layout.item_favorite_contact, this);
//        favoritesContainer.setAdapter(favoritesAdapter);
//
//        // Use LinearLayoutManager to display favorite contacts vertically
//        favoritesContainer.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        // Initialize your frequent contacts list
//        frequentContacts = getFrequentContacts();
//        ContactsAdapter frequentsAdapter = new ContactsAdapter(getContext(), frequentContacts, R.layout.tem_frequent_contact, this);
//        frequentsContainer.setAdapter(frequentsAdapter);
//        frequentsContainer.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        return view;
//    }
//
//    @Override
//    public void onContactClick(Contact contact) {
//
//    }
//
//
//
//
//    public void searchContacts(String query) {
//        // Filter the contacts based on the query
//        List<Contact> filteredContacts = filterContacts(query);
//        // Update the RecyclerView with the filtered data
//        favoritesAdapter.setContacts(filteredContacts);
//        frequentsAdapter.setContacts(filteredContacts);
//        favoritesAdapter.notifyDataSetChanged();
//        frequentsAdapter.notifyDataSetChanged();
//    }
//
//    private List<Contact> filterContacts(String query) {
//        List<Contact> filteredContacts = new ArrayList<>();
//        for (Contact contact : contacts) {
//            if (contact.getName().toLowerCase().contains(query.toLowerCase())) {
//                filteredContacts.add(contact);
//            }
//        }
//        return filteredContacts;
//    }
//
//
//    private List<Contact> getFavoriteContacts() {
//        // Use your database query logic to retrieve favorite contacts
//        ContactsDatabaseManager dbManager = new ContactsDatabaseManager(getContext());
//        dbManager.open();
//        List<Contact> favoriteContacts = dbManager.getFavoriteContacts();
//        dbManager.close();
//        return favoriteContacts;
//    }
//
//    private List<Contact> getFrequentContacts() {
//        ContactsDatabaseManager dbManager = new ContactsDatabaseManager(getContext());
//        dbManager.open();
//        List<Contact> frequentContacts = dbManager.getFrequentContacts();
//        dbManager.close();
//        return favoriteContacts;
//    }
//}



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.contacts.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment implements ContactsAdapter.OnContactClickListener {

    private List<Contact> favoriteContacts;
    private List<Contact> frequentContacts;
    private ContactsAdapter favoritesAdapter;
    private ContactsAdapter frequentsAdapter;
    private Button add_fav;
    private List<Contact> allContacts; // List to store all contacts

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        RecyclerView favoritesContainer = view.findViewById(R.id.favorites_recycler_view);
        RecyclerView frequentsContainer = view.findViewById(R.id.frequents_recycler_view);
        Button add_fav= view.findViewById(R.id.add_favorite_button);




        add_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FavoritesActivity.class);
                startActivity(intent);

            }
        });

        // Initialize your favorite contacts list
        favoriteContacts = getFavoriteContacts();
        favoritesAdapter = new ContactsAdapter(getContext(), favoriteContacts, R.layout.item_favorite_contact, this);
        favoritesContainer.setAdapter(favoritesAdapter);

        // Use LinearLayoutManager to display favorite contacts vertically
        favoritesContainer.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize your frequent contacts list
        frequentContacts = getFrequentContacts();
        frequentsAdapter = new ContactsAdapter(getContext(), frequentContacts, R.layout.tem_frequent_contact, this);
        frequentsContainer.setAdapter(frequentsAdapter);
        frequentsContainer.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onContactClick(Contact contact) {
        // Handle the click event for a contact
        // You can define what happens when a contact is clicked here
    }





    @Override
    public void onResume() {
        super.onResume();

        // Reload favorite and frequent contacts when the fragment is resumed
        favoriteContacts = getFavoriteContacts();
        frequentContacts = getFrequentContacts();

        // Update the adapters with the refreshed data
        favoritesAdapter.setContacts(favoriteContacts);
        frequentsAdapter.setContacts(frequentContacts);

        // Notify the adapters that the data has changed
        favoritesAdapter.notifyDataSetChanged();
        frequentsAdapter.notifyDataSetChanged();
    }

    public void searchContacts(String query) {
        // Filter the contacts based on the query
        List<Contact> filteredFavorites = filterContacts(favoriteContacts, query);
        List<Contact> filteredFrequents = filterContacts(frequentContacts, query);

        // Update the RecyclerView with the filtered data
        favoritesAdapter.setContacts(filteredFavorites);
        frequentsAdapter.setContacts(filteredFrequents);
        favoritesAdapter.notifyDataSetChanged();
        frequentsAdapter.notifyDataSetChanged();
    }

    private List<Contact> filterContacts(List<Contact> contacts, String query) {
        List<Contact> filteredContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredContacts.add(contact);
            }
        }
        return filteredContacts;
    }

    private List<Contact> getFavoriteContacts() {
        // Use your database query logic to retrieve favorite contacts
        ContactsDatabaseManager dbManager = new ContactsDatabaseManager(getContext());
        dbManager.open();
        List<Contact> favoriteContacts = dbManager.getFavoriteContacts();
        dbManager.close();
        return favoriteContacts;
    }

    private List<Contact> getFrequentContacts() {
        ContactsDatabaseManager dbManager = new ContactsDatabaseManager(getContext());
        dbManager.open();
        List<Contact> frequentContacts = dbManager.getFrequentContacts();
        dbManager.close();
        return frequentContacts;
    }
}
