package com.example.bricole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClientLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_login);
    }

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
    }
}