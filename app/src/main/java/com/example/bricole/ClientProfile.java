package com.example.bricole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ClientProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Intent intent;
    ClientModel client;

    EditText email, password, name, num, adress;

    Spinner spinnerVille;
    ArrayAdapter<CharSequence> villeAdapter;
    String villeSelected;

    TextWatcher w;

    Button ClUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);

        intent = getIntent();

        client = (new ClientDatabase(this)).getCurrentClient(intent.getStringExtra("email"));
        Log.d("ClientGET", client.getNomComplet());

        email = findViewById(R.id.client_email_update);
        password = findViewById(R.id.client_password_update);
        name = findViewById(R.id.client_name_update);
        num = findViewById(R.id.client_num_update);
        adress = findViewById(R.id.client_adress_update);

        email.setText(client.getEmail());
        password.setText(client.getPassword());
        name.setText(client.getNomComplet());
        num.setText(client.getNumTel());
        adress.setText(client.getAdresse());


        spinnerVille = findViewById(R.id.client_ville_update);
        villeAdapter = ArrayAdapter.createFromResource(this,
                R.array.cities, android.R.layout.simple_spinner_item);
        villeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVille.setAdapter(villeAdapter);
        spinnerVille.setSelection(villeAdapter.getPosition(client.getVille()));
        spinnerVille.setOnItemSelectedListener(this);

        ClUpdate = findViewById(R.id.update_btn);

        ClUpdate.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                if((new ClientDatabase(getApplicationContext())).update(new ClientModel(client.getId() ,name.getText().toString(), num.getText().toString(), adress.getText().toString(), villeSelected, email.getText().toString(), password.getText().toString())))
                {
                    Intent postUpdateIntent = new Intent(getApplicationContext(), ClientHome.class);
                    startActivity(postUpdateIntent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Une erreur est survenue", Toast.LENGTH_LONG);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()){

            case R.id.client_ville_update:
                villeSelected = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), villeSelected, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}