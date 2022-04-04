package com.aryan.stumps11.CreateTeam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CVCDatabase  extends SQLiteOpenHelper {


    public CVCDatabase(@Nullable Context context) {
        super(context, "Capatin", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table CaptainList(id integer primary key autoincrement,cid varchar,cpid varchar,pname varchar,ppts varchar,pcr varchar,CaptainName varchar,vcName varchar)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void creteCaptain(String pid,String playerId,String pname,String ppts,String pcr,String C,String vc){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put("pid",pid);  //1  // 0
        cv.put("umob",playerId);//2  //1
        cv.put("pname",pname);//3  //2
//        cv.put("pcountry",pcountry);//4  //3
//        cv.put("prole",prole);//5  //4
        cv.put("ppts",ppts);//6 //5
        cv.put("pcr",pcr);//7  //6
        cv.put("Captain",C);//8  //7
        cv.put("vc",vc);//9  //8
        sqLiteDatabase.insert("CaptainList",null,cv);
    }

    public Cursor DisplayCaptain(String playerId){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cc=sqLiteDatabase.rawQuery("select * from CaptainList where cpid='"+playerId+"'",null);
        return cc;
    }


    public void UpdateCaptain(String Captain,String umob){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("update CaptainList set CaptainName ='"+Captain+"'where cpid='"+umob+"'");
    }

    public void UpdateViceCaptain(String VCaptain,String umob){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("update CaptainList set vcName ='"+VCaptain+"'where cpid='"+umob+"'");
    }
}
