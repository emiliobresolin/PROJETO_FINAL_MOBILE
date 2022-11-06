package com.example.suplistapp.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.suplistapp.model.User;
import com.example.suplistapp.repository.UserRepository;
import com.example.suplistapp.view.MainActivity;

public class SignUpVM {

  Activity activity;

  public SignUpVM(Activity activity) {
    this.activity = activity;
  }

  public String signUpValidation(String name, String login, String password, String passwordConf, User user){
    if (login.isEmpty() || password.isEmpty() || name.isEmpty() || passwordConf.isEmpty()) {
      return "É necessário preencher todos os campos";
    }

    else if (!password.equals(passwordConf)) {
      return "Senhas não conferem!";
    }

    else if (user!=null){
      return "Usuário já existe!";
    }

    else {
      return "";
    }
  }

  public void handler(View view, String nameString, String loginRegistString, String passwordRegistString, UserRepository userRepository, String result){
    if(result.isEmpty()){
      userRepository.postUser(new User(nameString, loginRegistString, passwordRegistString));
      Intent intent = new Intent(view.getContext(), MainActivity.class);
      activity.startActivity(intent);
    }
  }
}
