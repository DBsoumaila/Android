package com.example.bricole.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.bricole.R;
import com.example.bricole.dbhelpers.TravailleurDBHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TravailleurSignUp extends AppCompatActivity {
    //Declare Variables
    EditText trName, trProffesion, trNum, trSignUpEmail, trSignUpPassword, trConfirmPassword;

    TextView toTrLogin;

    Button btTrSignUp;

    ImageView trCIN, trXXXX, trPicture;
    static final int PICK_IMAGE = 1;
    Uri imageUri;

    String activeImage;

    AwesomeValidation awesomeValidation;

    TravailleurDBHelper travailleurDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travailleur_sign_up);

        //Initialize Variables
        trName = findViewById(R.id.travailleur_name);
        trProffesion = findViewById(R.id.travailleur_profession);
        trNum = findViewById(R.id.travailleur_num);
        trSignUpEmail = findViewById(R.id.travailleur_signup_email);
        trSignUpPassword = findViewById(R.id.travailleur_signup_password);
        trConfirmPassword = findViewById(R.id.travailleur_confirm_password);


        toTrLogin = findViewById(R.id.to_login_text);

        btTrSignUp = findViewById(R.id.travailleur_signup_button);
        trCIN = findViewById(R.id.travailleur_cin);
        trXXXX = findViewById(R.id.travailleur_xxx);

        trPicture = findViewById(R.id.travailleur_picture);

        travailleurDB = new TravailleurDBHelper(this);

        //Initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation for Name
        awesomeValidation.addValidation(this, R.id.travailleur_name,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);

        //Add Validation for Job
        awesomeValidation.addValidation(this, R.id.travailleur_profession,
                RegexTemplate.NOT_EMPTY, R.string.invalid_profession);

        //Add Validation for Num
        awesomeValidation.addValidation(this, R.id.travailleur_num,
                RegexTemplate.NOT_EMPTY, R.string.invalid_num);

        //Add Validation for Sign Up Email
        awesomeValidation.addValidation(this, R.id.travailleur_signup_email,
                Patterns.EMAIL_ADDRESS, R.string.invalid_singup_email);

        //Add Validation for Sign Up Password
        awesomeValidation.addValidation(this, R.id.travailleur_signup_password,
               ".{6,}" , R.string.invalid_singup_password);

        //Add Validation for Confirm Password
        awesomeValidation.addValidation(this, R.id.travailleur_confirm_password,
             R.id.travailleur_signup_password , R.string.invalid_confirm_password);


        //Go Back to Login page
        toTrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TravailleurLogin.class));
            }
        });

        //Image Insertion
        //Profil
        trPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activeImage = "trPicture";

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
            }
        });

        //CIN
        trCIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activeImage = "btTrCIN";

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Your CIN"), PICK_IMAGE);
            }
        });

        //XXXX
        trXXXX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activeImage = "btTrXXXX";

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Your XXXX"), PICK_IMAGE);
            }
        });

        // Don't Forget CIN and XXX Buttons !!!!!!
        btTrSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check Validation
                if(awesomeValidation.validate()){
                        // On Validation Success
                        Toast.makeText(getApplicationContext(),
                                "Form Validate Successfully", Toast.LENGTH_SHORT).show();

                        //Add User To Database
                        byte[] profilPic = imageViewToByte(trPicture);
                        String fullName = trName.getText().toString();
                        String proffesion = trProffesion.getText().toString();
                        String num = trNum.getText().toString();
                        String signUpEmail = trSignUpEmail.getText().toString();
                        String signUpPassword = trSignUpPassword.getText().toString();
                        byte[] cin = imageViewToByte(trCIN);
                        byte[] xxx = imageViewToByte(trXXXX);

                        Boolean emailCheckResult = travailleurDB.checkTravailleurEmail(signUpEmail);
                        //If this Email doesn't exist then we can sign up using it
                        if(emailCheckResult == false){

                            Boolean signUpResult = travailleurDB.insertData(profilPic,fullName, proffesion, num, signUpEmail, signUpPassword, cin, xxx);
                            if(signUpResult == true){
                                Toast.makeText(getApplicationContext(),
                                        "Successful Sign Up", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(),
                                        "Failed Sign Up", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "Email already exists", Toast.LENGTH_SHORT).show();
                        }

                }else{
                    Toast.makeText(getApplicationContext(),
                            "Validation Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private byte[] imageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        Log.i("Converting Bitmap","Success");
        return byteArray;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            try {

                if(activeImage.equals("trPicture")){

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    trPicture.setImageBitmap(bitmap);

                }
                if(activeImage.equals("btTrCIN")){

                    Toast.makeText(getApplicationContext(),
                            "CIN ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();

                }
                if(activeImage.equals("btTrXXXX")){

                    Toast.makeText(getApplicationContext(),
                            "XXXX ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}