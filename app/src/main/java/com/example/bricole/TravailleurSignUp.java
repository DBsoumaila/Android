package com.example.bricole;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TravailleurSignUp extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    //Declare Variables
    EditText trName, trNum, trSignUpEmail, trSignUpPassword, trConfirmPassword;

    //Profession choices
    Spinner spinnerProfession;
    ArrayAdapter<CharSequence> professionAdapter;
    String professionSelected;

    //Ville choices
    Spinner spinnerVille;
    ArrayAdapter<CharSequence> villeAdapter;
    String villeSelected;

    TextView toTrLogin;

    Button btTrSignUp;

    ImageView trCIN, trCE, trPicture;
    static final int PICK_IMAGE = 1;
    Uri imageUri;

    boolean picAdded, cinAdded, ceAdded;

    String activeImage;

    AwesomeValidation awesomeValidation;


    static TravailleurDatabase travailleurDB;

    //Stores the add image icon appearing in additional infos page
    Bitmap addImgIcon;


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travailleur_sign_up);*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travailleur_sign_up);

        //Initialize Spinners
        spinnerProfession = findViewById(R.id.travailleur_profession);
        professionAdapter = ArrayAdapter.createFromResource(this,
                R.array.professions, android.R.layout.simple_spinner_item);
        professionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfession.setAdapter(professionAdapter);
        spinnerProfession.setOnItemSelectedListener(this);

        spinnerVille = findViewById(R.id.travailleur_ville);
        villeAdapter = ArrayAdapter.createFromResource(this,
                R.array.cities, android.R.layout.simple_spinner_item);
        villeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVille.setAdapter(villeAdapter);
        spinnerVille.setOnItemSelectedListener(this);

        addImgIcon = BitmapFactory.decodeResource(getResources(), R.drawable.add_img_icon);

        //Initialize Variables
        trName = findViewById(R.id.travailleur_name);
        trNum = findViewById(R.id.travailleur_num);
        trSignUpEmail = findViewById(R.id.travailleur_signup_email);
        trSignUpPassword = findViewById(R.id.travailleur_signup_password);
        trConfirmPassword = findViewById(R.id.travailleur_confirm_password);


        toTrLogin = findViewById(R.id.to_login_text);

        btTrSignUp = findViewById(R.id.travailleur_signup_button);
        trCIN = findViewById(R.id.travailleur_cin);
        trCE = findViewById(R.id.travailleur_ce);
        trPicture = findViewById(R.id.travailleur_picture);

        //travailleurDB = new TravailleurDB(this);
        travailleurDB = new TravailleurDatabase(this);

        //Initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation for Name
        awesomeValidation.addValidation(this, R.id.travailleur_name,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);


        //Add Validation for Num
        awesomeValidation.addValidation(this, R.id.travailleur_num,
                ".{10,}", R.string.invalid_number);

        //Validation Ville

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


        trCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activeImage = "btTrCE";

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Selectionez votre carte d'entrepreneur"), PICK_IMAGE);
            }
        });

        // Don't Forget CIN and XXX Buttons !!!!!!
        btTrSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check Validation
                if(!picAdded){
                    Toast.makeText(getApplicationContext(),
                            "Ajoutez votre photo de profil", Toast.LENGTH_SHORT).show();
                }

                if(!cinAdded){
                    Toast.makeText(getApplicationContext(),
                            "Ajoutez votre carte d'identité", Toast.LENGTH_SHORT).show();
                }

                if(!ceAdded){
                    Toast.makeText(getApplicationContext(),
                            "Ajoutez votre carte d'entrepreneur", Toast.LENGTH_SHORT).show();
                }

                if(awesomeValidation.validate() & picAdded & cinAdded & ceAdded){
                        // On Validation Success
                        /*Toast.makeText(getApplicationContext(),
                                "Form Validate Successfully", Toast.LENGTH_SHORT).show();*/

                        //Add User To Database
                        byte[] profilPic = imageViewToByte(trPicture);
                        String fullName = trName.getText().toString();
                        String profession = professionSelected;
                        String ville = villeSelected;
                        String num = trNum.getText().toString();
                        String signUpEmail = trSignUpEmail.getText().toString();
                        String signUpPassword = trSignUpPassword.getText().toString();
                        byte[] cin = imageViewToByte(trCIN);
                        byte[] ce = imageViewToByte(trCE);

                        byte[] addImageIcon = bitmapToByte(addImgIcon);

                        Boolean emailCheckResult = travailleurDB.checkTravailleurEmail(signUpEmail);
                        //If this Email doesn't exist then we can sign up using it
                        if(emailCheckResult == false){

                            //Boolean signUpResult = travailleurDB.insertData(profilPic,fullName, proffesion, num, signUpEmail, signUpPassword, cin, ce);
                            Boolean signUpResult = travailleurDB.insertData(profilPic,fullName, profession, num, ville, signUpEmail, signUpPassword, cin, ce,
                                    "  ", addImageIcon, addImageIcon, addImageIcon, addImageIcon);

                            if(signUpResult == true){

                                Toast.makeText(getApplicationContext(),
                                        "Inscription réussite", Toast.LENGTH_SHORT).show();

                                //Well registered go back to login
                               startActivity(new Intent(getApplicationContext(),TravailleurLogin.class));

                               //Fulfill Workers List in client view
                               //ClientListView.travailleursList = getTrList();


                            }else{
                                Toast.makeText(getApplicationContext(),
                                        "Inscription échouée", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "Email exist déjà", Toast.LENGTH_SHORT).show();
                        }

                }else{
                    /*Toast.makeText(getApplicationContext(),
                            "Validation Failed", Toast.LENGTH_SHORT).show();*/
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

    private byte[] bitmapToByte(Bitmap image){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        Log.i("Converting Bitmap","Success");
        return byteArray;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()){

            case R.id.travailleur_profession:
                professionSelected = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), professionSelected, Toast.LENGTH_SHORT).show();
                break;

            case R.id.travailleur_ville:
                villeSelected = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), villeSelected, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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

                    picAdded = true;

                }
                if(activeImage.equals("btTrCIN")){

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    trCIN.setImageBitmap(bitmap);

                    Toast.makeText(getApplicationContext(),
                            "CIN ajoutée avec succés", Toast.LENGTH_SHORT).show();

                    cinAdded = true;

                }
                if(activeImage.equals("btTrCE")){

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    trCE.setImageBitmap(bitmap);

                    Toast.makeText(getApplicationContext(),
                            "Carte d'entrepreneur ajoutée avec succés", Toast.LENGTH_SHORT).show();

                    ceAdded = true;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Get workers info needed in ClientListView
    static ArrayList<ClientViewTrInfos> getTrList()
    {
        Cursor result= travailleurDB.getAllWorkers();
        ArrayList<ClientViewTrInfos> trListe= new ArrayList<ClientViewTrInfos>();

        if(result.getCount()==0)
        {
            Log.i("Erreur ","Impossible de voir les utilisateurs. Aucun user trouvé!");
        }else{

            String fullName, profession;
            byte[] profil_pic;
            while (result.moveToNext())
            {
                profil_pic = result.getBlob(1);
                fullName = result.getString(2);
                profession = result.getString(3);

                // Ajout des valeurs de la ligne dans la liste
                trListe.add(new ClientViewTrInfos(profil_pic, fullName, profession));
            }
        }
        return trListe;
    }
}