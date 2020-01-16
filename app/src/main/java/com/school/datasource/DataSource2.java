package com.school.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataSource2 extends SQLiteOpenHelper {

    private static final String TABLE_ward="location";
    private static final String COL_1="ID";
    private static final String COL_region ="Region";
    private static final String COL_district ="District";
    private static final String COL_ward ="Ward";


    public DataSource2(@Nullable Context context) {
        super (context,DatabaseSource.DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        

        db.execSQL ("create table "+TABLE_ward+"("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_ward +" TEXT,"+ COL_district +" TEXT,"+ COL_region +" TEXT UNIQUE)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       
        db.execSQL ("DROP TABLE IF EXISTS "+TABLE_ward);
        onCreate (db);
    }

  

    public void insertWard(String ward,String district,String region){

        SQLiteDatabase db=this.getReadableDatabase ();
        ContentValues contentValues=new ContentValues ();
        contentValues.put (COL_ward,ward);
        contentValues.put (COL_district,district);
        contentValues.put(COL_region,region);
        db.insert (TABLE_ward,null,contentValues);
    }
}
