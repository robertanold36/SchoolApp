package com.school.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;




public class DatabaseSource extends SQLiteOpenHelper {


    private static final String DATABASE_NAME="School.db";
    private static final String TABLE_student="studentTable";
    private static final String COL_1="ID";
    private static final String COL_2="first_name";
    private static final String COL_3="middle_name";
    private static final String COL_4="last_name";
    private static final String COL_5="email";
    private static final String COL_6="username";
    private static final String COL_7="programme";
    private static final String COL_8="year";
    private static final String COL_9="phone_number";
    private static final String COL_10="gender";
    private static final String COL_11="date_of_birth";


    public DatabaseSource(@Nullable Context context) {
        super(context, DATABASE_NAME, null,
                1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL ("create table "+TABLE_student+"("+COL_1+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COL_2+" TEXT,"+COL_3+" TEXT, "+COL_4+" TEXT, "+COL_5+ " TEXT UNIQUE, " +
                ""+COL_6+ " TEXT UNIQUE, "+COL_7+ " TEXT, " +COL_8+" INTEGER,"
                +COL_9+" TEXT,"+COL_10+" TEXT,"+COL_11+" TEXT)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_student);

        onCreate(db);

    }


    public long createUser(String fname,String mname,String lname,String email,
                           String username,String programme,int year,
                           String phone_number,String gender,String DOB ){


              SQLiteDatabase db=this.getWritableDatabase();


               ContentValues content = new ContentValues ( );
               content.put (COL_2, fname);
               content.put (COL_3, mname);
               content.put (COL_4, lname);
               content.put (COL_5, email);
               content.put (COL_6, username);
               content.put (COL_7, programme);
               content.put (COL_8, year);
               content.put (COL_9, phone_number);
               content.put (COL_10, gender);
               content.put (COL_11, DOB);

               long res = db.insert (TABLE_student, null, content);

               db.close ( );
               return res;


    }

    public boolean checkUser(String username,String password){

        String[] columns={COL_1};
        SQLiteDatabase db=getReadableDatabase();
        String selection=COL_4 + " =? " + " and " + COL_5 + " =? ";
        String[] selectionArgs={username,password};
        Cursor cursor=db.query(TABLE_student,columns,selection,selectionArgs,null
                ,null,null);

        int count=cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;

    }


}
