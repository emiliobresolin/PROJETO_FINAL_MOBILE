package com.example.suplistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.suplistapp.Data.DBHelper;

public class MainActivity extends AppCompatActivity {

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DBHelper(this);
    }
}