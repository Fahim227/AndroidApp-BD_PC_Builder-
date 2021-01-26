package com.example.pcbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText username,email,password,confirmpassword;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.registerusernameID);
        email = findViewById(R.id.registeremailID);
        password = findViewById(R.id.registerpasswordID);
        confirmpassword = findViewById(R.id.registerconfirmpasswordID);
        register = findViewById(R.id.registerbuttonID);

        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.registerbuttonID){
            if(username.getText().length() == 0){
                username.setError("Please Enter UserName");
            }
            else if(email.getText().length() == 0){
                email.setError("Please Enter email");
            }
            else if(password.getText().length() <=7){
                password.setError("Please Enter Atlease 8 character");
            }
            else if(!confirmpassword.getText().equals(password.getText())){
                confirmpassword.setError("Please Enter same Password");
            }
            else{
                // Send Data to Register User .....

            }
        }
    }
}