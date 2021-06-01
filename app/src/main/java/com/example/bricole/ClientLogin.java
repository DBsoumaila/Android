package com.example.bricole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class ClientLogin extends AppCompatActivity {

    //Declare Variables
    EditText cltLoginEmail, cltLoginPassword;
    TextView cltCreateAccountTextView;
    Button btCltLogin;

    AwesomeValidation awesomeValidation;

    ClientDatabase cltDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_login);

        //Initialize Variables
        cltLoginEmail = findViewById(R.id.client_login_email);
        cltLoginPassword = findViewById(R.id.client_login_password);
        btCltLogin = findViewById(R.id.client_login_button);
        cltCreateAccountTextView = findViewById(R.id.create_account_text);

        cltDB = new ClientDatabase(this);

        //Initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation for Login Email
        awesomeValidation.addValidation(this, R.id.client_login_email,
                Patterns.EMAIL_ADDRESS, R.string.invalid_login_email);

        //Add Validation for Login Password
        awesomeValidation.addValidation(this, R.id.client_login_password,
                ".{6,}" , R.string.invalid_login_password);


        // Go To Sign Up Page
        cltCreateAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ClientSignUp.class));
            }
        });

        //Login
        btCltLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()){
                    String email =  cltLoginEmail.getText().toString();
                    String password = cltLoginPassword.getText().toString();

                    Boolean result =cltDB.checkClientEmailPassword(email, password);

                    Log.i("EMAIL", email);
                    Log.i("Password", password);

                    if(result == true){

                        Intent successfulLogin = new Intent(getApplicationContext(), ClientHome.class);
                        successfulLogin.putExtra("email", email);
                        startActivity(successfulLogin);
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "Ce compte n'existe pas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    /*
    public void clientLogin(View V)
    {
        EditText emailView = findViewById(R.id.client_login_email);
        EditText passwordView = findViewById(R.id.client_login_password);

        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();
        Boolean isClient = (new ClientDatabase(this)).checkClientEmailPassword(email, password);
        if(isClient)
        {
            Intent intent = new Intent(this, Client_profile.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Ce compte n'existe pas", Toast.LENGTH_LONG);
        }
    }
    public void clientRegister(View view)
    {
        Intent intent = new Intent(this, Client_profile.class);
        startActivity(intent);
    } */
}