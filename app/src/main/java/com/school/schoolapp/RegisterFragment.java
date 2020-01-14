package com.school.schoolapp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;


import android.R.style;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Random;


public class RegisterFragment extends Fragment {

    //declare edited text Data type variable
   private EditText first_nme,middle_nme,last_nme,mEmail,mYear,mPhone,mDate;
   private RadioGroup mGender;



    private static  final String TAG="RegisterFragment";

    Button mRegister;      //declare button data type for register button
    DatabaseSource db;      // object db to access class Data_source

    AutoCompleteTextView programme;

    private DatePickerDialog.OnDateSetListener dateSetListener; //reference variable for class date

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.activity_register,container,false);
    }


    @Override
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onActivityCreated(@Nullable  Bundle savedInstanceState) {
         super.onActivityCreated (savedInstanceState);


        first_nme=getActivity ().findViewById(R.id.fname);
        middle_nme=getActivity ().findViewById (R.id.mname);
        last_nme=getActivity ().findViewById (R.id.lname);
        mEmail=getActivity ().findViewById(R.id.email);
        programme=getActivity ().findViewById (R.id.programme);
        mYear=getActivity ().findViewById (R.id.year);
        mPhone=getActivity ().findViewById (R.id.phone);
        mGender=getActivity ().findViewById (R.id.gender);
        mDate=getActivity ().findViewById (R.id.date);

        
        mRegister=getActivity ().findViewById(R.id.register);
        db=new DatabaseSource (getContext ().getApplicationContext ());




        final String[] programs={"Bsc in computer science","Electronics","Engineering",
                "Telecommunication","law"}; //selected option view array for autocomplete

        ArrayAdapter<String> adapter=new ArrayAdapter<> (getActivity ().getApplicationContext (),
                android.R.layout.select_dialog_item,programs);//adapter to create Autocomplete

        programme.setThreshold (1);//start display when input is 1
        programme.setAdapter (adapter); 
        

        //event handler to show date_picker_dialog
        mDate.setOnClickListener (new View.OnClickListener () {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance ();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day= calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(getActivity (),
                        style.Theme_Holo_Light_Dialog_MinWidth
                       ,dateSetListener,day,month,year);

                datePickerDialog.getWindow ( ).setBackgroundDrawable
                        (new ColorDrawable (Color.TRANSPARENT));

                datePickerDialog.show ();

            }
        });

        //event handler when date inserted in date_Picker_dialog
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
                RadioButton radioButton=getActivity ().findViewById (selectedId);

                String first_name=first_nme.getText ().toString ().trim ();
                String middle_name=middle_nme.getText ().toString ().trim ();
                String last_name=last_nme.getText ().toString ().trim ();
                String email=mEmail.getText ().toString ().trim ();
                String username1="2000-01-";
                String program=programme.getText ().toString ().trim ();
                String year=mYear.getText ().toString ().trim ();
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


                    else if(TextUtils.isEmpty (program)){
                        programme.setError ("field is required");

                    }

                    else if(TextUtils.isEmpty (year)){
                        mYear.setError ("field is required");

                    }


                    else if(TextUtils.isEmpty (phone)){
                        mPhone.setError ("field is required");

                    }


                 else if(mGender.getCheckedRadioButtonId ()<=0){
                    RadioButton male_btn=getActivity ().findViewById (R.id.male);
                    male_btn.setError ("field is required");

                }

                    else if(TextUtils.isEmpty (date)){
                        mDate.setError ("field is required");

                    }

                    else {

                        Random random=new Random ();
                        random.nextInt (10000);
                        String username=username1+String.format ("%04d",random.nextInt (10000));
                        String gender=radioButton.getText ().toString ().trim ();


                        long res = db.createStudent (first_name, middle_name, last_name,
                                email, username, program, year, phone, gender, date);

                        if (res > 0) {

                            db.createUser (username,last_name,"student");

                            Toast.makeText (getActivity ( ).getApplicationContext (),
                                       "succeed register a student",
                                       Toast.LENGTH_SHORT).show ( );
                               Intent intent = new Intent
                                       (getActivity ( ).getApplicationContext ( ), AdminActivity.class);
                               startActivity (intent);
                               getActivity ( ).finish ( );


                        } else {

                            Toast.makeText (getActivity ().getApplicationContext (),
                                    " register doesn't exist email or username already exist "
                                    , Toast.LENGTH_SHORT).show ();

                        }
                    }
               }

        });

    }

}
