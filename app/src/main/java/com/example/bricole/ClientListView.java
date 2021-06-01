package com.example.bricole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.example.bricole.TravailleurSignUp.travailleurDB;

public class ClientListView extends AppCompatActivity {

    ListView listOfWorkers;
    ArrayList<ClientViewTrInfos> travailleursList;
    List<TravailleurModel> allWorkersList;

    static String wantedCity, wantedJob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list_view);

        listOfWorkers = findViewById(R.id.list_of_workers);

        travailleursList = new ArrayList<>();

        allWorkersList= (new TravailleurDatabase(getApplicationContext())).getTrList();

        ClientTrProfileView.workers = allWorkersList;

        for ( int i = 0; i < allWorkersList.size(); i++){

            if(wantedCity.equals(allWorkersList.get(i).getVille()) & wantedJob.equals(allWorkersList.get(i).getProffesion())){

                    Integer id = allWorkersList.get(i).getId();
                    byte[] profil_pic = allWorkersList.get(i).getProfil_pic();
                    String fullName = allWorkersList.get(i).getFullName();
                    String profession = allWorkersList.get(i).getProffesion();
                    String num = allWorkersList.get(i).getNum();

                    travailleursList.add(new ClientViewTrInfos(id,profil_pic, fullName, profession, num));
            }
        }

        //We make custom adapter
        ClientListAdapter cltListAdapter = new ClientListAdapter(this, R.layout.activity_client_list_view_row, travailleursList);
        listOfWorkers.setAdapter(cltListAdapter);

        if(travailleursList.size() == 0){
             Toast.makeText(getApplicationContext(),
                        "Aucun résultat n'est disponible", Toast.LENGTH_SHORT).show();
        }
    }

    //fonction pour faire un appelle
   /* public void call(String num)
    {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+num));
        startActivity(callIntent);
        Log.i("N° Tel: ",num);
    } */

}