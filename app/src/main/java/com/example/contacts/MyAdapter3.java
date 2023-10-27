//package com.example.contacts;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CheckBox;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.ViewHolder> {
//    private final List<Contact> contactList;
//    private List<Boolean> selectedContacts; // Add a list to keep track of selected contacts
//    private Context context;
//
//    public MyAdapter3(Context context, List<Contact> contactList) {
//        this.context = context;
//        this.contactList = contactList;
//
//        // Initialize the list for selected contacts
//        selectedContacts = new ArrayList<>(contactList.size());
//        for (int i = 0; i < contactList.size(); i++) {
//            selectedContacts.add(false);
//        }
//    }
//
//    public List<Integer> getSelectedContactIds() {
//        List<Integer> selectedContactIds = new ArrayList<>();
//        for (int i = 0; i < selectedContacts.size(); i++) {
//            if (selectedContacts.get(i)) {
//                selectedContactIds.add(contactList.get(i).getId());
//            }
//        }
//        return selectedContactIds;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Contact contact = contactList.get(position);
//        boolean isSelected = selectedContacts.get(position); // Get the selected status from the list
//
//        holder.nameTextView.setText(contact.getName());
//        holder.checkbox.setChecked(isSelected); // Set the CheckBox based on the selected status
//
//        holder.checkbox.setOnClickListener(view -> {
//            boolean isChecked = holder.checkbox.isChecked();
//            selectedContacts.set(position, isChecked); // Update the selected status in the list
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return contactList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView nameTextView;
//        public CheckBox checkbox;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            nameTextView = itemView.findViewById(R.id.nameTextView);
//            checkbox = itemView.findViewById(R.id.checkbox);
//        }
//    }
//}
