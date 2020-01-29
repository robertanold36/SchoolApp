package com.school.schoolApp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.school.datasource.DatabaseSource;

public class CourseFragment extends Fragment {

    EditText course_name,course_credit, course_semester,course_year;
    RadioGroup category;
    AutoCompleteTextView autoCompleteTextView,course_code;
    Button mRegister;
    DatabaseSource db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.activity_course, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);

        db = new DatabaseSource (getActivity ().getApplicationContext ());
        course_name=getActivity ().findViewById (R.id.cname);
        course_code=getActivity ().findViewById (R.id.cCode);
        course_credit=getActivity ().findViewById (R.id.cCredit);
        course_semester =getActivity ().findViewById (R.id.cSemister);
        course_year=getActivity ().findViewById (R.id.cYear);
        mRegister=getActivity ().findViewById (R.id.register);
        autoCompleteTextView=getActivity ().findViewById (R.id.programme);

        category=getActivity ().findViewById (R.id.category);

        final String[] program={"Bsc in computer science","Telecommunication",
                "Electronics","Engineering","law"};

        ArrayAdapter<String> adapter= new ArrayAdapter<>
                (getActivity ().getApplicationContext (),android.R.layout.select_dialog_item,program);

        autoCompleteTextView.setThreshold (1);
        autoCompleteTextView.setAdapter (adapter);

        final String[] code={"CS 150","CS 151","CS 152","CS 153",
                "CS 154","CS 155","CS 156","CS 157","CS 158","CS 159"};

        ArrayAdapter<String> course_adapter=new ArrayAdapter<> (getActivity ().getApplicationContext (),
                android.R.layout.select_dialog_item,code);

        course_code.setAdapter (course_adapter);
        course_code.setThreshold (1);




        mRegister.setOnClickListener (new View.OnClickListener ( ) {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                int selectedItem=category.getCheckedRadioButtonId ();
                RadioButton radioButton=getActivity ().findViewById (selectedItem);

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
                    RadioButton option=getActivity ().findViewById (R.id.option);
                    option.setError ("field required");

                }


                else{

                    String categories=radioButton.getText ().toString ().trim ();

                    long res=db.createCourse (name_course,code_course,credit_course,
                            semester_course,year_course,program_course,categories);

                    if(res>0){

                        Toast.makeText (getActivity ().getApplicationContext (),
                                "new course is successfully registered"
                                , Toast.LENGTH_SHORT).show ();
                        Intent intent=new Intent (getActivity ().getApplicationContext (),
                                AdminActivity.class);
                        getActivity ().finish ();
                        startActivity (intent);

                    }

                    else{

                        Toast.makeText (getActivity ().getApplicationContext (),
                                "error in inserting new course",
                                Toast.LENGTH_SHORT).show ();
                    }
                }


            }
        });
    }
}
