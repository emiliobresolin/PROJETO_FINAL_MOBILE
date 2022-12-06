package com.example.suplistapp.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.suplistapp.R;
import com.example.suplistapp.adapter.AdapterItems;
import com.example.suplistapp.model.Item;
import com.example.suplistapp.repository.ItemRepository;

import java.util.List;

public class DailyActivity extends Activity {

    public ItemRepository repository = null;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    AdapterItems adapter;
    List<Item> itemList;
    TextView deleteItemName;

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
        deleteItemName = findViewById(R.id.productDelete);
    }

    public Context getContext() {
        return this;
    }

    public void onClickRefresh(View view){
        try {
            deleteItemName.setText(repository.getItemById(adapter.getIdOnClick()).getProductName());
        }
        catch (Exception e) {
            deleteItemName.setText("Clique em algum item e tente novamente");
            e.getMessage();
        }
    }

    public void onClickDelete(View view) {
        try {
            Item item = repository.getItemById(adapter.getIdOnClick());
            repository.deleteItem(item);
            Intent intent = new Intent(view.getContext(), DailyActivity.class);
            startActivity(intent);
        }
        catch (Exception e) {
            deleteItemName.setText("Selecione e carregue um item antes");
            e.getMessage();
        }
    }

    public void onClickBack(View view){
        Intent intent = new Intent(view.getContext(), HomeActivity.class);
        startActivity(intent);
    }

    public void onCLickAdd(View view){
        Intent intent = new Intent(view.getContext(), AddItemActivity.class);
        intent.putExtra("listType", "daily");
        startActivity(intent);
    }
}