package com.example.bricole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClientHome extends AppCompatActivity {

    Intent intent;

    String email;

    Button yourProfilButton, workerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);
    }

    public void yourProfile(View v)
    {
        Intent i = new Intent(getApplicationContext(), ClientProfile.class);
        i.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(i);
    }

    public void listetravailleurs(View v)
    {

        Intent i = new Intent(getApplicationContext(), ClientSpinnerFilters.class);
        startActivity(i);
    }
}