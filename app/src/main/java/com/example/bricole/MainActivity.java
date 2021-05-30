package com.example.bricole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToAdmin(View v)
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void goToTravailleur(View v)
    {
        Intent intent = new Intent(this, TravailleurLogin.class);
        startActivity(intent);
    }

    public void goToClient(View v)
    {
        Intent intent = new Intent(this, ClientLogin.class);
        startActivity(intent);
    }
}