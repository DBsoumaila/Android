package com.example.bricole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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

import java.util.ArrayList;
import java.util.List;

public class TravailleurLogin extends AppCompatActivity {

    //Declare Variables
    EditText trLoginEmail, trLoginPassword;
    Button btTrLogin;
    TextView createAccountTextView;

    AwesomeValidation awesomeValidation;

    //TravailleurDB travailleurDB;
    TravailleurDatabase travailleurDB;

    public static TravailleurModel currentWorker;
    List<TravailleurModel> allWorkers;


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


        //travailleurDB = new TravailleurDB(this);
        travailleurDB = new TravailleurDatabase(this);

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

                    //Boolean result = travailleurDB.checkTravailleurEmailPassword(email, password);
                    Boolean result = travailleurDB.checkTravailleurEmailPassword(email, password);

                    if(result == true){

                        startActivity(new Intent(getApplicationContext(),TravailleurProfil.class));

                        /*Intent intent = new Intent(getApplicationContext(), TravailleurEditProfil.class);
                        intent.putExtra("tr_full_name", "HIBA BOUSOUAB");
                        startActivity(intent);*/

                        //Cursor cursor = travailleurDB.getCurrentWorker(email);
                        //currentWorker = getTravailleur(cursor);

                        //Cursor cursor = travailleurDB.getAllWorkers();

                        allWorkers = getTrList();

                        for ( int i = 0; i < allWorkers.size(); i++){

                            Log.i("EMAIL", allWorkers.get(i).getSignUpEmail() +"  "+ i);

                            if((allWorkers.get(i).getSignUpEmail()).equals(email)){

                                Log.i("EMAIL", allWorkers.get(i).getSignUpEmail() +"  "+ i);
                                currentWorker = allWorkers.get(i);
                            }
                        }

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

    //Get all workers
    /*private List<TravailleurModel> getTrList()
    {
        Cursor result= travailleurDB.getAllWorkers();
        List<TravailleurModel> trListe= new ArrayList<TravailleurModel>();

        if(result.getCount()==0)
        {
            Log.i("Erreur ","Impossible de voir les utilisateurs. Aucun user trouvé!");
        }else{
            int idRecu;
            String fullName, profession, num, email, password;
            byte[] profil_pic, cin, xxxx;
            while (result.moveToNext())
            {
                idRecu=Integer.parseInt(result.getString(0));
                profil_pic = result.getBlob(1);
                fullName = result.getString(2);
                profession = result.getString(3);
                num = result.getString(4);
                email = result.getString(5);
                password = result.getString(6);
                cin = result.getBlob(7);
                xxxx = result.getBlob(8);

                // Ajout des valeurs de la ligne dans la liste
                trListe.add(new TravailleurModel(idRecu,profil_pic ,fullName,profession,num,email,password,cin,xxxx));
            }
        }
        return trListe;
    }*/

    //List of all workers infos
    private List<TravailleurModel> getTrList()
    {
        Cursor result= travailleurDB.getAllWorkers();
        List<TravailleurModel> trListe= new ArrayList<TravailleurModel>();

        if(result.getCount()==0)
        {
            Log.i("Erreur ","Impossible de voir les utilisateurs. Aucun user trouvé!");
        }else{
            int idRecu;
            String fullName, num, email, password, description;
            String profession, ville;
            byte[] profil_pic, cin, ce, workImg1, workImg2, workImg3, workImg4;
            while (result.moveToNext())
            {
                idRecu=Integer.parseInt(result.getString(0));
                profil_pic = result.getBlob(1);
                fullName = result.getString(2);
                profession = result.getString(3);
                num = result.getString(4);
                ville = result.getString(5);
                email = result.getString(6);
                password = result.getString(7);
                cin = result.getBlob(8);
                ce = result.getBlob(9);

                description = result.getString(10);
                workImg1 = result.getBlob(11);
                workImg2 = result.getBlob(12);
                workImg3 = result.getBlob(13);
                workImg4 = result.getBlob(14);

                // Ajout des valeurs de la ligne dans la liste
                trListe.add(new TravailleurModel(idRecu,profil_pic ,fullName,profession,num, ville, email,password,cin,ce,
                        description, workImg1, workImg2, workImg3, workImg4));
            }
        }
        return trListe;
    }

    //Get Data From Cursor
    /*
    private TravailleurModel getTravailleur(Cursor cursor){

        int idRecu;
        String fullName, profession, num, email, password, description;
        byte[] profil_pic, cin, xxxx, workImg1, workImg2, workImg3, workImg4;

        idRecu=Integer.parseInt(cursor.getString(0));
        //idRecu = Integer.reverse(cursor.getInt(0));
        profil_pic = cursor.getBlob(1);
        fullName = cursor.getString(2);
        profession = cursor.getString(3);
        num = cursor.getString(4);
        email = cursor.getString(5);
        password = cursor.getString(6);
        cin = cursor.getBlob(7);
        xxxx = cursor.getBlob(8);

        description = cursor.getString(9);
        workImg1 = cursor.getBlob(10);
        workImg2 = cursor.getBlob(11);
        workImg3 = cursor.getBlob(12);
        workImg4 = cursor.getBlob(13);

        TravailleurModel result = new TravailleurModel(idRecu,profil_pic ,fullName,profession,num,email,password,cin,xxxx,
                description, workImg1, workImg2, workImg3, workImg4);

        return result;

    }*/
}