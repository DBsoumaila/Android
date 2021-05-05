package com.example.bricole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class TravailleurDBHelper extends SQLiteOpenHelper {

    //Boolean result = false;

    public TravailleurDBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase travailleurDB) {
        travailleurDB.execSQL("create Table ouvriers(IDTr INTEGER PRIMARY KEY AUTOINCREMENT, trProfilPic BLOG, trFullName Text, trProfession Text, trMobile Text, trEmail Text, trPassword Text, trCIN BLOG, trXXX BLOG)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase travailleurDB, int oldVersion, int newVersion) {
        travailleurDB.execSQL("drop Table if exists ouvriers");
    }

    public Boolean insertData(byte[] trProfilPic,String trFullName, String trProfession, String trMobile, String trEmail, String trPassword, byte[] trCIN, byte[] trXXX){
        SQLiteDatabase travailleurDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trProfilPic", trProfilPic);
        contentValues.put("trFullName", trFullName);
        contentValues.put("trProfession", trProfession);
        contentValues.put("trMobile", trMobile);
        contentValues.put("trEmail", trEmail);
        contentValues.put("trPassword", trPassword);
        contentValues.put("trCIN", trCIN);
        contentValues.put("trXXX", trXXX);

        long result = travailleurDB.insert("ouvriers", null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }


    public Cursor getData(String sql){
        SQLiteDatabase travailleurDB = this.getReadableDatabase();
        return travailleurDB.rawQuery(sql, null);
    }



    public Boolean checkTravailleurEmail(String trEmail){

        SQLiteDatabase travailleurDB = this.getWritableDatabase();
        Cursor cursor = travailleurDB.rawQuery("select * from ouvriers where trEmail = ?", new String[]{trEmail});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkTravailleurEmailPassword(String trEmail, String trPassword){

        SQLiteDatabase travailleurDB = this.getWritableDatabase();
        Cursor cursor = travailleurDB.rawQuery("select * from ouvriers where trEmail = ? and trPassword = ?", new String[]{trEmail, trPassword});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}
