package com.aryan.stumps11.EditTeam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class EditDatabase extends SQLiteOpenHelper {

    public EditDatabase(@Nullable Context context) {
        super(context, "name", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Player(id integer primary key autoincrement,pid varchar,umob varchar,pname varchar,pcountry varchar,prole varchar,ppts varchar,pcr varchar,Captain varchar,vc varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void EditAddPlayer(String pid,String umob,String pname,String pcountry,String prole,String ppts,String pcr,String C,String vc){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put("pid",pid);  //1  // 0
        cv.put("umob",umob);//2  //1
        cv.put("pname",pname);//3  //2
        cv.put("pcountry",pcountry);//4  //3
        cv.put("prole",prole);//5  //4
        cv.put("ppts",ppts);//6 //5
        cv.put("pcr",pcr);//7  //6
        cv.put("Captain",C);//8  //7
        cv.put("vc",vc);//9  //8
        sqLiteDatabase.insert("Player",null,cv);
    }

    public void EditUpdate(String Captain,String pid){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("update Player set Captain ='"+Captain+"'where pid='"+pid+"'");
    }

    public void EditUpdatevc(String VCaptain,String pid){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("update Player set vc ='"+VCaptain+"'where pid='"+pid+"'");
    }

    public void EditRemovePlayer(String umob,String pid){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("delete from Player where umob='"+umob+"'and pid='"+pid+"'");
    }

    public Cursor EditDisplayPlayer(String umob){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cc=sqLiteDatabase.rawQuery("select * from Player where umob='"+umob+"'",null);
        return cc;
    }

    public void EditTruncate(String umob){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("delete * from Player where umob='"+umob+"");
    }

    public void EditremoveOldId(){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String query = "delete from name where id=id" ;
        //Sql query to  retrieve  data from
        sqLiteDatabase.execSQL(query);


    }

    public void  EditdeleteReminder(){
        SQLiteDatabase database = this.getWritableDatabase();
        //Sql query to  retrieve  data from the database
        database.execSQL("delete from Player");

    }

}
