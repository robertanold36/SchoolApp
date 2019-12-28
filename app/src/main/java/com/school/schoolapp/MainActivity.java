package com.school.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;


import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

import com.school.datasource.DatabaseSource;
import com.school.datasource.Student;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView show, program,yer;
    DatabaseSource db;
    Student student;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        program =findViewById (R.id.programme);
        yer=findViewById (R.id.year);

        sharedPreferences=getSharedPreferences ("user_details",MODE_PRIVATE);

        db=new DatabaseSource (this);

        show=findViewById (R.id.username);


        String mUsername= sharedPreferences.getString ("username",null);
        show.setText ("WELCOME "+mUsername);

        student=new Student ();
        
        student= db.getDetails (mUsername);
        String programme= student.getProgramme ();

         program.setText (programme);

    }

    public void logout(View view){

            SharedPreferences.Editor editor=sharedPreferences.edit ();
            editor.clear ();
            editor.apply ();
            startActivity (new Intent (getApplicationContext (),LoginActivity.class));
    }


}
