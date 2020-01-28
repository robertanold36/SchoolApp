package com.school.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DatabaseSource extends SQLiteOpenHelper {

    Location location=new Location ();


    public static final String DATABASE_NAME="School.db";
    private static final String TABLE_student="studentTable";
    private static final String TABLE_course="courseTable";
    private static final String TABLE_lecture="lectureTable";
    private static final String TABLE_user="table_user";
    private static final String TABLE_location ="location";


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
    private static final String COL_13="roles";





    private static final String COL_name="course_name";
    private static final String COL_code="course_code";
    private static final String COL_credit="course_credit";
    private static final String COL_semester ="course_semister";
    private static final String COL_year="course_year";
    private static final String COL_category="course_category";
    private static final String COL_programme="course_programme";


    private static final String COL_region ="Region";
    private static final String COL_district ="District";
    private static final String COL_ward ="Ward";


    public DatabaseSource(@Nullable Context context) {
        super(context, DATABASE_NAME, null,
                1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL ("create table "+TABLE_student+"("+COL_1+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COL_2+" TEXT,"+COL_3+" TEXT, "+COL_4+" TEXT, "+COL_5+ " TEXT UNIQUE, " +
                ""+COL_6+ " TEXT UNIQUE, "+COL_7+ " TEXT, " +COL_8+" TEXT,"
                +COL_9+" TEXT,"+COL_10+" TEXT,"+COL_11+" TEXT,"+COL_12+" TEXT,"+COL_region+" TEXT,"
                +COL_district+" TEXT,"+COL_ward+" TEXT)");


        db.execSQL ("create table "+TABLE_course+ "("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "
     +COL_name+" TEXT UNIQUE, "+COL_code+" TEXT UNIQUE, "+COL_credit+" TEXT, "+ COL_semester +" TEXT,"
                +COL_year+" TEXT, "+COL_category+" TEXT, "+COL_programme+" TEXT)");



        db.execSQL ("create table "+TABLE_lecture+"("+COL_1+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COL_2+" TEXT,"+COL_3+" TEXT, "+COL_4+" TEXT, "+COL_5+ " TEXT UNIQUE, " +
     ""+COL_6+ " TEXT UNIQUE,"+COL_code+" TEXT," +COL_9+" TEXT,"+COL_10+" TEXT,"+COL_11+" TEXT," +
                ""+COL_region+" TEXT,"+COL_district+" TEXT,"+COL_ward+" TEXT)");


        db.execSQL ("create table "+TABLE_user+"("+COL_1+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ""+COL_6+" TEXT UNIQUE,"+COL_12+" TEXT,"+COL_13+" TEXT)");


        db.execSQL ("create table "+ TABLE_location +"("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_ward +" TEXT,"+ COL_district +" TEXT,"+ COL_region +" TEXT)");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_student);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_course);
        db.execSQL ("DROP TABLE IF EXISTS "+TABLE_lecture);
        db.execSQL ("DROP TABLE IF EXISTS "+TABLE_user);
        db.execSQL ("DROP TABLE IF EXISTS "+ TABLE_location);
        onCreate(db);

    }

    private String generateStudentRegNo(){

        SQLiteDatabase db = this.getReadableDatabase ();

        Cursor c = db.rawQuery ("SELECT * FROM " + TABLE_student , null);
        int C = (c.getCount ())+1;
        String cc = String.valueOf (C);
        String reg = "0000".substring (cc.length()) + cc;

        return reg;
    }

    private String generateStaffRegNo(){

        SQLiteDatabase db = this.getReadableDatabase ();

        Cursor c = db.rawQuery ("SELECT * FROM " + TABLE_lecture , null);
        int C = (c.getCount ())+1;
        String cc = String.valueOf (C);
        String reg = "0000".substring (cc.length()) + cc;

        return reg;
    }


    public long createStudent(String first_name,String middle_name,String last_name,String email,
                           String programme,String year,
                           String phone_number,String gender,String DOB,String region,String district,String ward ){


              SQLiteDatabase db=this.getWritableDatabase();

              String reg = generateStudentRegNo ();
              String RegNo = "2019-04-" + reg;

               ContentValues content = new ContentValues ();
               ContentValues contentValues=new ContentValues ();
               contentValues.put (COL_6,RegNo);
               contentValues.put (COL_12,last_name);
               contentValues.put (COL_13,"student");

               content.put (COL_2, first_name);
               content.put (COL_3, middle_name);
               content.put (COL_4, last_name);
               content.put (COL_5, email);
               content.put (COL_6, RegNo);
               content.put (COL_7, programme);
               content.put (COL_8, year);
               content.put (COL_9, phone_number);
               content.put (COL_10, gender);
               content.put (COL_11, DOB);
               content.put (COL_12,last_name);
               content.put (COL_region,region);
               content.put (COL_district,district);
               content.put (COL_ward,ward);

           db.insert (TABLE_user,null,contentValues);


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
     String email,String course_code,String phone_number,String gender,
                              String DOB,String region,String district,String ward)
    {

        String reg = generateStaffRegNo ();
        String RegNo = "2018-04-" + reg;

        ContentValues contentValues=new ContentValues ();
        contentValues.put (COL_6,RegNo);
        contentValues.put (COL_12,lname);
        contentValues.put (COL_13,"staff");

        SQLiteDatabase db=this.getWritableDatabase ();
        ContentValues content=new ContentValues ();
        content.put (COL_2, fname);
        content.put (COL_3, mname);
        content.put (COL_4, lname);
        content.put (COL_5, email);
        content.put (COL_6, RegNo);
        content.put (COL_code,course_code);
        content.put (COL_9,phone_number);
        content.put (COL_10,gender);
        content.put (COL_11,DOB);
        content.put (COL_region,region);
        content.put (COL_district,district);
        content.put (COL_ward,ward);

        db.insert (TABLE_user,null,contentValues);

        long res=db.insert (TABLE_lecture,null,content);
        db.close ();
        return res;

    }


    public boolean checkUser(String username, String password){

        SQLiteDatabase db=getReadableDatabase();
        String[] selectionArgs={username,password};
        Cursor cursor=db.rawQuery ("select*from "+TABLE_user+" where "+COL_6+"=? and "
                +COL_12+"=?",selectionArgs);

        int count=cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;

    }

    public User getRoles(String username){

        SQLiteDatabase db=this.getWritableDatabase ();
        String[] selectionArgs={username};
        User user=new User ();

        Cursor cursor=db.rawQuery ("select*from "+TABLE_user+" where "
                +COL_6+"=?",selectionArgs);

        if(cursor.moveToFirst ()){
            do{
                user.setRole (cursor.getString (3));

            }while (cursor.moveToNext ());
        }
        return user;
    }




    public Student getStudentProgramme(String username){

        SQLiteDatabase db=this.getWritableDatabase ();
        String[] selectionArgs={username};
        Cursor cursor=db.rawQuery ("select*from "+TABLE_student+" where "+COL_6+"=?"
                ,selectionArgs);

        Student student=new Student ();
        cursor.moveToFirst ();
              student.setProgramme (cursor.getString (6));
         cursor.close ();
        db.close ();
        return student;

     }

     public List<Course> getCourses(String programme){

        SQLiteDatabase db=this.getWritableDatabase ();
        String[] selectionArgs={programme};

        List<Course> courses=new ArrayList<> ();

        Cursor cursor=db.rawQuery ("select*from "+TABLE_course+" where "+COL_programme+"=?"
                ,selectionArgs);

        if(cursor.moveToFirst ()){

            do {

                Course course=new Course ();

                course.setCourse_name (cursor.getString (1));
                course.setCourse_code (cursor.getString (2));

                courses.add (course);

            }while (cursor.moveToNext ());
        }
        return courses;

     }


     public boolean changePassword(String username,String password,String newPassword){

        SQLiteDatabase db=this.getWritableDatabase ();
        String selection=COL_6+"=?"+" and "+COL_12+"=?";
        String[] selectionArgs={username,password};
        ContentValues contentValues=new ContentValues ();
        contentValues.put (COL_12,newPassword);

      int res= db.update (TABLE_student,contentValues,selection,selectionArgs);
      if(res>0){

          return true;
      }
      else {
          return false;
      }

     }


     public List<Student> viewAllStudents(){

        SQLiteDatabase db=this.getWritableDatabase ();
        List<Student> students=new ArrayList<> ();

        Cursor cursor=db.rawQuery ("select*from "+TABLE_student,null);

        if(cursor.moveToFirst ()) {
            do {
                Student student=new Student ();
                student.setUsername (cursor.getString (5));
                student.setProgramme (cursor.getString (6));
                student.setYear (cursor.getString (7));

                students.add(student);

            }while (cursor.moveToNext ());

        }

        return students;
     }


    public List<Course> viewAllCourses(){

        SQLiteDatabase db=this.getWritableDatabase ();
        List<Course> courses=new ArrayList<> ();

        Cursor cursor=db.rawQuery ("select*from "+TABLE_course,null);

        if(cursor.moveToFirst ()) {
            do {
                Course course=new Course ();
                course.setCourse_name (cursor.getString (1));
                course.setCourse_code (cursor.getString (2));
                course.setCourse_category (cursor.getString (6));

                courses.add(course);

            }while (cursor.moveToNext ());

        }

        return courses;
    }


    public Student getStudentDetails(String username){

        SQLiteDatabase db=this.getWritableDatabase ();
        String[] selectionArgs={username};
        Student student=new Student ();


        Cursor cursor=db.rawQuery ("select*from "+TABLE_student+" where "+COL_6+"=?",selectionArgs);

        if(cursor.moveToFirst ()){
            do {

                student.setFirst_name (cursor.getString (1));
                student.setLast_name (cursor.getString (3));
                student.setYear (cursor.getString (7));
                student.setProgramme (cursor.getString (6));
                student.setGender (cursor.getString (9));
                student.setEmail (cursor.getString (4));



            }while(cursor.moveToNext ());
        }
        return student;
    }


    public void createUser(String username,String password,String role){

        SQLiteDatabase db=this.getWritableDatabase ();


        ContentValues contentValues=new ContentValues ();

        contentValues.put (COL_6,username);
        contentValues.put (COL_12,password);
        contentValues.put (COL_13,role);

       db.insert (TABLE_user,null,contentValues);

    }


    public void insertWard(String ward,String district,String region){

        SQLiteDatabase db=this.getReadableDatabase ();
        ContentValues contentValues=new ContentValues ();
        contentValues.put (COL_ward,ward);
        contentValues.put (COL_district,district);
        contentValues.put(COL_region,region);
        db.insert (TABLE_location,null,contentValues);
    }

    public ArrayList<String> getWard(String region,String district){

        SQLiteDatabase db=this.getWritableDatabase ();
        ArrayList<String> ward=new ArrayList<> ();
        String[] selectionArgs={region,district};
        Cursor cursor=db.rawQuery ("select distinct "+COL_ward+" from "+TABLE_location+"" +
                " where "+COL_region+"=? and "+COL_district+"=?",selectionArgs);
        if(cursor.getCount ()>0){
            while(cursor.moveToNext ()){
                ward.add (cursor.getString (cursor.getColumnIndex (COL_ward)));
            }
        }
        return ward;

    }


    public ArrayList<String> getDistrict(String region){

        SQLiteDatabase db=this.getWritableDatabase ();
        ArrayList<String> district=new ArrayList<> ();
        String[] selectionArgs={region};
        Cursor cursor=db.rawQuery ("select distinct "+COL_district+" from "+TABLE_location+
                " where "+COL_region+"=?",selectionArgs);

        if(cursor.getCount ()>0){
            while(cursor.moveToNext ()){
                district.add (cursor.getString (cursor.getColumnIndex (COL_district)));
            }
        }
        return district;

    }



    public ArrayList<String> getRegion(){

        SQLiteDatabase db=this.getWritableDatabase ();
        ArrayList<String> region=new ArrayList<> ();
        Cursor cursor=db.rawQuery ("select distinct "+COL_region+" from "+TABLE_location,
                null);
        if(cursor.getCount ()>0){
            while(cursor.moveToNext ()){
                region.add (cursor.getString (cursor.getColumnIndex (COL_region)));
            }
        }
        return region;

    }
}
