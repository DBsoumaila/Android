package com.example.bricole;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class ClientSignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Declare Variables
    EditText cltName, cltNum, cltAdresse, cltSignUpEmail, cltSignUpPassword, cltConfirmPassword;

    //Ville choices
    Spinner spinnerVille;
    ArrayAdapter<CharSequence> villeAdapter;
    String villeSelected;

    Button btCltSignUp;

    AwesomeValidation awesomeValidation;

    ClientDatabase cltDB;
    ClientModel cltModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_sign_up);

        //Initialize cities Spinner
        spinnerVille = findViewById(R.id.client_ville);
        villeAdapter = ArrayAdapter.createFromResource(this,
                R.array.cities, android.R.layout.simple_spinner_item);
        villeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVille.setAdapter(villeAdapter);
        spinnerVille.setOnItemSelectedListener(this);

        //Initialize other variables
        cltName = findViewById(R.id.client_name);
        cltNum = findViewById(R.id.client_num);
        cltAdresse = findViewById(R.id.client_adress);
        cltSignUpEmail = findViewById(R.id.client_signup_email);
        cltSignUpPassword = findViewById(R.id.client_signup_password);
        cltConfirmPassword = findViewById(R.id.client_confirm_password);

        btCltSignUp = findViewById(R.id.client_signup_button);

        cltDB = new ClientDatabase(this);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        //Add Validation for Name
        awesomeValidation.addValidation(this, R.id.client_name,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);


        //Add Validation for Num
        awesomeValidation.addValidation(this, R.id.client_num,
                ".{10,}", R.string.invalid_number);

        //Validation Ville

        //Add Validation for Sign Up Email
        awesomeValidation.addValidation(this, R.id.client_signup_email,
                Patterns.EMAIL_ADDRESS, R.string.invalid_singup_email);

        //Add Validation for Sign Up Password
        awesomeValidation.addValidation(this, R.id.client_signup_password,
                ".{6,}" , R.string.invalid_singup_password);

        //Add Validation for Confirm Password
        awesomeValidation.addValidation(this, R.id.client_confirm_password,
                R.id.client_signup_password , R.string.invalid_confirm_password);

        //Go Back to Login page


        // Don't Forget CIN and XXX Buttons !!!!!!
        btCltSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check Validation
                if(awesomeValidation.validate()){
                    // On Validation Success
                        /*Toast.makeText(getApplicationContext(),
                                "Form Validate Successfully", Toast.LENGTH_SHORT).show();*/

                    //Add User To Database

                    String fullName = cltName.getText().toString();
                    String num = cltNum.getText().toString();
                    String adresse = cltAdresse.getText().toString();;
                    String ville = villeSelected;
                    //String ville = " ";
                    String signUpEmail = cltSignUpEmail.getText().toString();
                    String signUpPassword = cltSignUpPassword.getText().toString();


                    Boolean emailCheckResult = cltDB.checkClientEmail(signUpEmail);
                    //If this Email doesn't exist then we can sign up using it
                    if(emailCheckResult == false){

                        cltModel = new ClientModel(fullName, num, adresse, ville, signUpEmail, signUpPassword);
                        Boolean signUpResult = cltDB.insertData(cltModel);

                        if(signUpResult == true){

                            Log.i("EMAIL IN DB", cltModel.getEmail());
                            Log.i("PASSWORD IN DB", cltModel.getPassword());

                            Toast.makeText(getApplicationContext(),
                                    "Inscription réussite", Toast.LENGTH_SHORT).show();

                            //Well registered go back to login
                            startActivity(new Intent(getApplicationContext(),ClientLogin.class));


                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "Inscription échouée", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),
                                "Email exist déjà", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),
                            "Something went wrong, check the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        villeSelected = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}