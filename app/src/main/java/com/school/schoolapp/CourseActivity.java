package com.school.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

public class CourseActivity extends AppCompatActivity {

    EditText course_name,course_code,course_credit,course_semister,course_year;
    RadioGroup category;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_course);

        course_name=findViewById (R.id.cname);
        course_code=findViewById (R.id.cCode);
        course_credit=findViewById (R.id.cCredit);
        course_semister=findViewById (R.id.cSemister);
        course_year=findViewById (R.id.cYear);

        category=findViewById (R.id.category);

        String[] program={"Bsc in computer science","Telecommunication",
                "Electronics","Engineering","law"};
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<> (this,android.R.layout.select_dialog_item,program);

        autoCompleteTextView.setThreshold (1);
        autoCompleteTextView.setAdapter (adapter);


    }
}
