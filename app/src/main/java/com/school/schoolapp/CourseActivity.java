package com.school.schoolapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.school.datasource.DatabaseSource;

import java.util.Objects;


public class CourseActivity extends AppCompatActivity {

    EditText course_name,course_code,course_credit, course_semester,course_year;
    RadioGroup category;
    AutoCompleteTextView autoCompleteTextView;
    Button mRegister;
    DatabaseSource db;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId ( ) == android.R.id.home) {
            startActivity (new Intent (getApplicationContext ( ), AdminActivity.class));
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_course);

        db=new DatabaseSource (this);

        Toolbar toolbar=findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        Objects.requireNonNull (getSupportActionBar ( )).setDisplayHomeAsUpEnabled (true);
        getSupportActionBar().setDisplayShowTitleEnabled (true);
        getSupportActionBar ().setTitle ("back");


        course_name=findViewById (R.id.cname);
        course_code=findViewById (R.id.cCode);
        course_credit=findViewById (R.id.cCredit);
        course_semester =findViewById (R.id.cSemister);
        course_year=findViewById (R.id.cYear);
        mRegister=findViewById (R.id.register);
        autoCompleteTextView=findViewById (R.id.programme);

        category=findViewById (R.id.category);

        final String[] program={"Bsc in computer science","Telecommunication",
                "Electronics","Engineering","law"};

        ArrayAdapter<String> adapter= new ArrayAdapter<>
                (this,android.R.layout.select_dialog_item,program);

        autoCompleteTextView.setThreshold (1);
        autoCompleteTextView.setAdapter (adapter);



        mRegister.setOnClickListener (new View.OnClickListener ( ) {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                int selectedItem=category.getCheckedRadioButtonId ();
                RadioButton radioButton=findViewById (selectedItem);

                String name_course=course_name.getText ().toString ().trim ();
                String code_course=course_code.getText ().toString ().trim ();
                String credit_course=course_credit.getText ().toString ().trim ();
                String semester_course= course_semester.getText ().toString ().trim ();
                String year_course=course_year.getText ().toString ().trim ();
                String program_course=autoCompleteTextView.getText ().toString ().trim ();

                if(TextUtils.isEmpty (name_course)){
                    course_name.setError ("Text field is empty");

                }
               else if(TextUtils.isEmpty (code_course)){
                    course_code.setError ("Text field is empty");

                }
               else if(TextUtils.isEmpty (credit_course)){
                    course_credit.setError ("Text field is empty");

                }
                else if(TextUtils.isEmpty (semester_course)){
                    course_semester.setError ("Text field is empty");

                }
               else if(TextUtils.isEmpty (year_course)){
                    course_year.setError ("Text field is empty");

                }
               else if(TextUtils.isEmpty (program_course)){
                    autoCompleteTextView.setError ("Text field is empty");

                }

               else if(category.getCheckedRadioButtonId ()<=0){
                   RadioButton option=findViewById (R.id.option);
                   option.setError ("field required");

                }


               else{

                   String categories=radioButton.getText ().toString ().trim ();

                   long res=db.createCourse (name_course,code_course,credit_course,
                           semester_course,year_course,program_course,categories);

                   if(res>0){

                       Toast.makeText (CourseActivity.this, "new course is successfully registered"
                               , Toast.LENGTH_SHORT).show ();
                       startActivity (new Intent (getApplicationContext (),AdminActivity.class));

                   }

                   else{

                       Toast.makeText (CourseActivity.this, "error in inserting new course",
                               Toast.LENGTH_SHORT).show ();
                   }
                }


            }
        });


    }



}
