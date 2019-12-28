package com.school.schoolapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;


import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.school.datasource.DatabaseSource;
import com.school.datasource.Student;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    DatabaseSource db;
    Student student;
    

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences ("user_details",MODE_PRIVATE);

        Toolbar toolbar=findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        Objects.requireNonNull (getSupportActionBar ( )).setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayShowTitleEnabled (true);

        DrawerLayout drawerLayout=findViewById (R.id.drawer_layout);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle (this,drawerLayout,toolbar
                ,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener (toggle);
        toggle.syncState ();

        NavigationView navigationView=findViewById (R.id.nav_view);






        db=new DatabaseSource (this);

        //program =findViewById (R.id.programm);
        //yer=findViewById (R.id.year);


        /*

        show=findViewById (R.id.username);


        String mUsername= sharedPreferences.getString ("username",null);
        show.setText ("WELCOME "+mUsername);

        student=new Student ();
        
        student= db.getDetails (mUsername);
        String programme= student.getProgramme ();

         program.setText (programme);

         */

    }


    public void logout(View view){

            SharedPreferences.Editor editor=sharedPreferences.edit ();
            editor.clear ();
            editor.apply ();
            startActivity (new Intent (getApplicationContext (),LoginActivity.class));
            finish ();
    }


}
