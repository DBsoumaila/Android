package com.example.bricole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.List;

public class ClientDatabase extends SQLiteOpenHelper
{
    public static final String TABLE_NAME= "client";

    //Columns
    public static final String col_1="IDCl";
    public static final String col_2="ClFullName";
    public static final String col_3="ClMobile";
    public static final String col_4="ClAdress";
    public static final String col_5="ClVille";
    public static final String col_6="ClEmail";
    public static final String col_7="ClPassword";

    public ClientDatabase(Context context) { super(context, "client.db", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create Table client(IDCl INTEGER PRIMARY KEY AUTOINCREMENT, ClFullName Text, " +
                "ClMobile Text, ClAdress text, ClVille Text, ClEmail Text, ClPassword Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop Table if exists client");
    }

    public boolean insertData(ClientModel client)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col_2, client.getNomComplet());
        contentValues.put(col_3, client.getNumTel());
        contentValues.put(col_4, client.getAdresse());
        contentValues.put(col_5, client.getVille());
        contentValues.put(col_6, client.getEmail());
        contentValues.put(col_7, client.getPassword());

        long result = DB.insert("client", null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }

    }

    public List<ClientModel> getAllClients()
    {
        List<ClientModel> clients = null;
        //creation d'une instance de la bd
        SQLiteDatabase DB  = this.getWritableDatabase();
        Cursor cursor= DB.rawQuery(" select * from "+TABLE_NAME, null);
        while(cursor.moveToNext())
        {
            int Clid = Integer.parseInt(cursor.getString(0));
            String ClNomComplet = cursor.getString(1);
            String ClNumTel = cursor.getString(2);
            String ClAdress = cursor.getString(3);
            String ClVille = cursor.getString(4);
            String ClEmail = cursor.getString(5);
            String ClPassword = cursor.getString(6);
            ClientModel result = new ClientModel(Clid, ClNomComplet, ClNumTel, ClAdress, ClVille, ClEmail, ClPassword);
            clients.add(result);
        }

        return clients;
    }

    public ClientModel getCurrentClient(String email)
    {
        ClientModel result = null;
        //creation d'une instance de la bd
        SQLiteDatabase DB  = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from client where ClEmail = ?", new String[]{email});
        while(cursor.moveToNext())
        {
            int Clid = Integer.parseInt(cursor.getString(0));
            String ClNomComplet = cursor.getString(1);
            String ClNumTel = cursor.getString(2);
            String ClAdress = cursor.getString(3);
            String ClVille = cursor.getString(4);
            String ClEmail = cursor.getString(5);
            String ClPassword = cursor.getString(6);
            result = new ClientModel(Clid, ClNomComplet, ClNumTel, ClAdress, ClVille, ClEmail, ClPassword);
            break;
        }
        return result;
    }

    public boolean update(ClientModel client)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col_1, client.getId());
        contentValues.put(col_2, client.getNomComplet());
        contentValues.put(col_3, client.getNumTel());
        contentValues.put(col_4, client.getAdresse());
        contentValues.put(col_5, client.getVille());
        contentValues.put(col_6, client.getEmail());
        contentValues.put(col_7, client.getPassword());

        DB.update(TABLE_NAME,contentValues, "IDCl =?", new String[]{Integer.toString(client.getId())});
        return true;
    }

    public Boolean checkClientEmail(String clEmail){

        SQLiteDatabase clientDB = this.getWritableDatabase();
        Cursor cursor = clientDB.rawQuery("select * from client where ClEmail = ?", new String[]{clEmail});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkClientEmailPassword(String clEmail, String clPassword){

        SQLiteDatabase clientDB = this.getWritableDatabase();
        Cursor cursor = clientDB.rawQuery("select * from client where ClEmail = ? and ClPassword = ?", new String[]{clEmail, clPassword});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}
