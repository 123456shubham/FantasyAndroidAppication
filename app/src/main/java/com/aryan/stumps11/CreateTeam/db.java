package com.aryan.stumps11.CreateTeam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class db extends SQLiteOpenHelper {

    public db(@Nullable Context context) {
        super(context, "Stumpps11", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists Player(pname varchar,prole varchar,pcountry varchar,credits DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void Save(String pname, String prole, String pcountry, String credit) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("pname", pname);
        cv.put("prole", prole);
        cv.put("pcountry", pcountry);
        cv.put("credits", credit);
        sqLiteDatabase.insert("Player", null, cv);
    }

    public void Remove(String pname) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sqLiteDatabase.execSQL("delete from Player where pname='" + pname + "'");
    }

    public void Clear() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sqLiteDatabase.execSQL("delete from Player");
    }

    public Cursor CountRole(String role) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cc = sqLiteDatabase.rawQuery("select * from Player where prole='" + role + "'", null);
        return cc;
    }

    public Cursor CountCountry(String country) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cc = sqLiteDatabase.rawQuery("select * from Player where pcountry='" + country + "'", null);
        return cc;
    }

    public Cursor FullCount() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cc = sqLiteDatabase.rawQuery("select * from Player", null);
        return cc;
    }

    public Cursor Sum_of_Credits() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cc = sqLiteDatabase.rawQuery("select sum(credits) from Player", null);
        return cc;
    }
    public void deleteReminder() {
        SQLiteDatabase database = this.getWritableDatabase();
        //Sql query to  retrieve  data from the database
        database.execSQL("delete from Player");

    }

    public  boolean getChecked(String playerName){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cc = sqLiteDatabase.rawQuery("select * from Player where pname= '"+playerName+"'", null);
        boolean isTrue=false;
        if (cc.getCount()>0){

            isTrue=true;
        }
        return isTrue;
    }

    public int getCountRole(String role) {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cc = sqLiteDatabase.rawQuery("select * from Player where prole='" + role + "'", null);
        return cc.getCount();
    }

}
