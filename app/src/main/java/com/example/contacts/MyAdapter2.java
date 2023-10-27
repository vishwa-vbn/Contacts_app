//package com.example.contacts;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.GroupViewHolder> {
//
//    private Context context;
//    private List<Group> groups;
//    private OnGroupClickListener clickListener;
//    private OnEditIconClickListener editIconClickListener;
//
//    public MyAdapter2(Context context, List<Group> groups, OnGroupClickListener clickListener, OnEditIconClickListener editIconClickListener) {
//        this.context = context;
//        this.groups = groups;
//        this.clickListener = clickListener;
//        this.editIconClickListener = editIconClickListener;
//    }
//
//    public interface OnEditIconClickListener {
//        void onEditIconClick(Group group);
//    }
//
//
//    public interface OnDeleteIconClickListener {
//        void onDeleteIconClick(Group group);
//    }
//
//    @NonNull
//    @Override
//    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.group_items, parent, false);
//        return new GroupViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyAdapter2.GroupViewHolder holder, int position) {
//        Group group = groups.get(position);
//        String groupName = group.getGroupName();
//
//        holder.groupName.setText(groupName);
//
//        holder.itemView.setOnClickListener(view -> {
//            // Handle group item click here
//            clickListener.onGroupClick(groupName);
//        });
//
//        holder.editIcon.setOnClickListener(view -> {
//            editIconClickListener.onEditIconClick(group);
//        });
//
//        holder.deleteIcon.setOnClickListener(view -> {
//            showDeleteConfirmationDialog(group);
//        });
//
//
//    }
//
//
//
//
//    @Override
//    public int getItemCount() {
//        return groups != null ? groups.size() : 0;
//    }
//
//    public class GroupViewHolder extends RecyclerView.ViewHolder {
//        private TextView groupName;
//        private ImageView editIcon;
//
//        public GroupViewHolder(@NonNull View itemView) {
//            super(itemView);
//            groupName = itemView.findViewById(R.id.group_name);
//            editIcon = itemView.findViewById(R.id.editIcon);
//        }
//    }
//
//    public interface OnGroupClickListener {
//        void onGroupClick(String groupName);
//    }
//}



package com.example.contacts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.GroupViewHolder> {

    private Context context;
    private List<Group> groups;
    private OnGroupClickListener clickListener;
    private OnEditIconClickListener editIconClickListener;
    private OnDeleteIconClickListener deleteIconClickListener;

    public MyAdapter2(Context context, List<Group> groups, OnGroupClickListener clickListener, OnEditIconClickListener editIconClickListener, OnDeleteIconClickListener deleteIconClickListener) {
        this.context = context;
        this.groups = groups;
        this.clickListener = clickListener;
        this.editIconClickListener = editIconClickListener;
        this.deleteIconClickListener = deleteIconClickListener;
    }

    public interface OnEditIconClickListener {
        void onEditIconClick(Group group);
    }

    public interface OnDeleteIconClickListener {
        void onDeleteIconClick(Group group);
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.group_items, parent, false);
        return new GroupViewHolder(view);
    }

    public void updateData(List<Group> newGroups) {
        groups.clear();
        groups.addAll(newGroups);
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.GroupViewHolder holder, int position) {
        Group group = groups.get(position);
        String groupName = group.getGroupName();

        holder.groupName.setText(groupName);

        holder.itemView.setOnClickListener(view -> {
            // Handle group item click here
            clickListener.onGroupClick(groupName);
        });

        holder.editIcon.setOnClickListener(view -> {
            editIconClickListener.onEditIconClick(group);
        });

        holder.deleteIcon.setOnClickListener(view -> {
            deleteIconClickListener.onDeleteIconClick(group);
        });
    }

    @Override
    public int getItemCount() {
        return groups != null ? groups.size() : 0;
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        private TextView groupName;
        private ImageView editIcon;
        private ImageView deleteIcon;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.group_name);
            editIcon = itemView.findViewById(R.id.editIcon);
            deleteIcon = itemView.findViewById(R.id.deleteicon);
        }
    }

    public interface OnGroupClickListener {
        void onGroupClick(String groupName);
    }
}
