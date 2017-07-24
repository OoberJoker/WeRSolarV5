package com.example.swara.wersolarv5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView loginInButton = (ImageView)findViewById(R.id.loginFormLoginButtonFieldID);
        loginInButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText userNameFormField = (EditText) findViewById(R.id.loginFormUserNameFieldID);
                EditText passwordFormField = (EditText) findViewById(R.id.loginFormPasswordFieldID);
                final String email = userNameFormField.getText().toString();
                final String password = passwordFormField.getText().toString();
                final HashMap<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                try {
                    Log.i("email check: ", email);
                    Log.i("pass check: ", password);
                    new PostAsync(StaticDataMembers.serverAuthDataUrl, params, LoginActivity.this).execute();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });












        




        ImageView signUpIcon = (ImageView)findViewById(R.id.loginFormSignUpButtonFieldID);
        signUpIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }

        });






    }



}
