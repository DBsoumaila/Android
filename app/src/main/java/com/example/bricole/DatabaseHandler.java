package com.example.bricole;
//importations pour la base de données

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends  SQLiteOpenHelper {
    //Déclarations
    // requests
    public    Cursor  curseur;
    // DATABASE
    public static final String DATABASE_NAME= "Bricole.db";
    // TABLE
    public static final String TABLE_NAME= "Admin";
    // COLUMNS
    public static final String col_1="ID";
    public static final String col_2= "Nom";
    public static final String col_3= "Prenom";
    public static final String col_4= "Password";
    public static final String col_5= "Email";
    // REQUETES
    public static final String TABLE_USER_CREATION=" create TABLE User (ID INTEGER PRIMARY KEY AUTOINCREMENT, Nom TEXT, Prenom TEXT, Password TEXT, Email TEXT)";



    // Constructeur
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
         }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
               " create TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, Nom TEXT, Prenom TEXT, Password TEXT, Email TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public  boolean insertData(String nom, String prenom, String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeursDeContenu= new ContentValues();
        //remplissage de valeurs
        valeursDeContenu.put(col_2,nom);
        valeursDeContenu.put(col_3,prenom);
        valeursDeContenu.put(col_4,password);
        valeursDeContenu.put(col_5,email);

        long result= db.insert(TABLE_NAME , null, valeursDeContenu);

        if(result==-1)
        {
            return false;
        }else
        {
            return true;
        }
    }

    public Cursor  getAllUsers()
    {
        //creation d'une instance de la bd
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result= db.rawQuery(" select * from "+TABLE_NAME, null);
        return result;
    }

    public boolean update( String id, String nom, String prenom, String password, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeursDeContenu= new ContentValues();
        //remplissage de nouvelles valeurs
        valeursDeContenu.put(col_1,id);
        valeursDeContenu.put(col_2,nom);
        valeursDeContenu.put(col_3,prenom);
        valeursDeContenu.put(col_4,password);
        valeursDeContenu.put(col_5,email);
        //mise à jour
        db.update(TABLE_NAME,valeursDeContenu, "ID =?", new String[]{id});
        return true;
    }

    public  int delete(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // return number of rows deleted
        return db.delete(TABLE_NAME,"ID= ?",new String[] {id});

    }

    public boolean isAuthentificated(String emailAverifier, String passwordAverifier)
    {
        String email, password;
        email="";
        password="" ;
        SQLiteDatabase db = this.getWritableDatabase();
         curseur= db.rawQuery("select * from "+TABLE_NAME+" where Password= ? and Email= ? ",new String[]{passwordAverifier,emailAverifier});
       // remplissage avec les données
        if (curseur != null && curseur.moveToFirst()){
            do {
                email= curseur.getString(4);
                password= curseur.getString(3);


            } while (curseur.moveToNext());
        }


       // vérification
        if(emailAverifier.equals(email) && passwordAverifier.equals(password))
        {
            return  true;
        }else
        {
            return false;
        }
    }
}
