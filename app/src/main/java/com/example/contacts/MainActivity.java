package com.example.contacts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.SearchView;

import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private LinearLayout contactsOption;
    private LinearLayout favoritesOption;
    private LinearLayout recentsOption;
    public DrawerLayout drawerLayout;

    public FloatingActionButton fab_ad;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNavigationViewListener();

        contactsOption = findViewById(R.id.contacts_option);
        favoritesOption = findViewById(R.id.favorites_option);
        recentsOption = findViewById(R.id.recents_option);
        fab_ad= findViewById(R.id.fab_add);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        fab_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(MainActivity.this, newActivity.class);
                startActivity(i);

            }
        });

        // Enable the menu icon in the ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.custom_menu_icon); // Use the custom menu icon drawable
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        contactsOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new ContactsListFragment());
            }
        });

        favoritesOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new FavoritesFragment());
            }
        });

        recentsOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new RecentsFragment());
            }
        });

        loadFragment(new ContactsListFragment());
    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.group) {
            // Open the "CreateGroup" activity here using an Intent
            Intent intent = new Intent(MainActivity.this, Main_Group.class);
            startActivity(intent);
        } else if (id == R.id.nav_restore) {
            Intent intent = new Intent(MainActivity.this, RestorePage.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_merge) {
            // Handle other options if needed

            Intent intent = new Intent(MainActivity.this, MergeContactsActivity.class);
            startActivity(intent);
        }
//        else if (id == R.id.nav_trash) {
//            // Handle other options if needed
//
//            Intent intent = new Intent(MainActivity.this, TrashPage.class);
//            startActivity(intent);
//        }
        else if (id == R.id.nav_export_import) {
            // Handle other options if needed

            Intent intent = new Intent(MainActivity.this, Import_and_export.class);
            startActivity(intent);
        }

        // Close the drawer after handling the click event
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem profileMenuItem = menu.findItem(R.id.profile);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }
        });

        Drawable customProfileIcon = ContextCompat.getDrawable(this, R.drawable.profile);
        if (customProfileIcon != null) {
            customProfileIcon.setBounds(0, 0, 48, 48);
            profileMenuItem.setIcon(customProfileIcon);
        }

        return true;
    }

    private void performSearch(String query) {
        // Find the active fragment
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (currentFragment instanceof ContactsListFragment) {
            ContactsListFragment contactsListFragment = (ContactsListFragment) currentFragment;
            contactsListFragment.searchContacts(query);
        } else if (currentFragment instanceof FavoritesFragment) {
            FavoritesFragment favoritesFragment = (FavoritesFragment) currentFragment;
            favoritesFragment.searchContacts(query);
        } else if (currentFragment instanceof RecentsFragment) {
            RecentsFragment recentsFragment = (RecentsFragment) currentFragment;
            recentsFragment.searchContacts(query);
        }
    }





    // Handle menu item clicks in the ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



