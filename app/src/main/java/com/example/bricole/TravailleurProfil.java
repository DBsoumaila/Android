package com.example.bricole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TravailleurProfil extends AppCompatActivity {

    //Declaration
    ImageView profilPic, editPage, logOut;
    TextView name, profession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travailleur_profil);

        //Initialisation
        profilPic = findViewById(R.id.travailleur_edit_pic);
        name = findViewById(R.id.travailleur_ed_name);
        profession = findViewById(R.id.travailleur_ed_profession);
        editPage =  findViewById(R.id.edit);
        logOut =  findViewById(R.id.logout);

        //Get needed data from database
        byte[] profil = TravailleurLogin.currentWorker.getProfil_pic();
        String currentFullName = TravailleurLogin.currentWorker.getFullName();
        String currentProfession = TravailleurLogin.currentWorker.getProffesion();

        //Set view fields to current worker infos
        profilPic.setImageBitmap(byteArrayToBitmap(profil));
        name.setText(currentFullName);
        profession.setText(currentProfession);

        editPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),TravailleurEditProfil.class));
            }
        });


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),TravailleurLogin.class));
            }
        });
    }

    private Bitmap byteArrayToBitmap(byte[] image){
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        return  bitmap;
    }

}