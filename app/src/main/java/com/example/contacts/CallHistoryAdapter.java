//package com.example.contacts;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.List;
//
//public class CallHistoryAdapter extends RecyclerView.Adapter<CallHistoryAdapter.ViewHolder> {
//
//    private List<CallHistoryItem> callHistoryList;
//    private Context context;
//
//    public CallHistoryAdapter(Context context, List<CallHistoryItem> callHistoryList) {
//        this.context = context;
//        this.callHistoryList = callHistoryList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        CallHistoryItem item = callHistoryList.get(position);
//
//        holder.dateTimeTextView.setText(item.getDateTime());
//        holder.phoneNumberTextView.setText(item.getPhoneNumber());
//
//        // Check if it's the 3rd item (position % 3 == 2) and set the text color of dateTimeTextView accordingly
//        if (position % 3 == 2) {
//            holder.dateTimeTextView.setTextColor(Color.RED); // Set red text color
//        } else {
//            holder.dateTimeTextView.setTextColor(Color.BLACK); // Default text color
//        }
//
//        // Set the call icon based on the call type (incoming or outgoing)
//        if (item.isIncomingCall()) {
//            holder.callIconImageView.setImageResource(R.drawable.baseline_arrow_inward_24);
//        } else {
//            holder.callIconImageView.setImageResource(R.drawable.baseline_arrow_outward_24);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return callHistoryList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView dateTimeTextView;
//        public TextView phoneNumberTextView;
//        public ImageView callIconImageView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            dateTimeTextView = itemView.findViewById(R.id.date_timeTextView);
//            phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
//            callIconImageView = itemView.findViewById(R.id.callIconImageView);
//        }
//    }
//}









package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CallHistoryAdapter extends RecyclerView.Adapter<CallHistoryAdapter.ViewHolder> {






    private List<CallHistoryItem> callHistoryList;
    private Context context;


    public void updateCallHistory(List<CallHistoryItem> callLogs) {
        this.callHistoryList = callLogs; // Update the callLogs variable
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    public CallHistoryAdapter(Context context, List<CallHistoryItem> callHistoryList) {
        this.context = context;
        this.callHistoryList = callHistoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CallHistoryItem item = callHistoryList.get(position);

        holder.dateTimeTextView.setText(item.getDateTime());
        holder.phoneNumberTextView.setText(item.getPhoneNumber());
        holder.dateTimeTextView.setTextColor(Color.BLACK); // Default text color
        // Check if it's the 3rd item (position % 3 == 2) and set the text color of dateTimeTextView accordingly
//        if (position % 3 == 2) {
//            holder.dateTimeTextView.setTextColor(Color.RED); // Set red text color
//        } else {
//            holder.dateTimeTextView.setTextColor(Color.BLACK); // Default text color
//        }



        // Handle click events on call history items
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to open the IndividualCallHistory activity
                Intent intent = new Intent(context, IndividualCallHistory.class);

                // Pass any necessary data to the IndividualCallHistory activity
                // For example, you can pass the contact's ID or other relevant information
                // You should modify this part based on how your data is structured and retrieved
                // You can get the contact details from the database here

                // Example: Pass contact ID to IndividualCallHistory
                long contactId = item.getContactId();
                intent.putExtra("contact_id", contactId);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return callHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTimeTextView;
        public TextView phoneNumberTextView;
        public ImageView callIconImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTimeTextView = itemView.findViewById(R.id.date_timeTextView);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
            callIconImageView = itemView.findViewById(R.id.callIconImageView);
        }
    }
}
