package com.example.contacts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Random;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteContactViewHolder> {
    private List<Contact> favoriteContacts;
    private Context context;
    private ContactsDatabaseManager databaseManager;

    public FavoritesAdapter(Context context, List<Contact> favoriteContacts, ContactsDatabaseManager databaseManager) {
        this.context = context;
        this.favoriteContacts = favoriteContacts;
        this.databaseManager = databaseManager;
    }

    @NonNull
    @Override
    public FavoriteContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite_list, parent, false);
        return new FavoriteContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteContactViewHolder holder, int position) {
        Contact contact = favoriteContacts.get(position);

        holder.contactNameTextView.setText(contact.getName());
        holder.favoriteCheckBox.setOnCheckedChangeListener(null);
        holder.favoriteCheckBox.setChecked(contact.isFavorite());

        // Set profile image or initial letter with circular background
        if (contact.getImage() != null) {
            holder.profileImage.setImageBitmap(contact.getImage());
        } else {
            Drawable circularDrawable = getCircularTextDrawable(contact.getName());
            holder.profileImage.setImageDrawable(circularDrawable);
        }

        holder.favoriteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Update the 'isFavorite' field of the contact in memory
                contact.setFavorite(isChecked);

                // Update the database asynchronously
                updateContactFavoriteAsync(contact, isChecked);
            }
        });
    }




    private void updateContactFavoriteAsync(Contact contact, boolean isFavorite) {
        // Perform the database operation in a background thread or AsyncTask
        // You can use a separate thread or an AsyncTask to update the database
        // Make sure to update the UI on the main thread after the operation is complete.
        // Example:
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                // Update the database
                databaseManager.setContactFavorite(contact.getId(), isFavorite);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                // Update the UI on the main thread if needed
            }
        }.execute();
    }

    @Override
    public int getItemCount() {
        return favoriteContacts.size();
    }

    public class FavoriteContactViewHolder extends RecyclerView.ViewHolder {
        private TextView contactNameTextView;
        private CheckBox favoriteCheckBox;
        private ImageView profileImage;

        public FavoriteContactViewHolder(@NonNull View itemView) {
            super(itemView);
            contactNameTextView = itemView.findViewById(R.id.contact_name);
            favoriteCheckBox = itemView.findViewById(R.id.checkbox);
            profileImage = itemView.findViewById(R.id.profile_image);
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
