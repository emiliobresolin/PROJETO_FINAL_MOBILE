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

public class MonthlyActivity extends Activity {

    public ItemRepository repository = null;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    AdapterItems adapter;
    List<Item> itemList;
    EditText monthlyTextDate;
    SharedPreferences preferences;
    String monthlyTextDateString;
    Validation validation;
    TextView warningExpiration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly);

        recyclerView = findViewById(R.id.recyclerViewMonthly);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        repository = new ItemRepository(this);
        itemList = repository.getAllMonthlyItems();
        adapter = new AdapterItems(itemList, this);
        recyclerView.setAdapter(adapter);
        preferences = getSharedPreferences("pref", 0);
        monthlyTextDateString = preferences.getString("monthlyTextDate", "");
        monthlyTextDate = findViewById(R.id.MonthlyTextDate);
        monthlyTextDate.setText(monthlyTextDateString);
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
        intent.putExtra("listType", "monthly");
        startActivity(intent);
    }

    public void onClickSave(View view){
        SharedPreferences.Editor edit = preferences.edit();
        String newValue = monthlyTextDate.getText().toString();
        if (!validation.dateValidation(newValue)) {
            warningExpiration.setText("Data inválida");
            return;
        }
        else {
            warningExpiration.setText("");
        }

        edit.putString("monthlyTextDate", newValue);
        edit.apply();
    }
}