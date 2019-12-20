package com.school.schoolapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class DatabaseSource extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="register.db";
    private static final String TABLE_NAME="registerUser";
    private static final String TABLE_ADMIN="ADMIN_TABLE";
    private static final String COL_1="ID";
    private static final String COL_2="name";
    private static final String COL_3="email";
    private static final String COL_4="username";
    private static final String COL_5="password";

    public DatabaseSource(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +TABLE_NAME+"("+COL_1+ " INTEGER PRIMARY KEY AUTOINCREMENT" +
                ","+COL_2+" TEXT,"+COL_3+ " TEXT,"+COL_4+" TEXT,"+COL_5+ " TEXT)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL ("DROP TABLE IF EXISTS "+TABLE_ADMIN);
        onCreate(db);

    }


    public long createUser(String name,String email,String username,String password){


       SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("name",name);
        content.put("email",email);
        content.put("username",username);
        content.put("password",password);


        long res =db.insert("registerUser",null,content);


        db.close();
        return  res;

    }

    public boolean checkUser(String username,String password){

        String[] columns={COL_1};
        SQLiteDatabase db=getReadableDatabase();
        String selection=COL_4 + " =? " + " and " + COL_5 + " =? ";
        String[] selelctionArgs={username,password};
        Cursor cursor=db.query(TABLE_NAME,columns,selection,selelctionArgs,null
                ,null,null);

        int count=cursor.getCount();
        cursor.close();
        db.close();

        if(count>0) {
            return true;
        }

        else {
            return false;
        }

    }


}
