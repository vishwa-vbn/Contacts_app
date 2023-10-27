//package com.example.contacts;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class IndividualGroupActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private MyAdapter2 adapter;
//    private List<IndividualGroupItem> individualGroupItemList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_individual_group);
//
//        // Initialize the RecyclerView
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        // Create a list of IndividualGroupItem objects (sample data)
//        individualGroupItemList = new ArrayList<>();
//        individualGroupItemList.add(new IndividualGroupItem("Name 1"));
//        individualGroupItemList.add(new IndividualGroupItem("Name 2"));
//        individualGroupItemList.add(new IndividualGroupItem("Name 3"));
//        individualGroupItemList.add(new IndividualGroupItem("Name 4"));
//        individualGroupItemList.add(new IndividualGroupItem("Name 5"));
//        individualGroupItemList.add(new IndividualGroupItem("Name 6"));
//        individualGroupItemList.add(new IndividualGroupItem("Name 7"));
//        individualGroupItemList.add(new IndividualGroupItem("Name 8"));
//        individualGroupItemList.add(new IndividualGroupItem("Name 9"));
//        individualGroupItemList.add(new IndividualGroupItem("Name 10"));
//
//        // Initialize the adapter and set it to the RecyclerView
//        adapter = new MyAdapter2(this, individualGroupItemList);
//        recyclerView.setAdapter(adapter);
//    }
//}
