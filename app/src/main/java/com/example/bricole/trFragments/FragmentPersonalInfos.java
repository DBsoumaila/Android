package com.example.bricole.trFragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bricole.R;
import com.example.bricole.TravailleurDatabase;
import com.example.bricole.TravailleurEditProfil;
import com.example.bricole.TravailleurLogin;
import com.example.bricole.TravailleurProfil;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.net.URISyntaxException;

public class FragmentPersonalInfos extends Fragment {

    //Declaration
    public static TextInputEditText fullName, num, email, password;

    public static TextInputEditText profession, ville;

    Button pUpdate, pToHome;

    TravailleurDatabase travailleurDB;
    Integer id;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.travailleur_personal_infos, container,
                        false);

        //Initialisation
        fullName = rootView.findViewById(R.id.edit_full_name);
        profession = rootView.findViewById(R.id.edit_profession);
        num = rootView.findViewById(R.id.edit_num);
        ville =  rootView.findViewById(R.id.edit_ville);
        email = rootView.findViewById(R.id.edit_email);
        password = rootView.findViewById(R.id.edit_password);
        pUpdate = rootView.findViewById(R.id.p_update);
        pToHome = rootView.findViewById(R.id.p_to_home);

        //Get current user (worker) personal infos from DB
        String currentFullName = TravailleurLogin.currentWorker.getFullName();
        //Profession
        String currentProfession = TravailleurLogin.currentWorker.getProffesion();

        String currentNum = TravailleurLogin.currentWorker.getNum();
        //Ville
        String currentVille = TravailleurLogin.currentWorker.getVille();

        String currentEmail = TravailleurLogin.currentWorker.getSignUpEmail();
        String currentPassword = TravailleurLogin.currentWorker.getSignUpPassword();

        id = TravailleurLogin.currentWorker.getId();

        //travailleurDB = new TravailleurDB(getContext());
        travailleurDB = new TravailleurDatabase(getContext());

        //Set view fields to current worker infos
        fullName.setText(currentFullName);

        profession.setText(currentProfession);
        ville.setText(currentVille);

        num.setText(currentNum);
        email.setText(currentEmail);
        password.setText(currentPassword);

        //In case of change update DB
        pUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update functionality

                //Parts appearing in this fragment
                String currentFullName = fullName.getText().toString();
                String currentProfession = profession.getText().toString();
                String currentNum = num.getText().toString();

                String currentVille = ville.getText().toString();

                String currentEmail = email.getText().toString();
                String currentPassword = password.getText().toString();

                //Parts appearing in activity travailleur edit profil must stay the same in the database
                byte[] currentProfilPic =  imageViewToByte(TravailleurEditProfil.profil_pic);
                byte[] currentCin =  imageViewToByte(TravailleurEditProfil.cin_pic);
                byte[] currentCe =  imageViewToByte(TravailleurEditProfil.ce_pic);
                TravailleurEditProfil.name_text.setText(currentFullName);
                TravailleurEditProfil.profession_text.setText(currentProfession);

                //Parts appearing in additional infos fragment must stay the same in the database
                String currentDescription = FragmentAdditionalInfos.description.getText().toString();
                byte[] currentWorkImg1 =  imageViewToByte(FragmentAdditionalInfos.workImg1);
                byte[] currentWorkImg2 =  imageViewToByte(FragmentAdditionalInfos.workImg2);
                byte[] currentWorkImg3 =  imageViewToByte(FragmentAdditionalInfos.workImg3);
                byte[] currentWorkImg4=  imageViewToByte(FragmentAdditionalInfos.workImg4);


                Boolean result = travailleurDB.update(id, currentProfilPic, currentFullName,
                        currentProfession, currentNum, currentVille, currentEmail, currentPassword, currentCin, currentCe,
                        currentDescription, currentWorkImg1, currentWorkImg2, currentWorkImg3, currentWorkImg4);

                if(result){
                    Toast.makeText(getContext(),
                            "Mise à jour avec succès", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Go back to profile page
        pToHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TravailleurProfil.class));
            }
        });


        return rootView;

    }

    //Convert from image view to byte[]
    private byte[] imageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        Log.i("Converting Bitmap","Success");
        return byteArray;
    }


}
