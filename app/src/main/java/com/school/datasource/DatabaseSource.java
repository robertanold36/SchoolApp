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
    private static final String TABLE_course="courseTable";
    private static final String TABLE_lecture="lectureTable";

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
    private static final String COL_12="password";





    private static final String COL_name="course_name";
    private static final String COL_code="course_code";
    private static final String COL_credit="course_credit";
    private static final String COL_semester ="course_semister";
    private static final String COL_year="course_year";
    private static final String COL_category="course_category";
    private static final String COL_programme="course_programme";

    public DatabaseSource(@Nullable Context context) {
        super(context, DATABASE_NAME, null,
                1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL ("create table "+TABLE_student+"("+COL_1+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COL_2+" TEXT,"+COL_3+" TEXT, "+COL_4+" TEXT, "+COL_5+ " TEXT UNIQUE, " +
                ""+COL_6+ " TEXT UNIQUE, "+COL_7+ " TEXT, " +COL_8+" TEXT,"
                +COL_9+" TEXT,"+COL_10+" TEXT,"+COL_11+" TEXT,"+COL_12+" TEXT)");

        db.execSQL ("create table "+TABLE_course+ "("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "
     +COL_name+" TEXT UNIQUE, "+COL_code+" TEXT UNIQUE, "+COL_credit+" TEXT, "+ COL_semester +" TEXT,"
                +COL_year+" TEXT, "+COL_category+" TEXT, "+COL_programme+" TEXT)");



        db.execSQL ("create table "+TABLE_lecture+"("+COL_1+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COL_2+" TEXT,"+COL_3+" TEXT, "+COL_4+" TEXT, "+COL_5+ " TEXT UNIQUE, " +
     ""+COL_6+ " TEXT UNIQUE,"+COL_code+" TEXT," +COL_9+" TEXT,"+COL_10+" TEXT,"+COL_11+" TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_student);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_course);
        onCreate(db);

    }


    public long createStudent(String fname,String mname,String lname,String email,
                           String username,String programme,String year,
                           String phone_number,String gender,String DOB ){


              SQLiteDatabase db=this.getWritableDatabase();


               ContentValues content = new ContentValues ();
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
               content.put (COL_12,lname);

               long res = db.insert (TABLE_student, null, content);

               db.close ();
               return res;


    }

    public long createCourse(String cname,String cCode,String ccredit,
                             String cSemister,String cYear,String cProgram,String cCategory)
    {
        SQLiteDatabase db=this.getWritableDatabase ();
        ContentValues contentValues=new ContentValues ();

        contentValues.put(COL_name,cname);
        contentValues.put(COL_code,cCode);
        contentValues.put(COL_credit,ccredit);
        contentValues.put(COL_semester,cSemister);
        contentValues.put(COL_year,cYear);
        contentValues.put(COL_programme,cProgram);
        contentValues.put(COL_category,cCategory);

        long res=db.insert (TABLE_course,null,contentValues);
        db.close ();
        return res;
    }


    public long createLecture(String fname,String mname,String lname,
     String email,String username,String course_code,String phone_number,String gender,String DOB)
    {
        SQLiteDatabase db=this.getWritableDatabase ();
        ContentValues content=new ContentValues ();
        content.put (COL_2, fname);
        content.put (COL_3, mname);
        content.put (COL_4, lname);
        content.put (COL_5, email);
        content.put (COL_6, username);
        content.put (COL_code,course_code);
        content.put (COL_9,phone_number);
        content.put (COL_10,gender);
        content.put (COL_11,DOB);

        long res=db.insert (TABLE_lecture,null,content);
        db.close ();
        return res;


    }




    public boolean checkUser(String username,String password){

        String[] columns={COL_1};
        SQLiteDatabase db=getReadableDatabase();
        String selection=COL_6 + " =? " + " and " + COL_12 + " =? ";
        String[] selectionArgs={username,password};
        Cursor cursor=db.query(TABLE_student,columns,selection,selectionArgs,null
                ,null,null);

        int count=cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;

    }


     public Student getDetails(String username){

        SQLiteDatabase db=this.getWritableDatabase ();
        String[] selectionArgs={username};
        Cursor cursor=db.rawQuery ("select*from "+TABLE_student+" where "+COL_6+"=?"
                ,selectionArgs);
        cursor.moveToFirst ();

        Student student=new Student();
        student.setFirst_name (cursor.getString (cursor.getColumnIndex (COL_2)));
        student.setMiddle_name (cursor.getString (cursor.getColumnIndex (COL_3)));
        student.setLast_name (cursor.getString (cursor.getColumnIndex (COL_4)));
        student.setEmail (cursor.getString (cursor.getColumnIndex (COL_5)));
        student.setProgramme (cursor.getString (cursor.getColumnIndex (COL_7)));
        student.setPhone_number (cursor.getString (cursor.getColumnIndex (COL_9)));

        cursor.close ();
        db.close ();
        return student;

     }
}
