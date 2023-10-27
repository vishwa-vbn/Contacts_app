//package com.example.contacts;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageButton;
//
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class GroupActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private MyAdapter adapter;
//    private List<GroupItem> groupItemList;
//    ImageButton backButton;
//
//    Button CreateBtn;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_group);
//
//        CreateBtn = findViewById(R.id.createButton);
//
//         ImageButton  editbtn = findViewById(R.id.editButton);
//        // Initialize the RecyclerView
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        // Create a list of GroupItem objects (sample data)
//        groupItemList = new ArrayList<>();
//        groupItemList.add(new GroupItem("Group 1"));
//        groupItemList.add(new GroupItem("Group 2"));
//        groupItemList.add(new GroupItem("Group 3"));
//        // Add more items as needed
//
//        // Initialize the adapter and set it to the RecyclerView
//        adapter = new MyAdapter(this, groupItemList);
//        recyclerView.setAdapter(adapter);
//
//        backButton = findViewById(R.id.backButton);
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(GroupActivity.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        CreateBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent= new Intent( GroupActivity.this , AddContact.class);
//                startActivity(intent);
//            }
//        });
//    }
//}