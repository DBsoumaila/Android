package com.example.bricole;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DashBoardAdmin extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminAdapter adminAdapter;
    DatabaseHandler mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // creation de l'instance de la base de données
        mydatabase = new DatabaseHandler(this);


        recyclerView= (RecyclerView) findViewById(R.id.recycler_dashboard);
        setRecyclerView();


        // appel de l'enregistrement s il ya un click
       // addData();
        //viewAllUsers();
       // updateUser();
       // deleteUser();
       // gotoMenu();


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
            case R.id.stat_admin_barchat:
               //allData();
                afficherBarChartAdmin();
                return true;
            case R.id.stat_admin_piechart:
               afficherPieChartAdmin();

                return true;

            case R.id.stat_admin_radarchart:
               afficherRadrChartAdmin();
                return true;

            case R.id.ajouter_travailleur:
                creerTravailleur();
                return true;

            default:
                // the action is not handle
                return super.onOptionsItemSelected(item);
        }

    }

    private void creerTravailleur() {
        startActivity(new Intent(getApplicationContext(),TravailleurLogin.class));
    }


    // les charts
    public  void  afficherBarChartAdmin()
    {
        // il faut envoyer les données admin a afficher ici, venant de la base Admin
        startActivity(new Intent(getApplicationContext(),BarChartActivity.class));

    }
    public  void  afficherPieChartAdmin()
    {
        // il faut envoyer les données admin a afficher ici, venant de la base Admin
        startActivity(new Intent(getApplicationContext(),PieChartActivity.class));

    }
    public  void  afficherRadrChartAdmin()
    {
        // il faut envoyer les données admin a afficher ici, venant de la base Admin
        startActivity(new Intent(getApplicationContext(),RadarChartActivity.class));

    }

    public void creerAdmin()
    {
        // on redirige vers la page signup de création d'admin
        Intent intent = new Intent(DashBoardAdmin.this, SignUp.class);
        startActivity(intent);
    }
    public void setRecyclerView()
    {
        // true, if the adapter changes can't affect the recycler view size
        recyclerView.setHasFixedSize(false);
        recyclerView.setClickable(true);
        recyclerView.setHorizontalScrollBarEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adminAdapter= new AdminAdapter(this,getList());
        recyclerView.setAdapter(adminAdapter);
    }

    private List<AdminModel> getList()
    {
        Cursor result= mydatabase.getAllUsers();
        List<AdminModel> maliste= new ArrayList<AdminModel>();

        if(result.getCount()==0)
        {
            showMessage("Erreur ","Impossible de voir les utilisateurs. Aucun user trouvé!");
        }else{
           int idRecu;
           String nom, prenoom, pass, emailAdr;
            while (result.moveToNext())
            {
                idRecu=Integer.parseInt(result.getString(0));
                nom=result.getString(1);
                prenoom=result.getString(2);
                pass=result.getString(3);
                emailAdr=result.getString(4);
                // Ajout des valeurs de la ligne dans la liste
                maliste.add(new AdminModel(idRecu,nom,prenoom,pass,emailAdr));


            }

        }


        return  maliste;
    }

    public void addData()
    {
        //enregister.setOnClickListener(clicPourEnregistrer);
    }

    public  void   allData()
    {
        Cursor result= mydatabase.getAllUsers();

        if(result.getCount()==0)
        {
            showMessage("Erreur ","Impossible de voir les utilisateurs. Aucun user trouvé!");
        }else{
            StringBuffer buffer= new StringBuffer();
            while (result.moveToNext())
            {
                buffer.append("ID:"+result.getString(0)+"\n");
                buffer.append("Nom:"+result.getString(1)+"\n");
                buffer.append("Prénom:"+result.getString(2)+"\n");
                buffer.append("Password:"+result.getString(3)+"\n");
                buffer.append("Email:"+result.getString(4)+"\n \n");
            }
            showMessage("DATA", buffer.toString());
        }
    }

    public void updateUser()
    {
       // updatebtn.setOnClickListener(updateClick);
    }
    public void deleteUser()
    {
        //deletebtn.setOnClickListener(deleteClick);
    }

    public void gotoMenu()
    {
       // menubtn.setOnClickListener(menuclick);
    }


    // definition des listeners
    View.OnClickListener deleteClick= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener menuclick= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //transition sur le menu principal
            Intent intent = new Intent(DashBoardAdmin.this, DashBoardAdmin.class);
            startActivity(intent);
        }
    };


    View.OnClickListener updateClick= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int deletedRows= mydatabase.delete("1");
            if(deletedRows>0)
            {
                Toast.makeText(DashBoardAdmin.this, "Bien supprimé",Toast.LENGTH_LONG).show();

            }else
            {
                Toast.makeText(DashBoardAdmin.this, "Non supprimé",Toast.LENGTH_LONG).show();

            }
        }
    };


    public  void  showMessage( String title, String message)
    {
        AlertDialog.Builder builder=  new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}