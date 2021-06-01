package com.example.bricole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ClientSpinnerFilters extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Profession filtrage
    Spinner spinnerProfession;
    ArrayAdapter<CharSequence> professionAdapter1;

    //Ville filtrage
    Spinner spinnerVille;
    ArrayAdapter<CharSequence> villeAdapter1;


    Button recherche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_spinner_filters);

        recherche = findViewById(R.id.recherche);

        spinnerProfession = findViewById(R.id.filter_profession);
        professionAdapter1 = ArrayAdapter.createFromResource(this,
                R.array.professions, android.R.layout.simple_spinner_item);
        professionAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfession.setAdapter(professionAdapter1);
        spinnerProfession.setOnItemSelectedListener(this);


        spinnerVille = findViewById(R.id.filter_ville);
        villeAdapter1 = ArrayAdapter.createFromResource(this,
                R.array.cities, android.R.layout.simple_spinner_item);
        villeAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVille.setAdapter(villeAdapter1);
        spinnerVille.setOnItemSelectedListener(this);

        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ClientListView.class));
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()){

            case R.id.filter_profession:
                ClientListView.wantedJob = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), ClientListView.wantedJob, Toast.LENGTH_SHORT).show();
                break;

            case R.id.filter_ville:
                ClientListView.wantedCity = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), ClientListView.wantedCity, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}