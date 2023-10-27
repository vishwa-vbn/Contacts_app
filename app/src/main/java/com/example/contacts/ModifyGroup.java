//package com.example.contacts;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageButton;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ModifyGroup extends AppCompatActivity {
//    private RecyclerView recyclerView;
////    private MyAdapter3 adapter;
//    private List<String> contactList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_modify_group);
//
//        recyclerView = findViewById(R.id.modifyGroupRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        // Create a list of contact names
//        contactList = new ArrayList<>();
//        contactList.add("Contact 1");
//        contactList.add("Contact 2");
//        contactList.add("Contact 3");
//        contactList.add("Contact 4");
//        contactList.add("Contact 5");
//        contactList.add("Contact 6");
//        contactList.add("Contact 7");
//        contactList.add("Contact 8");
//        contactList.add("Contact 9");
//        contactList.add("Contact 10");
//
//        // Initialize the adapter and set it to the RecyclerView
//        adapter = new MyAdapter3(this, contactList);
//        recyclerView.setAdapter(adapter);
//
//        ImageButton backbtn = findViewById(R.id.backButton);
//        backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//
//            }
//        });
//
//        Button cancelBtn = findViewById(R.id.cancelButton);
//        cancelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//
//            }
//        });
//    }
//}