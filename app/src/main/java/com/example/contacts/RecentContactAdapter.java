package com.example.contacts;//package com.example.contacts;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.drawable.Drawable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import com.example.contacts.R;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class RecentContactAdapter extends RecyclerView.Adapter<RecentContactAdapter.ContactViewHolder> {
//    private List<String> recentContactNames;
//    private Context context;
//
//    public RecentContactAdapter(Context context, List<String> recentContactNames) {
//        this.context = context;
//        this.recentContactNames = recentContactNames;
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
//        String contactName = recentContactNames.get(position);
//        holder.contactNameTextView.setText(contactName);
//
//        // Create a circular background with the first character of the contact name
//        Drawable circularDrawable = getCircularTextDrawable(contactName);
//        holder.profileImage.setImageDrawable(circularDrawable);
//    }
//
//    @Override
//    public int getItemCount() {
//        return recentContactNames.size();
//    }
//
//    public void setContacts(List<Contact> contacts) {
//        this.recentContactNames = contacts;
//    }
//    public class ContactViewHolder extends RecyclerView.ViewHolder {
//        private ImageView profileImage;
//        private TextView contactNameTextView;
//
//        public ContactViewHolder(View itemView) {
//            super(itemView);
//            profileImage = itemView.findViewById(R.id.profile_image);
//            contactNameTextView = itemView.findViewById(R.id.contact_name);
//        }
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
//
//    private int getRandomColor() {
//        Random random = new Random();
//        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
//    }
//
//}


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.contacts.R;

import java.util.List;
import java.util.Random;

public class RecentContactAdapter extends RecyclerView.Adapter<RecentContactAdapter.ContactViewHolder> {
    private List<String> recentContactNames;
    private Context context;

    public RecentContactAdapter(Context context, List<String> recentContactNames) {
        this.context = context;
        this.recentContactNames = recentContactNames;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }
    //ok

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        String contactName = recentContactNames.get(position);
        holder.contactNameTextView.setText(contactName);

        // Create a circular background with the first character of the contact name
        Drawable circularDrawable = getCircularTextDrawable(contactName);
        holder.profileImage.setImageDrawable(circularDrawable);
    }

    @Override
    public int getItemCount() {
        return recentContactNames.size();
    }

    public void setContacts(List<String> contacts) {
        this.recentContactNames = contacts;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private ImageView profileImage;
        private TextView contactNameTextView;

        public ContactViewHolder(View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image);
            contactNameTextView = itemView.findViewById(R.id.contact_name);
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
