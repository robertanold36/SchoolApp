package com.school.schoolapp;


import androidx.appcompat.app.AppCompatActivity;

import android.R.style;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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


public class RegisterActivity extends AppCompatActivity {

    //declare edited text Data type variable
    EditText first_nme,middle_nme,last_nme,mEmail,mUsername,mYear,mPhone,mDate;
    RadioGroup mGender;



    private static  final String TAG="RegisterActivity";

    Button mRegister;      //declare button data type for register button
    DatabaseSource db;      // object db to access class Datasource

    AutoCompleteTextView programme;

    private DatePickerDialog.OnDateSetListener dateSetListener; //reference variable for class date



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        first_nme=findViewById(R.id.fname);
        middle_nme=findViewById (R.id.mname);
        last_nme=findViewById (R.id.lname);
        mEmail=findViewById(R.id.email);
        mUsername=findViewById(R.id.username);
        programme=findViewById (R.id.programme);
        mYear=findViewById (R.id.year);
        mPhone=findViewById (R.id.phone);
        mGender=findViewById (R.id.gender);
        mDate=findViewById (R.id.date);

        
        mRegister=findViewById(R.id.register);
        db=new DatabaseSource (this); //instatiate reference variable db for database




        final String[] programs={"Bsc in computer science","Electronics","Engineering",
                "Telecommunication","law"}; //selected option view array for autocompleteview

        ArrayAdapter<String> adapter=new ArrayAdapter<> (this,
                android.R.layout.select_dialog_item,programs);//adapter to create Autocompleteview

        programme.setThreshold (1);//start display when input is 1
        programme.setAdapter (adapter); 
        

        //event handler to show datepickerdialog
        mDate.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance ();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day= calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(RegisterActivity.this,
                        style.Theme_Holo_Light_Dialog_MinWidth
                       ,dateSetListener,day,month,year);

                datePickerDialog.getWindow ().setBackgroundDrawable
                        (new ColorDrawable (Color.TRANSPARENT));

                datePickerDialog.show ();

            }
        });

        //event handler when date inserted in datePickerdDalog
        dateSetListener=new DatePickerDialog.OnDateSetListener () {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                Log.d (TAG,"onDateSet: yyyy/mm/dd: "+ year +"/"+ month +"/"+dayOfMonth);
                String date=year+"/"+month+"/"+dayOfMonth;
                mDate.setText (date);
            }
        };



        //event handler after clicking register button
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
                String programe=programme.getText ().toString ().trim ();
                int year=mYear.getInputType ();
                String phone=mPhone.getText ().toString ().trim ();
                String date=mDate.getText ().toString ().trim ();


                    if(TextUtils.isEmpty (first_name)){
                        first_nme.setError ("field is required");
                        return;
                    }

                    else if(TextUtils.isEmpty (last_name)){
                         last_nme.setError ("field is required");
                         return;
                    }

                    else if(TextUtils.isEmpty (middle_name)){
                        middle_nme.setError ("field is required");
                        return;
                    }
                    else if(TextUtils.isEmpty (email)){
                        mEmail.setError ("field is required");
                        return;
                    }

                    else if(TextUtils.isEmpty (username)){
                        mUsername.setError ("field is required");
                        return;
                    }
                    else if(TextUtils.isEmpty (programe)){
                        programme.setError ("field is required");
                        return;
                    }

                    else if (mYear.equals ("")){
                         mYear.setError ("field required");
                         return;
                    }

                    else if(TextUtils.isEmpty (phone)){
                        mPhone.setError ("field is required");
                        return;
                    }


                else if(mGender.getCheckedRadioButtonId ()<=0){
                    RadioButton malebtn=findViewById (R.id.male);
                    malebtn.setError ("field is required");
                    return;
                }

                    else if(TextUtils.isEmpty (date)){
                        mDate.setError ("field is required");
                        return;
                    }

                    else {
                        String gender=radioButton.getText ().toString ().trim ();


                        long res = db.createUser (first_name, middle_name, last_name,
                                email, username, programe, year, phone, gender, date);

                        if (res > 0) {

                            Toast.makeText (RegisterActivity.this,
                                    "succedd register a student",
                                    Toast.LENGTH_SHORT).show ( );
                            Intent intent=new Intent
                                    (RegisterActivity.this,AdminActivity.class);
                            startActivity (intent);
                        } else {

                            Toast.makeText (RegisterActivity.this,
                                    " register doesnt exist email or username arleady exist "
                                    , Toast.LENGTH_SHORT).show ();

                        }
                    }
               }

        });

    }

}
