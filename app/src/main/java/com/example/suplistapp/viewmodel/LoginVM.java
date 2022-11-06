package com.example.suplistapp.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.suplistapp.model.User;
import com.example.suplistapp.view.HomeActivity;

public class LoginVM {
  Activity activity;

  public LoginVM(Activity activity) {
    this.activity = activity;
  }

  public String loginValidation(String login, String password, User user){
    if (login.isEmpty() || password.isEmpty()) {
      return "É necessário preencher o login e senha!";
    }

    else if (user == null) {
      return "Usuário não encontrado, tente novamente!";
    }

    else if (user.getPassword().equals(password) && user.getUserLogin().equals(login)){
      return "";
    }

    else {
      return "Login ou senha incorreta!";
    }
  }

  public void handler(View view, String result, User user){
    if(result.isEmpty()){
      Intent intent = new Intent(view.getContext(), HomeActivity.class);
      intent.putExtra("name", user.getName());
      activity.startActivity(intent);
    }
  }
}
