package com.example.suplistapp.view;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.suplistapp.R;
import com.example.suplistapp.viewmodel.HomeVM;

public class HomeActivity extends Activity {

  TextView user;

  SharedPreferences preferences;
  boolean logged;

  HomeVM homeVM = new HomeVM(this);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    user = findViewById(R.id.user);
    user.setText(getIntent().getStringExtra("name"));
    preferences = getSharedPreferences("pref", 0);

    logged = preferences.getBoolean("logged", false);
    homeVM.ifNotLogged(logged, this);
  }

  public void onClickSignOut(View view){
    homeVM.logOutHandler(logged, preferences, view);
  }
}