package com.example.bricole.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.bricole.R;
import com.example.bricole.dbhelpers.TravailleurDBHelper;


public class TravailleurLogin extends AppCompatActivity {

    //Declare Variables
    private EditText trLoginEmail, trLoginPassword;
    Button btTrLogin;
    TextView createAccountTextView;

    AwesomeValidation awesomeValidation;

    TravailleurDBHelper travailleurDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travailleur_login);

        //Initialize Variables
        trLoginEmail = findViewById(R.id.travailleur_login_email);
        trLoginPassword = findViewById(R.id.travailleur_login_password);
        btTrLogin = findViewById(R.id.taravailleur_login_button);
        createAccountTextView = findViewById(R.id.create_account_text);

        //Initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation for Login Email
        awesomeValidation.addValidation(this, R.id.travailleur_login_email,
                Patterns.EMAIL_ADDRESS, R.string.invalid_login_email);

        //Add Validation for Login Password
        awesomeValidation.addValidation(this, R.id.travailleur_login_password,
                ".{6,}" , R.string.invalid_login_password);


        travailleurDB = new TravailleurDBHelper(this);

        // Go To Sign Up Page
        createAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TravailleurSignUp.class));
            }
        });

        //Login
        btTrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()){
                    String email =  trLoginEmail.getText().toString();
                    String password = trLoginPassword.getText().toString();

                    Boolean result = travailleurDB.checkTravailleurEmailPassword(email, password);
                    if(result == true){
                        //What the worker should visualize
                        Toast.makeText(getApplicationContext(),
                                "Great You're in the right page", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "Invalid Crediantials.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}