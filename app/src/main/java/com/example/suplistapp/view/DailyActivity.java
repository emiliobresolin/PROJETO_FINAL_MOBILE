package com.example.suplistapp.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.suplistapp.R;
import com.example.suplistapp.adapter.AdapterItems;
import com.example.suplistapp.model.Item;
import com.example.suplistapp.repository.ItemRepository;
import com.example.suplistapp.utils.Validation;

import java.util.List;

public class DailyActivity extends Activity {

    public ItemRepository repository = null;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    AdapterItems adapter;
    List<Item> itemList;
    EditText dailyTextDate;
    SharedPreferences preferences;
    String dailyTextDateString;
    Validation validation;
    TextView warningExpiration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        recyclerView = findViewById(R.id.recyclerViewDaily);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        repository = new ItemRepository(this);
        itemList = repository.getAllDailyItems();
        adapter = new AdapterItems(itemList, this);
        recyclerView.setAdapter(adapter);
        preferences = getSharedPreferences("pref", 0);
        dailyTextDateString = preferences.getString("dailyTextDate", "");
        dailyTextDate = findViewById(R.id.DailyTextDate);
        dailyTextDate.setText(dailyTextDateString);
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
        intent.putExtra("listType", "daily");
        startActivity(intent);
    }

    public void onClickSave(View view){
        SharedPreferences.Editor edit = preferences.edit();
        String newValue = dailyTextDate.getText().toString();
        if (!validation.dateValidation(newValue)) {
            warningExpiration.setText("Data inválida");
            return;
        }
        else {
            warningExpiration.setText("");
        }

        edit.putString("dailyTextDate", newValue);
        edit.apply();
    }
}