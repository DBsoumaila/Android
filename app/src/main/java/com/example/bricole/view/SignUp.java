package com.example.bricole.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bricole.R;
import com.example.bricole.dbhelpers.DatabaseHandler;

public class SignUp extends AppCompatActivity {
    EditText nomEdit, prenomEdit, passwordEdit, emailEdit;
    // creation de la base
    DatabaseHandler mydatabase ;
Button enregistrerAdmin, dashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // instance de la DB
        mydatabase = new DatabaseHandler(this);

        nomEdit= (EditText) findViewById(R.id.editNom);
        prenomEdit= (EditText) findViewById(R.id.editPrenom);
        passwordEdit= (EditText) findViewById(R.id.editTextTextPassword);
        emailEdit= (EditText) findViewById(R.id.editemail);


        // Les boutons
        enregistrerAdmin= (Button)findViewById(R.id.enregistrerButton);
        dashboard= (Button)findViewById(R.id.Dashboard_retour);


        crerAd();
        dashboardFonc();
    }

    // fonctions
    public  void crerAd()
    {
        enregistrerAdmin.setOnClickListener(clicPourEnregistrer);
    }

    public  void dashboardFonc()
    {
        dashboard.setOnClickListener(dashClick);
    }
    public  void  initialisateurDeChamps()
    {

        nomEdit.setText("");
        prenomEdit.setText("");
        passwordEdit.setText("");
        emailEdit.setText("");
    }

    // listeners


    View.OnClickListener clicPourEnregistrer= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean isInserted=  mydatabase.insertData(nomEdit.getText().toString(), prenomEdit.getText().toString(), passwordEdit.getText().toString(), emailEdit.getText().toString());
            if(isInserted)
            {
                // si les données sont bien insérées
                showMessage("Création Admin","L'administrateur \n Nom:"+nomEdit.getText().toString()+" Prenom: "+prenomEdit.getText().toString()+"\n A été bien créé");
                Toast.makeText(SignUp.this, "Les données ont été bien inséreées",Toast.LENGTH_LONG).show();
                // reinitialiser les champs
                initialisateurDeChamps();
            }else{
                Toast.makeText(SignUp.this, "Erreur!!! Données NON insérées",Toast.LENGTH_LONG).show();

            }
        }
    };

    View.OnClickListener dashClick= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // on redirige vers la page dashboard de création d'admin
            Intent intent = new Intent(SignUp.this, MainActivity.class);
            startActivity(intent);
        }
    };
// le dialog de messages
    public  void  showMessage( String title, String message)
    {
        AlertDialog.Builder builder=  new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}