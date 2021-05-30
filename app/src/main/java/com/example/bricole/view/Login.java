package com.example.bricole.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bricole.R;
import com.example.bricole.dbhelpers.DatabaseHandler;

public class Login extends AppCompatActivity {
    DatabaseHandler databaseHandler;
    EditText mailEdt, passEdit;
Button loginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        // db
        databaseHandler= new DatabaseHandler(this);

        // editText
        mailEdt= (EditText) findViewById(R.id.mailbox) ;
        passEdit= (EditText) findViewById(R.id.editTextTextPassword);
        // bouton login
        loginbtn= (Button) findViewById(R.id.loginBouton);
        loginFonc();
    }
    public void loginFonc()
    {
        loginbtn.setOnClickListener(loginClick);
    }


    View.OnClickListener loginClick= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           login();
        }
    };
    public void login()
    {
       boolean verif;
        String email, password;
        email= mailEdt.getText().toString();
        password= passEdit.getText().toString();
       verif =databaseHandler.isAuthentificated(email, password);

       // tests avant redirection
        if (verif)
        {
            // on redirige vers la page dashboard de création d'admin
            Intent intent = new Intent(Login.this, MainActivity.class);
            // on peut penser a faire passer des donnes ici, comme il est authentifié ou il a droit tel ou tel dans les activités a venir
            showMessage("Correct", " Bienvenu Admin !!");

            startActivity(intent);
        }else{
            // dire a l'utiisateur qu'il n'est pas Admin et de refaire la saisie
            showMessage("Erreur D' Authentification", " Ce compte n'est pas Admin \n Veillez ressaisir vos identifiants !!");
        }


    }
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