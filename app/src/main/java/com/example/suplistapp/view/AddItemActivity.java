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
import com.example.suplistapp.utils.Validation;

public class AddItemActivity extends Activity {

    private EditText productName;
    private String    productNameString;
    private EditText  expiration;
    private String    expirationString;
    private EditText  price;
    private String    priceString;
    private EditText  qty;
    private String   qtyString;
    private TextView warningAdd;
    private TextView warningDate;

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
        qty = findViewById(R.id.quantity);
        qtyString = qty.getText().toString();
        warningAdd = findViewById(R.id.warningAdd);
        warningDate = findViewById(R.id.warningDate);

        boolean fieldsEmpty = productNameString.isEmpty() || expirationString.isEmpty() || priceString.isEmpty() || qtyString.isEmpty();
        Validation validation = new Validation();
        boolean dateUnformat = validation.dateValidation(expirationString);

        if (fieldsEmpty || !dateUnformat) {
            if (!dateUnformat) warningDate.setText("Data inválida");
            else warningDate.setText("");
            if (fieldsEmpty) warningAdd.setText("É necessário preencher todos os campos");
            else warningAdd.setText("");

            return;
        }

        Double priceDouble = Double.parseDouble(priceString);
        Integer qtyInt = Integer.parseInt(qtyString);

        repository.postItem(new Item(
            productNameString,
            expirationString,
            priceDouble,
            qtyInt,
            this.getIntent().getStringExtra("listType"))
        );

        switch (this.getIntent().getStringExtra("listType")) {
            case "daily":
                Intent intent = new Intent(view.getContext(), DailyActivity.class);
                startActivity(intent);
                break;
            case "weekly":
                Intent intent2 = new Intent(view.getContext(), WeeklyActivity.class);
                startActivity(intent2);
                break;
            case "monthly":
                Intent intent3 = new Intent(view.getContext(), MonthlyActivity.class);
                startActivity(intent3);
                break;
        }
    }
}