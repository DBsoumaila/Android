package com.example.bricole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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

    public Cursor getAllClients()
    {
        //creation d'une instance de la bd
        SQLiteDatabase DB  = this.getWritableDatabase();
        Cursor result= DB.rawQuery(" select * from "+TABLE_NAME, null);
        return result;
    }

    public Cursor getCurrentClient(String email)
    {
        //creation d'une instance de la bd
        SQLiteDatabase DB  = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from worker where ClEmail = ?", new String[]{email});
        return cursor;
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

        SQLiteDatabase workerDB = this.getWritableDatabase();
        Cursor cursor = workerDB.rawQuery("select * from client where ClEmail = ?", new String[]{clEmail});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkClientEmailPassword(String clEmail, String clPassword){

        SQLiteDatabase workerDB = this.getWritableDatabase();
        Cursor cursor = workerDB.rawQuery("select * from worker where ClEmail = ? and ClPassword = ?", new String[]{clEmail, clPassword});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}
