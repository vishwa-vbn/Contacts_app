////package com.example.contacts;
////
////import android.content.Context;
////import android.graphics.Bitmap;
////import android.graphics.Canvas;
////import android.graphics.Color;
////import android.graphics.Paint;
////import android.graphics.drawable.BitmapDrawable;
////import android.graphics.drawable.Drawable;
////import android.text.TextUtils;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.ImageView;
////import android.widget.TextView;
////
////import androidx.annotation.NonNull;
////import androidx.recyclerview.widget.RecyclerView;
////
////import com.example.contacts.R; // Make sure to import your R class.
////
////import java.util.List;
////import java.util.Random;
////
////public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {
////
////    private int itemLayout;
////    private List<Contact> contacts;
////    private Context context;
////
////    public ContactsAdapter(Context context, List<Contact> contacts) {
////        this.context = context;
////        this.contacts = contacts;
////    }
////
////
////    public ContactsAdapter(Context context, List<Contact> contacts, int itemLayout) {
////        this.context = context;
////        this.contacts = contacts;
////        this.itemLayout = itemLayout; // Initialize the itemLayout field
////    }
////    @NonNull
////    @Override
////    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
////        return new ContactViewHolder(view);
////    }
////
////
////
////
////    @Override
////    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
////        Contact contact = contacts.get(position);
////
////        // Set contact name
////        holder.contactName.setText(contact.getName());
////
////        // Set profile image or initial letter with circular background
////        if (contact.getImage() != null) {
////            holder.profileImage.setImageBitmap(contact.getImage());
////        } else {
////            Drawable circularDrawable = getCircularTextDrawable(contact.getName());
////            holder.profileImage.setImageDrawable(circularDrawable);
////        }
////
////        // Check if the contact is a favorite
////        if (contact.isFavorite()) {
////            // Hide the favoriteIcon when in the FavoritesFragment or FrequentContactsFragment
////            if (itemLayout != R.layout.item_favorite_contact && itemLayout != R.layout.tem_frequent_contact && itemLayout != R.layout.item_recent_contacts) {
////                holder.favoriteIcon.setVisibility(View.VISIBLE);
////            } else {
////                holder.favoriteIcon.setVisibility(View.GONE);
////            }
////        } else {
////            // This contact is not a favorite, hide the star icon
////            holder.favoriteIcon.setVisibility(View.GONE);
////        }
////
////
////
////        holder.contactNameTextView.setText(contact.getName());
////        // Set the CheckBox status based on your contact data
////        holder.restoreCheckBox.setChecked(contact.isSelected());
////
////        // Handle CheckBox click events
////        holder.restoreCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
////            // Update the contact's selection status when CheckBox is clicked
////            contact.setSelected(isChecked);
////    }
////    }
////
////
////    @Override
////    public int getItemCount() {
////        return contacts != null ? contacts.size() : 0;
////    }
////
////    public class ContactViewHolder extends RecyclerView.ViewHolder {
////
////        private ImageView profileImage;
////        private TextView contactName;
////        private ImageView favoriteIcon; // ImageView for the star icon
////
////        public ContactViewHolder(@NonNull View itemView) {
////            super(itemView);
////
////            profileImage = itemView.findViewById(R.id.profile_image);
////            contactName = itemView.findViewById(R.id.contact_name);
////            favoriteIcon = itemView.findViewById(R.id.favorite_icon); // Initialize the ImageView for the star icon
////        }
////    }
////
////    private int getRandomColor() {
////        Random random = new Random();
////        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
////    }
////
////    private Drawable getCircularTextDrawable(String text) {
////        // Create a circular background with the first character of the text
////        Bitmap bitmap = Bitmap.createBitmap(48, 48, Bitmap.Config.ARGB_8888);
////        Canvas canvas = new Canvas(bitmap);
////        Paint paint = new Paint();
////        paint.setColor(getRandomColor()); // Background color
////        canvas.drawCircle(24, 24, 24, paint); // Draw a circular background
////
////        paint.setColor(Color.WHITE); // Text color
////        paint.setTextSize(20); // Text size
////        paint.setAntiAlias(true);
////        paint.setTextAlign(Paint.Align.CENTER);
////
////        // Draw the text in the center of the bitmap
////        float x = canvas.getWidth() / 2f;
////        float y = (canvas.getHeight() / 2f) - ((paint.descent() + paint.ascent()) / 2);
////        canvas.drawText(text.substring(0, 1).toUpperCase(), x, y, paint);
////
////        return new BitmapDrawable(context.getResources(), bitmap);
////    }
////}
////
////
////
////
////
////
////
////
//
//
//
//
//
//package com.example.contacts;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.CheckBox; // Import CheckBox
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.contacts.R;
//
//import java.util.List;
//import java.util.Random;
//
//public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {
//
//    private int itemLayout;
//    private List<Contact> contacts;
//    private Context context;
//
//    public interface OnContactClickListener {
//        void onContactClick(Contact contact);
//    }
//
//    public ContactsAdapter(Context context, List<Contact> contacts) {
//        this.context = context;
//        this.contacts = contacts;
//    }
//
//    public ContactsAdapter(Context context, List<Contact> contacts, int itemLayout) {
//        this.context = context;
//        this.contacts = contacts;
//        this.itemLayout = itemLayout;
//    }
//
//    @NonNull
//    @Override
//    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
//        return new ContactViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
//        Contact contact = contacts.get(position);
//
//        // Set contact name
//        holder.contactName.setText(contact.getName());
//
//        // Set profile image or initial letter with circular background
//        if (contact.getImage() != null) {
//            holder.profileImage.setImageBitmap(contact.getImage());
//        } else {
//            Drawable circularDrawable = getCircularTextDrawable(contact.getName());
//            holder.profileImage.setImageDrawable(circularDrawable);
//        }
//
//        // Check if the contact is a favorite
//        if (contact.isFavorite()) {
//            // Hide the favoriteIcon when in the FavoritesFragment or FrequentContactsFragment
//            if (itemLayout != R.layout.item_favorite_contact && itemLayout != R.layout.tem_frequent_contact && itemLayout != R.layout.item_recent_contacts) {
//                holder.favoriteIcon.setVisibility(View.VISIBLE);
//            } else {
//                holder.favoriteIcon.setVisibility(View.GONE);
//            }
//        } else {
//            // This contact is not a favorite, hide the star icon
//            holder.favoriteIcon.setVisibility(View.GONE);
//        }
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return contacts != null ? contacts.size() : 0;
//    }
//
//    public class ContactViewHolder extends RecyclerView.ViewHolder {
//
//        private ImageView profileImage;
//        private TextView contactName;
//        private ImageView favoriteIcon;
//        public ContactViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            profileImage = itemView.findViewById(R.id.profile_image);
//            contactName = itemView.findViewById(R.id.contact_name);
//            favoriteIcon = itemView.findViewById(R.id.favorite_icon);
//        }
//    }
//
//
//    private int getRandomColor() {
//        Random random = new Random();
//        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
//    }
//
//    private Drawable getCircularTextDrawable(String text) {
//        // Create a circular background with the first character of the text
//        Bitmap bitmap = Bitmap.createBitmap(48, 48, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        Paint paint = new Paint();
//        paint.setColor(getRandomColor());
//        canvas.drawCircle(24, 24, 24, paint);
//
//        paint.setColor(Color.WHITE);
//        paint.setTextSize(20);
//        paint.setAntiAlias(true);
//        paint.setTextAlign(Paint.Align.CENTER);
//
//        float x = canvas.getWidth() / 2f;
//        float y = (canvas.getHeight() / 2f) - ((paint.descent() + paint.ascent()) / 2);
//        canvas.drawText(text.substring(0, 1).toUpperCase(), x, y, paint);
//
//        return new BitmapDrawable(context.getResources(), bitmap);
//    }
//}




//
//package com.example.contacts;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.contacts.R;
//
//import java.util.List;
//import java.util.Random;
//
//public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {
//
//    private int itemLayout;
//    private List<Contact> contacts;
//    private Context context;
//    private OnContactClickListener clickListener; // Add the listener
//
//    public interface OnContactClickListener {
//        void onContactClick(Contact contact);
//    }
//
//    public ContactsAdapter(Context context, List<Contact> contacts, int itemLayout, OnContactClickListener clickListener) {
//        this.context = context;
//        this.contacts = contacts;
//        this.itemLayout = itemLayout;
//        this.clickListener = clickListener;
//    }
//
//
//
//    @NonNull
//    @Override
//    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
//        return new ContactViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
//        Contact contact = contacts.get(position);
//
//        // Set contact name
//        holder.contactName.setText(contact.getName());
//
//        // Set profile image or initial letter with circular background
//        if (contact.getImage() != null) {
//            holder.profileImage.setImageBitmap(contact.getImage());
//        } else {
//            Drawable circularDrawable = getCircularTextDrawable(contact.getName());
//            holder.profileImage.setImageDrawable(circularDrawable);
//        }
//
//
//        holder.itemView.setOnClickListener(view -> {
//            long contactId = contacts.get(position).getId(); // Get the contact ID
//            // Pass the contact ID to another activity or fragment
//            clickListener.onContactClick(contacts.get(position));
//        });
//
//
//        // Check if the contact is a favorite
//        if (contact.isFavorite()) {
//            // Hide the favoriteIcon when in the FavoritesFragment or FrequentContactsFragment
//            if (itemLayout != R.layout.item_favorite_contact && itemLayout != R.layout.tem_frequent_contact && itemLayout != R.layout.item_recent_contacts) {
//                holder.favoriteIcon.setVisibility(View.VISIBLE);
//            } else {
//                holder.favoriteIcon.setVisibility(View.GONE);
//            }
//        } else {
//            // This contact is not a favorite, hide the star icon
//            holder.favoriteIcon.setVisibility(View.GONE);
//        }
//
//        // Set the CheckBox status based on your contact data
//
//
//        holder.itemView.setOnClickListener(view -> {
//            // Create an intent to open the IndividualCallHistory activity
//            long contactId = contacts.get(position).getId();
//            Intent intent = new Intent(context, IndividualCallHistory.class);
//
//            // Pass the contact details to the IndividualCallHistory activity
//            intent.putExtra("contact_id", contactId);
//            intent.putExtra("contact_name", contact.getName());
//            intent.putExtra("contact_phone", contact.getPhoneNumber());
//
//            context.startActivity(intent);
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return contacts != null ? contacts.size() : 0;
//    }
//
//    public class ContactViewHolder extends RecyclerView.ViewHolder {
//
//        private ImageView profileImage;
//        private TextView contactName;
//        private ImageView favoriteIcon;
//        public ContactViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            profileImage = itemView.findViewById(R.id.profile_image);
//            contactName = itemView.findViewById(R.id.contact_name);
//            favoriteIcon = itemView.findViewById(R.id.favorite_icon);
//        }
//    }
//
//    private int getRandomColor() {
//        Random random = new Random();
//        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
//    }
//
//    private Drawable getCircularTextDrawable(String text) {
//        // Create a circular background with the first character of the text
//        Bitmap bitmap = Bitmap.createBitmap(48, 48, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        Paint paint = new Paint();
//        paint.setColor(getRandomColor());
//        canvas.drawCircle(24, 24, 24, paint);
//
//        paint.setColor(Color.WHITE);
//        paint.setTextSize(20);
//        paint.setAntiAlias(true);
//        paint.setTextAlign(Paint.Align.CENTER);
//
//        float x = canvas.getWidth() / 2f;
//        float y = (canvas.getHeight() / 2f) - ((paint.descent() + paint.ascent()) / 2);
//        canvas.drawText(text.substring(0, 1).toUpperCase(), x, y, paint);
//
//        return new BitmapDrawable(context.getResources(), bitmap);
//    }
//}










package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.contacts.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    private int itemLayout;
    private List<Contact> contacts;
    private Context context;
    private OnContactClickListener clickListener; // Add the listener
    private boolean displayDeletedContacts;

    public void updateContacts(List<Contact> updatedContacts) {
        this.contacts = updatedContacts;
        notifyDataSetChanged();
    }

    public List<Contact> getSelectedContacts() {
        List<Contact> selectedContacts = new ArrayList<>();

        for (Contact contact : contacts) {
            if (contact.isSelected()) {
                selectedContacts.add(contact);
            }
        }

        return selectedContacts;
    }

    public interface OnContactClickListener {
        void onContactClick(Contact contact);
    }

    public ContactsAdapter(Context context, List<Contact> contacts, int itemLayout, OnContactClickListener clickListener) {
        this.context = context;
        this.contacts = contacts;
        this.itemLayout = itemLayout;
        this.clickListener = clickListener;
    }


    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);



    }


    private void loadProfilePicture(String profilePicturePath, ImageView imageView) {
        if (profilePicturePath != null && !profilePicturePath.isEmpty()) {
            Log.d("ProfileImage", "Profile Picture Path: " + profilePicturePath); // Log the profile picture path
            try {
                File imageFile = new File(profilePicturePath);
                if (imageFile.exists()) {
                    Bitmap profileBitmap = BitmapFactory.decodeFile(profilePicturePath);
                    if (profileBitmap != null) {
                        imageView.setImageBitmap(profileBitmap);
                    }
                } else {
                    Log.e("ProfileImage", "Image file does not exist: " + profilePicturePath);
                }
            } catch (Exception e) {
                Log.e("ProfileImage", "Error loading profile picture: " + e.getMessage());
            }
        } else {
            Log.d("ProfileImage", "profilePicturePath is null or empty");
        }
    }




    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);

        // Set contact name
        holder.contactName.setText(contact.getName());



        if (contact.getImage() != null) {
            String profilePicturePath = contact.getProfilePicturePath();
            Log.d("ProfileImage:path:", profilePicturePath);

            loadProfilePicture(profilePicturePath, holder.profileImage);
        } else {
            Drawable circularDrawable = getCircularTextDrawable(contact.getName());
            holder.profileImage.setImageDrawable(circularDrawable);
        }

        holder.itemView.setOnClickListener(view -> {
            // Handle contact item click
            clickListener.onContactClick(contacts.get(position));

            // Create an intent to open the IndividualCallHistory activity
            long contactId = contacts.get(position).getId();
            Intent intent = new Intent(context, IndividualCallHistory.class);

            // Pass the contact details to the IndividualCallHistory activity
            intent.putExtra("contact_id", contactId);
            intent.putExtra("contact_name", contact.getName());
            intent.putExtra("contact_phone", contact.getPhoneNumber());

            context.startActivity(intent);
        });

        // Check if the contact is a favorite
        if (contact.isFavorite()) {
            // Hide the favoriteIcon when in the FavoritesFragment or FrequentContactsFragment
            if (itemLayout != R.layout.item_favorite_contact && itemLayout != R.layout.tem_frequent_contact && itemLayout != R.layout.item_recent_contacts) {
                holder.favoriteIcon.setVisibility(View.VISIBLE);
            } else {
                holder.favoriteIcon.setVisibility(View.GONE);
            }
        } else {
            // This contact is not a favorite, hide the star icon
            holder.favoriteIcon.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return contacts != null ? contacts.size() : 0;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        private ImageView profileImage;
        private TextView contactName;
        private ImageView favoriteIcon;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profile_image);
            contactName = itemView.findViewById(R.id.contact_name);
            favoriteIcon = itemView.findViewById(R.id.favorite_icon);
        }
    }

    private int getRandomColor() {
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    private Drawable getCircularTextDrawable(String text) {
        // Create a circular background with the first character of the text
        Bitmap bitmap = Bitmap.createBitmap(48, 48, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(getRandomColor());
        canvas.drawCircle(24, 24, 24, paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(20);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);

        float x = canvas.getWidth() / 2f;
        float y = (canvas.getHeight() / 2f) - ((paint.descent() + paint.ascent()) / 2);
        canvas.drawText(text.substring(0, 1).toUpperCase(), x, y, paint);

        return new BitmapDrawable(context.getResources(), bitmap);
    }
}
