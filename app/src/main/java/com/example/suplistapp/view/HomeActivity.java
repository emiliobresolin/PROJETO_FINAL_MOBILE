package com.example.suplistapp.view;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.suplistapp.R;
import com.example.suplistapp.repository.UserRepository;
import com.example.suplistapp.viewmodel.HomeVM;

public class HomeActivity extends Activity {

  TextView user;
  String userName;

  SharedPreferences preferences;
  boolean logged;

  HomeVM homeVM = new HomeVM(this);
  private UserRepository userRepository = new UserRepository(this);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    user = findViewById(R.id.user);

    preferences = getSharedPreferences("pref", 0);

    try {
      userName = userRepository.getUserById(preferences.getInt("userId", -1)).getName();
    }
    catch (Exception e) {
      e.getMessage();
    }
    user.setText(userName);

    logged = preferences.getBoolean("logged", false);
    homeVM.ifNotLogged(logged, this);
  }

  public void onClickSignOut(View view){
    homeVM.logOutHandler(logged, preferences, view);
  }
}