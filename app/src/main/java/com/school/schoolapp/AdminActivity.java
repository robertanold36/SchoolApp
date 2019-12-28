package com.school.schoolapp;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Build;
import android.os.Bundle;

import android.view.MenuItem;

import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;


public class AdminActivity extends AppCompatActivity  {

    SharedPreferences sharedPreferences;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_admin);

        //get the loginValues stored by the sharedPreference
        sharedPreferences=getSharedPreferences ("user_details",MODE_PRIVATE);

        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull (getSupportActionBar ( )).setDisplayHomeAsUpEnabled (true);

        getSupportActionBar().setDisplayShowTitleEnabled (true);

        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);



        ActionBarDrawerToggle toggle;
        toggle = new ActionBarDrawerToggle (this,drawerLayout, toolbar
                ,R.string.navigation_drawer_open,R.string.navigation_drawer_close);


        drawerLayout.addDrawerListener (toggle);
        toggle.syncState ();



        NavigationView navigationView=findViewById (R.id.nav_view);
        navigationView.setNavigationItemSelectedListener
                (new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId ( );


                switch(id){
                    case(R.id.item1):
                     startActivity (new Intent (getApplicationContext (),RegisterActivity.class));
                     break;

                    case(R.id.item2):
                     startActivity (new Intent (getApplicationContext (),CourseActivity.class));
                        break;

                    case(R.id.item3):
                        startActivity (new Intent (getApplicationContext (),LectureActivity.class));
                        break;


                    case(R.id.item4):
                        Toast.makeText (AdminActivity.this, "click", Toast.LENGTH_SHORT)
                                .show ();
                        break;


                    case(R.id.item5):
                        Toast.makeText (AdminActivity.this, "clicked", Toast.LENGTH_SHORT)
                                .show ();

                        break;

                    case(R.id.logout):
                        logoutAdmin (null);
                        break;
                }

                DrawerLayout drawerLayout1=findViewById (R.id.drawer_layout);
                drawerLayout1.closeDrawer (GravityCompat.START);
                return true;

            }
        });


    }

    //method to clear sharedPreference key value when user logout

    public void logoutAdmin(View view){
        SharedPreferences.Editor editor=sharedPreferences.edit ();
        editor.clear ();
        editor.apply ();
        startActivity (new Intent (getApplicationContext (),LoginActivity.class));

    }



}
