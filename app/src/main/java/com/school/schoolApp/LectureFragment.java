package com.school.schoolApp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.school.datasource.DatabaseSource;

import java.util.ArrayList;
import java.util.Calendar;


public class LectureFragment extends Fragment {

   private EditText first_nme, middle_nme,last_nme,mEmail,mPhone,mDate;
   private   AutoCompleteTextView mCourse_code;
    private String Region;
    private String District;

   private RadioGroup mGender;

    private DatePickerDialog.OnDateSetListener dateSetListener;

    private static final String TAG="LectureFragment";

    DatabaseSource db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.activity_lecture,container,false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);


        first_nme =getActivity ().findViewById (R.id.fname);
        middle_nme =getActivity ().findViewById (R.id.mname);
        last_nme =getActivity ().findViewById (R.id.lname);
        mEmail=getActivity ().findViewById (R.id.email);
        mCourse_code=getActivity ().findViewById (R.id.course_code);
        mPhone=getActivity ().findViewById (R.id.phone);
        mDate=getActivity ().findViewById (R.id.date);

        mGender=getActivity ().findViewById (R.id.gender);
        Button mRegister = getActivity ().findViewById (R.id.register);


        db=new DatabaseSource (getContext ().getApplicationContext ());


        String[] code={"CS 150","CS 151","CS 152","CS 153",
                "CS 154","CS 155","CS 156","CS 157","CS 158","CS 159"};

        ArrayAdapter<String> adapter=new ArrayAdapter<> (getActivity ().getApplicationContext (),
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
                        (getActivity (),
                   android.R.style.Theme_Holo_Dialog_MinWidth,dateSetListener,year,month,day);

                datePickerDialog.getWindow ( ).setBackgroundDrawable
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


        //Spinner for location implementation
        final Spinner sp1=getActivity ().findViewById (R.id.sp1);
        final Spinner sp2=getActivity ().findViewById (R.id.sp2);
        final Spinner sp3=getActivity ().findViewById (R.id.sp3);

        ArrayList<String>  regions;
        regions=db.getRegion ();

        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<>(getActivity (),android.R.layout.simple_dropdown_item_1line,
                regions);

        sp1.setAdapter (arrayAdapter1);



        sp1.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Region =sp1.getSelectedItem ().toString ();

                Region =parent.getItemAtPosition (position).toString ();
                ArrayList<String>  districts;
                districts=db.getDistrict (Region);
                ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<>(getActivity (),
                       android.R.layout.simple_dropdown_item_1line,districts);
                sp2.setAdapter (arrayAdapter2);

                sp2.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                        District =parent.getItemAtPosition (position).toString ();
                        ArrayList<String> wards=db.getWard (Region, District);
                        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter<> (getActivity (),
                                android.R.layout.simple_dropdown_item_1line,wards);
                        sp3.setAdapter (arrayAdapter3);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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


                else if(TextUtils.isEmpty (course_code)){
                    mCourse_code.setError ("field is required");

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

                else{



                    String gender=radioButton.getText ().toString ().trim ();
                    String L_region=sp1.getSelectedItem ().toString ();
                    String L_district=sp2.getSelectedItem ().toString ();
                    String L_ward=sp3.getSelectedItem ().toString ();




                    long res=db.createLecture (first_name,middle_name,last_name,
                       email,course_code,phone,gender,date,L_region,L_district,L_ward);

                    if(res>0){


                        Toast.makeText (getActivity ().getApplicationContext (),
                                "new staff lecture has " +
                                "been registered", Toast.LENGTH_SHORT).show ( );

                        startActivity (new Intent (getActivity ().getApplicationContext ()
                                ,AdminActivity.class));
                        getActivity ().finish ();
                    }

                    else {

                        Toast.makeText (getActivity ().getApplicationContext (),
                                "fail to register new lecture",
                                Toast.LENGTH_SHORT).show ( );
                    }
                }


            }
        });

    }
}
