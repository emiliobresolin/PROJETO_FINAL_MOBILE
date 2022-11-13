package com.example.suplistapp.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.suplistapp.R;
import com.example.suplistapp.model.Item;
import com.example.suplistapp.model.User;
import com.example.suplistapp.repository.ItemRepository;
import com.example.suplistapp.viewmodel.HomeVM;
import com.example.suplistapp.viewmodel.LoginVM;
import com.example.suplistapp.repository.UserRepository;

public class MainActivity extends Activity {

    private final String TAG = "MainActivity";

    private EditText login;
    private String    loginString;
    private EditText  password;
    private String    passwordString;
    private TextView warningLogin;

    SharedPreferences preferences;
    boolean logged;
    int userId;

    LoginVM loginVM = new LoginVM(this);
    HomeVM homeVM = new HomeVM(this);
    private UserRepository userRepository = new UserRepository(this);

    ItemRepository itemRepository = new ItemRepository(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("pref", 0);
        logged = preferences.getBoolean("logged", false);
        userId = preferences.getInt("userId", -1);
        String name = "";
        try {
            name = userRepository.getUserById(userId).getName();
        }
        catch (Exception e) {
            e.getMessage();
        }
        homeVM.ifLogged(logged, this, name);
    }

    public void onClickLogin(View view){

        login = findViewById(R.id.name);
        loginString = login.getText().toString();
        password = findViewById(R.id.password);
        passwordString = password.getText().toString();
        warningLogin = findViewById(R.id.warningLogin);

        User user = userRepository.getUserByUserLogin(loginString);

        String result = loginVM.loginValidation(loginString, passwordString, user);

        if(result.isEmpty()){
            loginVM.handler(view, result, user);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putBoolean("logged", true);
            edit.putInt("userId", user.getId());
            edit.commit();
        }
        else {
            warningLogin.setText(result);
        }
    }

    public void onClickSignUp(View view) {
        Intent intent = new Intent(view.getContext(), SignUpActivity.class);
        startActivity(intent);
    }
}