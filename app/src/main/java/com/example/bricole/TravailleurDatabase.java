package com.example.bricole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TravailleurDatabase extends SQLiteOpenHelper {

    //Table
    public static final String TABLE_NAME= "worker";

    //Columns
    public static final String col_1="IDTr";
    public static final String col_2="trProfilPic";
    public static final String col_3="trFullName";
    public static final String col_4="trProfession";
    public static final String col_5="trMobile";
    public static final String col_6="trVille";
    public static final String col_7="trEmail";
    public static final String col_8="trPassword";
    public static final String col_9="trCIN";
    public static final String col_10="trCE";

    public static final String col_11="trDescription";
    public static final String col_12="trWorkImage1";
    public static final String col_13="trWorkImage2";
    public static final String col_14="trWorkImage3";
    public static final String col_15="trWorkImage4";

    public TravailleurDatabase( Context context) {
        super(context, "worker.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase workerDB) {
        workerDB.execSQL("create Table worker(IDTr INTEGER PRIMARY KEY AUTOINCREMENT, trProfilPic BLOG, trFullName Text, " +
                "trProfession Text, trMobile Text, trVille Text, trEmail Text, trPassword Text, trCIN BLOG, trCE BLOG, " +
                "trDescription Text, trWorkImage1 BLOG, trWorkImage2 BLOG, trWorkImage3 BLOG, trWorkImage4 BLOG)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase workerDB, int oldVersion, int newVersion) {
        workerDB.execSQL("drop Table if exists worker");
    }

    public Boolean insertData(byte[] trProfilPic,String trFullName, String trProfession, String trMobile, String trVille,
                              String trEmail, String trPassword, byte[] trCIN, byte[] trCE, String trDescription,
                              byte[] trWorkImage1, byte[] trWorkImage2, byte[] trWorkImage3, byte[] trWorkImage4){

        SQLiteDatabase workerDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2, trProfilPic);
        contentValues.put(col_3, trFullName);
        contentValues.put(col_4, trProfession);
        contentValues.put(col_5, trMobile);
        contentValues.put(col_6, trVille);
        contentValues.put(col_7, trEmail);
        contentValues.put(col_8, trPassword);
        contentValues.put(col_9, trCIN);
        contentValues.put(col_10, trCE);

        contentValues.put(col_11, trDescription);
        contentValues.put(col_12, trWorkImage1);
        contentValues.put(col_13, trWorkImage2);
        contentValues.put(col_14, trWorkImage3);
        contentValues.put(col_15, trWorkImage4);

        long result = workerDB.insert("worker", null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getAllWorkers()
    {
        //creation d'une instance de la bd
        SQLiteDatabase workerDB  = this.getWritableDatabase();
        Cursor result= workerDB.rawQuery(" select * from "+TABLE_NAME, null);
        return result;
    }

    public Cursor getCurrentWorker(String email)
    {
        //creation d'une instance de la bd
        SQLiteDatabase workerDB  = this.getWritableDatabase();
        Cursor cursor = workerDB.rawQuery("select * from worker where trEmail = ?", new String[]{email});
        return cursor;
    }


    public boolean update( Integer trID, byte[] trProfilPic,String trFullName, String trProfession, String trMobile, String trVille,
                           String trEmail, String trPassword, byte[] trCIN, byte[] trCE,String trDescription,
                           byte[] trWorkImage1, byte[] trWorkImage2, byte[] trWorkImage3, byte[] trWorkImage4)
    {
        SQLiteDatabase workerDB = this.getWritableDatabase();
        ContentValues valeursDeContenu= new ContentValues();
        //remplissage de nouvelles valeurs
        valeursDeContenu.put(col_1,trID);
        valeursDeContenu.put(col_2,trProfilPic);
        valeursDeContenu.put(col_3,trFullName);
        valeursDeContenu.put(col_4,trProfession);
        valeursDeContenu.put(col_5,trMobile);
        valeursDeContenu.put(col_6,trEmail);
        valeursDeContenu.put(col_7,trEmail);
        valeursDeContenu.put(col_8,trPassword);
        valeursDeContenu.put(col_9,trCIN);
        valeursDeContenu.put(col_10,trCE);

        valeursDeContenu.put(col_11, trDescription);
        valeursDeContenu.put(col_12, trWorkImage1);
        valeursDeContenu.put(col_13, trWorkImage2);
        valeursDeContenu.put(col_14, trWorkImage3);
        valeursDeContenu.put(col_15, trWorkImage4);

        //mise à jour
        workerDB.update(TABLE_NAME,valeursDeContenu, "IDTr =?", new String[]{Integer.toString(trID)});
        return true;
    }

    public Boolean checkTravailleurEmail(String trEmail){

        SQLiteDatabase workerDB = this.getWritableDatabase();
        Cursor cursor = workerDB.rawQuery("select * from worker where trEmail = ?", new String[]{trEmail});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkTravailleurEmailPassword(String trEmail, String trPassword){

        SQLiteDatabase workerDB = this.getWritableDatabase();
        Cursor cursor = workerDB.rawQuery("select * from worker where trEmail = ? and trPassword = ?", new String[]{trEmail, trPassword});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}
