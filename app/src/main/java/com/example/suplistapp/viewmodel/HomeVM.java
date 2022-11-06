package com.example.suplistapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import com.example.suplistapp.view.HomeActivity;
import com.example.suplistapp.view.MainActivity;

public class HomeVM {
  Activity activity;

  public HomeVM(Activity activity) {
    this.activity = activity;
  }

  public void ifNotLogged(boolean logged, Context context){
    if(!logged) {
      Intent intent = new Intent(context, MainActivity.class);
      activity.startActivity(intent);
    }
  }

  public void ifLogged(boolean logged, Context context, String name){
    if(logged) {
      Intent intent = new Intent(context, HomeActivity.class);
      context.startActivity(intent);
    }
  }

  public void logOutHandler(boolean logged, SharedPreferences preferences, View view){
    if(logged){
      SharedPreferences.Editor edit = preferences.edit();
      edit.putBoolean("logged", false);
      edit.commit();
      Intent intent = new Intent(view.getContext(), MainActivity.class);
      view.getContext().startActivity(intent);
    }
  }
}
