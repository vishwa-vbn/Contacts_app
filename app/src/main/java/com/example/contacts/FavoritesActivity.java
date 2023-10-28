package com.example.contacts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    private RecyclerView favoritesRecyclerView;
    private FavoritesAdapter favoritesAdapter;
    private Button back_btn;

    private ContactsDatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        getSupportActionBar().hide();
        back_btn= findViewById(R.id.back_button);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        favoritesRecyclerView = findViewById(R.id.favoritesRecyclerView);
        databaseManager = new ContactsDatabaseManager(this);
        databaseManager.open();

        List<Contact> allContacts = databaseManager.getAllContacts();

        favoritesAdapter = new FavoritesAdapter(this, allContacts, databaseManager);

        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        favoritesRecyclerView.setAdapter(favoritesAdapter);

        Button saveFavoritesButton = findViewById(R.id.saveFavoritesButton);
        saveFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save favorite contacts to the database
                favoritesAdapter.notifyDataSetChanged();
            }
        });
    }
}
