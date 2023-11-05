package com.example.contacts;

import static android.app.DownloadManager.COLUMN_ID;


import android.content.ContentValues;
import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLException;
import java.util.List;

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

        holder.mergeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Merge button click for contact1 and contact2
                if (name1.equals(name2)){
                    String mergedPhoneNumbers = phoneNumber1 + ", " + phoneNumber2;
                    long contactId = contact1.getId();
                    updateContactPhoneNumber(contactId, mergedPhoneNumbers);
                    long secondContactId = contact2.getId();
                    databaseManager.deleteContact(secondContactId);
                    notifyDataSetChanged();
                    ((MainActivity) context).finish();
                }
                else{
                    showCustomToast("Both contacts are different with same phone numbers");
                }
            }
        });

        holder.dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).finish();
            }
        });
    }

    //new customtoast
    private void showCustomToast(String message) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View customToastView = inflater.inflate(R.layout.custom_toast, null);
        TextView messageTextView = customToastView.findViewById(R.id.messageTextView);
        messageTextView.setText(message);

        Toast customToast = new Toast(context);
        customToast.setView(customToastView);
        customToast.setDuration(Toast.LENGTH_LONG);
        customToast.show();
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
        Button mergeButton;
        Button dismissButton;

        public MergeViewHolder(View itemView) {
            super(itemView);
//            mergeBoxContent = itemView.findViewById(R.id.mergeBoxContent);
            mergeButton = itemView.findViewById(R.id.mergeButton);
            dismissButton = itemView.findViewById(R.id.dismissButton);
            nameTextView1 = itemView.findViewById(R.id.firstName1);
            phoneNumberTextView1 = itemView.findViewById(R.id.phoneNumber1);
            nameTextView2 = itemView.findViewById(R.id.firstName2);
            phoneNumberTextView2 = itemView.findViewById(R.id.phoneNumber2);
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
            showCustomToast("Phone numbers merged successfully");
        } else {
            // Contact with the given ID was not found, show an error message
            Toast.makeText(context, "Contact not found.", Toast.LENGTH_SHORT).show();
        }
    }
}
