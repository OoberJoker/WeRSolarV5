package com.example.swara.wersolarv5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ImageView signUpIcon = (ImageView)findViewById(R.id.registrationFormSignUpButtonID);

        signUpIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText firstNameField = (EditText)findViewById(R.id.registrationFormFirstNameFieldID);
                EditText lastNameField = (EditText)findViewById(R.id.registrationFormLastNameFieldID);
                EditText emailField = (EditText)findViewById(R.id.registrationFormEmailFieldID);
                EditText phoneField = (EditText)findViewById(R.id.registrationFormPhoneNumberFieldID);
                EditText passwordField = (EditText)findViewById(R.id.registrationFormPasswordFieldID);
                EditText userNameField = (EditText)findViewById(R.id.registrationFormUserNameFieldID);

                String firstName = firstNameField.getText().toString();
                String lastName = lastNameField.getText().toString();
                String email = emailField.getText().toString();
                String phoneNumber = phoneField.getText().toString();
                String password = passwordField.getText().toString();
                String userName  = userNameField.getText().toString();

                final HashMap<String, String> params = new HashMap<>();
                params.put("firstName", firstName);
                params.put("lastName", lastName);
                params.put("email", email);
                params.put("phoneNumber", phoneNumber);
                params.put("password", password);
                params.put("userName",userName);

                try {
                   // RegistrationActivity registrationActivity = ((RegistrationActivity)getActivity());
                    // registrationActivity.getApplicationContext();
                    new PostAsync(StaticDataMembers.serverInsertDataUrl, params,RegistrationActivity.this).execute();
                }
                catch (Exception e){
                    e.printStackTrace();
                }































               /* Intent intent = new Intent(RegistrationActivity.this, InformationFormActivity.class);
                startActivity(intent);*/
            }

        });
    }

}
