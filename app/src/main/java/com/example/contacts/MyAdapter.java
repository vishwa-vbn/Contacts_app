package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {

    private int itemLayout;
    private List<Contact> contacts;
    private Context context;
    private OnContactClickListener clickListener; // Add the listener
//    private List<Long> selectedContactIds = new ArrayList<>();

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

    public MyAdapter(Context context, List<Contact> contacts, int itemLayout, OnContactClickListener clickListener) {
        this.context = context;
        this.contacts = contacts;
        this.itemLayout = itemLayout;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contacts, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);

        // Set contact name
        holder.contactName.setText(contact.getName());

        // Set profile image or initial letter with circular background
        if (contact.getImage() != null) {
            holder.profileImage.setImageBitmap(contact.getImage());
        } else {
            Drawable circularDrawable = getCircularTextDrawable(contact.getName());
            holder.profileImage.setImageDrawable(circularDrawable);
        }

        // Set the checkbox state based on the isSelected property
        holder.checkbox.setChecked(contact.isSelected());

        // Handle checkbox click event
        holder.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Update the isSelected property of the contact
            contact.setSelected(isChecked);
            //Toast.makeText(context, "done", Toast.LENGTH_SHORT).show();
        });

        // Handle contact item click (if needed)
        holder.itemView.setOnClickListener(view -> {
            // Handle contact item click here
            clickListener.onContactClick(contact);
        });




    }

    @Override
    public int getItemCount() {
        return contacts != null ? contacts.size() : 0;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

      private CheckBox  checkbox;
        private ImageView profileImage;
        private TextView contactName;
        private ImageView favoriteIcon;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profile_image);
            contactName = itemView.findViewById(R.id.contact_name);
            checkbox = itemView.findViewById(R.id.checkbox);
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
