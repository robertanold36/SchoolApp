package com.school.schoolapp;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.school.datasource.DatabaseSource;

import java.util.Calendar;
import java.util.Objects;


public class LectureActivity extends AppCompatActivity {

    EditText first_nme, middle_nme,last_nme,mEmail,mUsername,mPhone,mDate;
    AutoCompleteTextView mCourse_code;

    RadioGroup mGender;

    Button mRegister;
    DatePickerDialog.OnDateSetListener dateSetListener;

    private static final String TAG="LectureActivity";

    DatabaseSource db;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId ()==android.R.id.home){
            super.onBackPressed ();
            finish ();

        }
        return true;
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_lecture);

        first_nme =findViewById (R.id.fname);
        middle_nme =findViewById (R.id.mname);
        last_nme =findViewById (R.id.lname);
        mEmail=findViewById (R.id.email);
        mUsername=findViewById (R.id.username);
        mCourse_code=findViewById (R.id.course_code);
        mPhone=findViewById (R.id.phone);
        mDate=findViewById (R.id.date);

        mGender=findViewById (R.id.gender);
        mRegister=findViewById (R.id.register);

        db=new DatabaseSource (this); //instantiate reference variable db for database

        Toolbar toolbar=findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        Objects.requireNonNull (getSupportActionBar ( )).setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setTitle ("back");

        String[] code={"CS 150","CS 151","CS 152","CS 153",
                "CS 154","CS 155","CS 156","CS 157","CS 158","CS 159"};

        ArrayAdapter<String> adapter=new ArrayAdapter<> (LectureActivity.this,
                android.R.layout.select_dialog_item,code);

         mCourse_code.setThreshold (1);
         mCourse_code.setAdapter (adapter);


        mDate.setOnClickListener (new View.OnClickListener ( ) {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                Calendar calendar=Calendar.getInstance ();

                int year=calendar.get (Calendar.YEAR);
                int month=calendar.get (Calendar.MONTH);
                int day=calendar.get (Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog
                        (LectureActivity.this,
                   android.R.style.Theme_Holo_Dialog_MinWidth,dateSetListener,year,month,day);

                Objects.requireNonNull (datePickerDialog.getWindow ( )).setBackgroundDrawable
                        (new ColorDrawable (Color.TRANSPARENT));

                datePickerDialog.show ();


            }
        });

        dateSetListener=new DatePickerDialog.OnDateSetListener ( ) {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Log.d(TAG,"onDateSet: yyyy/mm/dd "+year+"/"+month+"/"+dayOfMonth);
                month=month+1;
                String date=year+"/"+month+"/"+dayOfMonth;
                mDate.setText (date);
            }
        };

        mRegister.setOnClickListener (new View.OnClickListener ( ) {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                int selectedId=mGender.getCheckedRadioButtonId ();
                RadioButton radioButton=findViewById (selectedId);

                String first_name=first_nme.getText ().toString ().trim ();
                String middle_name=middle_nme.getText ().toString ().trim ();
                String last_name=last_nme.getText ().toString ().trim ();
                String email=mEmail.getText ().toString ().trim ();
                String username=mUsername.getText ().toString ().trim ();
                String course_code=mCourse_code.getText ().toString ().trim ();
                String phone=mPhone.getText ().toString ().trim ();
                String date=mDate.getText ().toString ().trim ();


                if(TextUtils.isEmpty (first_name)){
                    first_nme.setError ("field is required");

                }

                else if(TextUtils.isEmpty (last_name)){
                    last_nme.setError ("field is required");

                }

                else if(TextUtils.isEmpty (middle_name)){
                    middle_nme.setError ("field is required");
                }
                else if(TextUtils.isEmpty (email)){
                    mEmail.setError ("field is required");

                }

                else if(TextUtils.isEmpty (username)){
                    mUsername.setError ("field is required");

                }
                else if(TextUtils.isEmpty (course_code)){
                    mCourse_code.setError ("field is required");

                }


                else if(TextUtils.isEmpty (phone)){
                    mPhone.setError ("field is required");

                }


                else if(mGender.getCheckedRadioButtonId ()<=0){
                    RadioButton male_btn=findViewById (R.id.male);
                    male_btn.setError ("field is required");

                }

                else if(TextUtils.isEmpty (date)){
                    mDate.setError ("field is required");

                }

                else{


                    String gender=radioButton.getText ().toString ().trim ();

                    long res=db.createLecture (first_name,middle_name,last_name,
                            email,username,course_code,phone,gender,date);

                    if(res>0){
                        Toast.makeText (LectureActivity.this, "new staff lecture has " +
                                "been registered", Toast.LENGTH_SHORT).show ( );

                        startActivity (new Intent (getApplicationContext (),AdminActivity.class));
                    }

                    else {

                        Toast.makeText (LectureActivity.this, "fail to register new lecture",
                                Toast.LENGTH_SHORT).show ( );
                    }
                }


            }
        });

    }
}
