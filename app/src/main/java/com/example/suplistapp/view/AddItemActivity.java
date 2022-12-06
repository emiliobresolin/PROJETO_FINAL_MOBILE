package com.example.suplistapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.suplistapp.R;
import com.example.suplistapp.model.Item;
import com.example.suplistapp.repository.ItemRepository;

public class AddItemActivity extends Activity {

    private EditText productName;
    private String    productNameString;
    private EditText  expiration;
    private String    expirationString;
    private EditText  price;
    private String    priceString;
    private EditText  qty;
    private String   qtyString;

    ItemRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        repository = new ItemRepository(this);
    }

    public void onClickAdd(View view) {
        productName = findViewById(R.id.productName);
        productNameString = productName.getText().toString();
        expiration = findViewById(R.id.expirationDate);
        expirationString = expiration.getText().toString();
        price = findViewById(R.id.productPrice);
        priceString = price.getText().toString();
        Double priceDouble = Double.parseDouble(priceString);
        qty = findViewById(R.id.quantity);
        qtyString = qty.getText().toString();
        Integer qtyInt = Integer.parseInt(qtyString);

        repository.postItem(new Item(
            productNameString,
            expirationString,
            priceDouble,
            qtyInt,
            this.getIntent().getStringExtra("listType"))
        );

        Intent intent = new Intent(view.getContext(), DailyActivity.class);
        startActivity(intent);
    }
}