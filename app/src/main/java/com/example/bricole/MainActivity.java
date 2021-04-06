package com.example.bricole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminAdapter adminAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= (RecyclerView) findViewById(R.id.recycler_dashboard);
        setRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int idItem = item.getItemId();

        switch (idItem) {
            case R.id.ajouter_admin:
                creerAdmin();
                return true;
           /*
            case R.id.ajouter_admin:
                //showHelp();
                return true; */
            default:
                // the action is not handle
                return super.onOptionsItemSelected(item);
        }

    }

    public void creerAdmin()
    {
        // on redirige vers la page signup de création d'admin
        Intent intent = new Intent(MainActivity.this, SignUp.class);
        startActivity(intent);
    }
    public void setRecyclerView()
    {
        // true, if the adapter changes can't affect the recycler view size
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adminAdapter= new AdminAdapter(this,getList());
        recyclerView.setAdapter(adminAdapter);
    }

    private List<AdminModel> getList()
    {
        List<AdminModel> maliste= new ArrayList<AdminModel>();
        // filling data into the tables
        maliste.add(new AdminModel(1,"Dabanguibe","Soumaila","1234retzz","dab@gmail.com"));
        maliste.add(new AdminModel(2,"Toto","Tatio","1234","toto@gmail.com"));
        maliste.add(new AdminModel(3,"Madamee","Bovary","Bovaryroad","bovaryR@gmail.com"));
        maliste.add(new AdminModel(4,"Fatou","Fatima","zara","zara@gmail.com"));
        maliste.add(new AdminModel(5,"PotoTz","Patrick","tr567","patrick@gmail.com"));
        maliste.add(new AdminModel(6,"Hope","Health","wealth","help@gmail.com"));
        maliste.add(new AdminModel(7,"Yassine","Ptro","tzoi43537","yas@gmail.com"));
        maliste.add(new AdminModel(8,"Bouhandira","Hafsa","hafsa453738bou","hafsab@gmail.com"));
        maliste.add(new AdminModel(9,"Boussouab","hiba","hibatoppoz","bhiba@gmail.com"));
        maliste.add(new AdminModel(10,"Amrani","Yassine","amraner36390ysa08u","aYassine789@gmail.com"));



        return  maliste;
    }
}