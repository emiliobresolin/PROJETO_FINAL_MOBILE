package com.example.suplistapp.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.suplistapp.R;
import com.example.suplistapp.adapter.AdapterItems;
import com.example.suplistapp.model.Item;
import com.example.suplistapp.repository.ItemRepository;
import com.example.suplistapp.utils.Validation;

import java.util.List;

public class WeeklyActivity extends Activity {

    public ItemRepository repository = null;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    AdapterItems adapter;
    List<Item> itemList;
    EditText weeklyTextDate;
    SharedPreferences preferences;
    String weeklyTextDateString;
    Validation validation;
    TextView warningExpiration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);

        recyclerView = findViewById(R.id.recyclerViewWeekly);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        repository = new ItemRepository(this);
        itemList = repository.getAllWeeklyItems();
        adapter = new AdapterItems(itemList, this);
        recyclerView.setAdapter(adapter);
        preferences = getSharedPreferences("pref", 0);
        weeklyTextDateString = preferences.getString("weeklyTextDate", "");
        weeklyTextDate = findViewById(R.id.WeeklyTextDate);
        weeklyTextDate.setText(weeklyTextDateString);
        validation = new Validation();
        warningExpiration = findViewById(R.id.warningExpiration);
    }

    public Context getContext() {
        return this;
    }

    public void onClickBack(View view){
        Intent intent = new Intent(view.getContext(), HomeActivity.class);
        startActivity(intent);
    }

    public void onCLickAdd(View view){
        Intent intent = new Intent(view.getContext(), AddItemActivity.class);
        intent.putExtra("listType", "weekly");
        startActivity(intent);
    }

    public void onClickSave(View view){
        SharedPreferences.Editor edit = preferences.edit();
        String newValue = weeklyTextDate.getText().toString();
        if (!validation.dateValidation(newValue)) {
            warningExpiration.setText("Data inválida");
            return;
        }
        else {
            warningExpiration.setText("");
        }
        edit.putString("weeklyTextDate", newValue);
        edit.apply();
    }
}