package com.example.teamapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper2 extends SQLiteOpenHelper
{
    public DBHelper2(Context context)
    {
        super(context,"householddb_2",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table housetbl " + "(number integer not null primary key autoincrement, " +
                "date DATE not null, content CHAR(10) not null, money CHAR(15) not null, category CHAR(4) not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS housetbl;");
        onCreate(db);
    }
}