//package com.example.contacts;
//
//import static android.app.DownloadManager.COLUMN_ID;
//
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.util.Pair;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class MergeContactsAdapter extends RecyclerView.Adapter<MergeContactsAdapter.MergeViewHolder> {
//
//    private static final String COLUMN_PHONE_NUMBER = null;
//    private final Context context;
//    private final List<Pair<Contact, Contact>> contactPairs;
//    private final ContactsDatabaseManager databaseManager;
//
//    public MergeContactsAdapter(Context context, List<Pair<Contact, Contact>> contactPairs, ContactsDatabaseManager databaseManager) {
//        this.context = context;
//        this.contactPairs = contactPairs;
//        this.databaseManager = databaseManager;
//    }
//
//    @NonNull
//    @Override
//    public MergeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(context).inflate(R.layout.item_merge_box, parent, false);
//        return new MergeViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MergeViewHolder holder, int position) {
//        Pair<Contact, Contact> contactPair = contactPairs.get(position);
//        Contact contact1 = contactPair.first;
//        Contact contact2 = contactPair.second;
//
//        String name1 = contact1.getName();
//        String phoneNumber1 = contact1.getPhoneNumber();
//        String name2 = contact2.getName();
//        String phoneNumber2 = contact2.getPhoneNumber();
//
//        // Set the contact information in your layout
//        holder.nameTextView1.setText(name1);
//        holder.phoneNumberTextView1.setText(phoneNumber1);
//
//        holder.nameTextView2.setText(name2);
//        holder.phoneNumberTextView2.setText(phoneNumber2);
//
//        holder.mergeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle Merge button click for contact1 and contact2
//                if (name1.equals(name2)){
//                    String mergedPhoneNumbers = phoneNumber1 + ", " + phoneNumber2;
//                    long contactId = contact1.getId();
//                    updateContactPhoneNumber(contactId, mergedPhoneNumbers);
//                    long secondContactId = contact2.getId();
//                    databaseManager.deleteContact(secondContactId);
//                    notifyDataSetChanged();
//                    ((MainActivity) context).finish();
//                }
//                else{
//                    Toast.makeText(context, "Both contacts are different with same phone numbers", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        holder.dismissButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((MainActivity) context).finish();
//            }
//        });
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return contactPairs.size();
//    }
//
//    public static class MergeViewHolder extends RecyclerView.ViewHolder {
//        TextView nameTextView1;
//        TextView phoneNumberTextView1;
//        TextView nameTextView2;
//        TextView phoneNumberTextView2;
//        Button mergeButton;
//        Button dismissButton;
//
//        public MergeViewHolder(View itemView) {
//            super(itemView);
////            mergeBoxContent = itemView.findViewById(R.id.mergeBoxContent);
//            mergeButton = itemView.findViewById(R.id.mergeButton);
//            dismissButton = itemView.findViewById(R.id.dismissButton);
//            nameTextView1 = itemView.findViewById(R.id.firstName1);
//            phoneNumberTextView1 = itemView.findViewById(R.id.phoneNumber1);
//            nameTextView2 = itemView.findViewById(R.id.firstName2);
//            phoneNumberTextView2 = itemView.findViewById(R.id.phoneNumber2);
//        }
//    }
//    public void updateContactPhoneNumber(long contactId, String newPhoneNumber) {
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_PHONE_NUMBER, newPhoneNumber);
//
//        String whereClause = COLUMN_ID + " = ?";
//        String[] whereArgs = {String.valueOf(contactId)};
//
//        int rowsUpdated = databaseManager.updateContactPhoneNumbers(contactId, newPhoneNumber);
//        if (rowsUpdated > 0) {
//            // Update was successful, show a success message
//            Toast.makeText(context, "Phone numbers merged successfully.", Toast.LENGTH_SHORT).show();
//        } else {
//            // Contact with the given ID was not found, show an error message
//            Toast.makeText(context, "Contact not found.", Toast.LENGTH_SHORT).show();
//        }
//    }
//}


package com.example.contacts;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.sql.SQLException;
import java.util.Random;

import static android.app.DownloadManager.COLUMN_ID;

public class MergeContactsAdapter extends RecyclerView.Adapter<MergeContactsAdapter.MergeViewHolder> {
    private static final String COLUMN_PHONE_NUMBER = null;
    private final Context context;
    private final List<Pair<Contact, Contact>> contactPairs;
    private final ContactsDatabaseManager databaseManager;

    public MergeContactsAdapter(Context context, List<Pair<Contact, Contact>> contactPairs, ContactsDatabaseManager databaseManager) {
        this.context = context;
        this.contactPairs = contactPairs;
        this.databaseManager = databaseManager;
    }

    @NonNull
    @Override
    public MergeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_merge_box, parent, false);
        return new MergeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MergeViewHolder holder, int position) {
        Pair<Contact, Contact> contactPair = contactPairs.get(position);
        Contact contact1 = contactPair.first;
        Contact contact2 = contactPair.second;

        String name1 = contact1.getName();
        String phoneNumber1 = contact1.getPhoneNumber();
        String name2 = contact2.getName();
        String phoneNumber2 = contact2.getPhoneNumber();

        // Set the contact information in your layout
        holder.nameTextView1.setText(name1);
        holder.phoneNumberTextView1.setText(phoneNumber1);

        holder.nameTextView2.setText(name2);
        holder.phoneNumberTextView2.setText(phoneNumber2);

        // Set profile images or circular text drawables
        if (contact1.getImage() != null) {
            holder.profileImage1.setImageBitmap(contact1.getImage());
        } else {
            Drawable circularDrawable1 = getCircularTextDrawable(name1);
            holder.profileImage1.setImageDrawable(circularDrawable1);
        }

        if (contact2.getImage() != null) {
            holder.profileImage2.setImageBitmap(contact2.getImage());
        } else {
            Drawable circularDrawable2 = getCircularTextDrawable(name2);
            holder.profileImage2.setImageDrawable(circularDrawable2);
        }

        holder.mergeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                // Get the LayoutInflater from the context
                LayoutInflater inflater = LayoutInflater.from(context);

                // Set the custom layout for the dialog
                View dialogView = inflater.inflate(R.layout.custom_dialog, null);
                builder.setView(dialogView);

                TextView titleTextView = dialogView.findViewById(R.id.dialog_title);
                TextView messageTextView = dialogView.findViewById(R.id.dialog_message);
                Button positiveButton = dialogView.findViewById(R.id.dialog_button_positive);
                Button negativeButton = dialogView.findViewById(R.id.dialog_button_negative);

                titleTextView.setText("Merge Contacts");
                messageTextView.setText("Are you sure you want to merge these contacts?");

                AlertDialog dialog = builder.create();

                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle Merge button click for contact1 and contact2
                        if (name1.equals(name2)) {
                            String mergedPhoneNumbers = phoneNumber1 + ", " + phoneNumber2;
                            long contactId = contact1.getId();
                            updateContactPhoneNumber(contactId, mergedPhoneNumbers);
                            long secondContactId = contact2.getId();
                            databaseManager.deleteContact(secondContactId);
                            notifyDataSetChanged();
                            ((MainActivity) context).finish();
                        } else {
                            Toast.makeText(context, "Both contacts are different with the same phone numbers", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss(); // Close the dialog after merging
                    }
                });

                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss(); // Close the dialog when "Cancel" is clicked
                    }
                });

                dialog.show();
            }
        });

        holder.dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).finish();
            }
        });
    }




    @Override
    public int getItemCount() {
        return contactPairs.size();
    }

    public static class MergeViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView1;
        TextView phoneNumberTextView1;
        TextView nameTextView2;
        TextView phoneNumberTextView2;
        ImageView profileImage1;
        ImageView profileImage2;
        Button mergeButton;
        Button dismissButton;

        public MergeViewHolder(View itemView) {
            super(itemView);
            mergeButton = itemView.findViewById(R.id.mergeButton);
            dismissButton = itemView.findViewById(R.id.dismissButton);
            nameTextView1 = itemView.findViewById(R.id.firstName1);
            phoneNumberTextView1 = itemView.findViewById(R.id.phoneNumber1);
            nameTextView2 = itemView.findViewById(R.id.firstName2);
            phoneNumberTextView2 = itemView.findViewById(R.id.phoneNumber2);
            profileImage1 = itemView.findViewById(R.id.profileImage1); // Add ImageView for profile image
            profileImage2 = itemView.findViewById(R.id.profileImage2); // Add ImageView for profile image
        }
    }

    public void updateContactPhoneNumber(long contactId, String newPhoneNumber) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PHONE_NUMBER, newPhoneNumber);

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(contactId)};

        int rowsUpdated = databaseManager.updateContactPhoneNumbers(contactId, newPhoneNumber);
        if (rowsUpdated > 0) {
            // Update was successful, show a success message
            Toast.makeText(context, "Phone numbers merged successfully.", Toast.LENGTH_SHORT).show();
        } else {
            // Contact with the given ID was not found, show an error message
            Toast.makeText(context, "Contact not found.", Toast.LENGTH_SHORT).show();
        }
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

    private int getRandomColor() {
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
