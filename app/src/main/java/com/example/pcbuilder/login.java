package com.example.pcbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity implements View.OnClickListener {
    private EditText username,password;
    private Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameloginID);
        password = findViewById(R.id.userpasswordloginID);
        login = findViewById(R.id.loginbuttonID);
        register = findViewById(R.id.registerloginbuttonID);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.loginbuttonID){

            if(username.getText().length() == 0){
                username.setError("Please Enter UserName");
            }
            else if(password.getText().length() == 0){
                password.setError("Please Enter Password");
            }
            else{
                //check for authantication
            }

        }
        else if (view.getId() == R.id.registerloginbuttonID){
            Intent intent = new Intent(this,Register.class);
            startActivity(intent);

        }
    }
}