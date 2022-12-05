package com.example.suplistapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.suplistapp.R;
import com.example.recyclerviewativ.adapter.AdapterItems;
import com.example.suplistapp.model.Item;
import com.example.suplistapp.repository.ItemRepository;

import java.util.List;

public class DailyActivity extends Activity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    AdapterItems adapter;
    ItemRepository repository = null;
    List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        recyclerView = findViewById(R.id.recyclerViewDaily);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        repository = new ItemRepository(this);
        itemList = repository.getAllDailyItems();
        adapter = new AdapterItems(itemList);
        recyclerView.setAdapter(adapter);
        Log.d("DailyActivity", "onCreate: "+itemList);
    }

    public void onCLick(View view){
        Intent intent = new Intent(view.getContext(), AddItemActivity.class);
        intent.putExtra("listType", "daily");
        startActivity(intent);
    }
}