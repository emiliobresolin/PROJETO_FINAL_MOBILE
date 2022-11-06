package com.example.suplistapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.suplistapp.R;
import com.example.suplistapp.model.User;
import com.example.suplistapp.viewmodel.SignUpVM;
import com.example.suplistapp.repository.UserRepository;

public class SignUpActivity extends Activity {

  private final String TAG = "SignOnActivity";

  private EditText name;
  private String nameString;
  private EditText loginRegist;
  private String loginRegistString;
  private EditText passwordRegist;
  private String passwordRegistString;
  private EditText passwordConf;
  private String passwordConfString;
  private TextView warning;

  private UserRepository userRepository = new UserRepository(this);
  SignUpVM signUpVM = new SignUpVM(this);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);
  }

  public void onClickSignUp(View view){
    name = findViewById(R.id.name);
    nameString = name.getText().toString();
    loginRegist = findViewById(R.id.loginRegist);
    loginRegistString = loginRegist.getText().toString();
    passwordRegist = findViewById(R.id.passwordRegist);
    passwordRegistString = passwordRegist.getText().toString();
    passwordConf = findViewById(R.id.passwordConf);
    passwordConfString = passwordConf.getText().toString();
    warning = findViewById(R.id.warning);

    User user = userRepository.getUserByUserLogin(loginRegistString);

    String result = signUpVM.signUpValidation(nameString, loginRegistString, passwordRegistString, passwordConfString, user);

    if(result.isEmpty()){
      signUpVM.handler(view, nameString, loginRegistString, passwordRegistString, userRepository, result);
    }
    else {
      warning.setText(result);
    }
  }
}