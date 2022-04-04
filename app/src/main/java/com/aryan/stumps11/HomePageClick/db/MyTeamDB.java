package com.aryan.stumps11.HomePageClick.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyTeamDB extends SQLiteOpenHelper {


    public MyTeamDB(@Nullable Context context) {
        super(context, "myTeam", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
