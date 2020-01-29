package com.school.schoolApp;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Build;
import android.os.Bundle;


import android.os.Handler;
import android.view.KeyEvent;
import android.view.MenuItem;

import android.view.View;


import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.school.datasource.DatabaseSource;

import java.util.Objects;


public class AdminActivity extends AppCompatActivity  {

    DrawerLayout drawerLayout;
    DatabaseSource db;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_admin);


        db=new DatabaseSource (this);


        //get the loginValues stored by the sharedPreference
        sharedPreferences=getSharedPreferences ("user_details",MODE_PRIVATE);

        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull (getSupportActionBar ()).setDisplayHomeAsUpEnabled (true);

        getSupportActionBar().setDisplayShowTitleEnabled (true);

         drawerLayout=findViewById(R.id.drawer_layout);



        ActionBarDrawerToggle toggle;
        toggle = new ActionBarDrawerToggle (this,drawerLayout, toolbar
                ,R.string.navigation_drawer_open,R.string.navigation_drawer_close);


        drawerLayout.addDrawerListener (toggle);
        toggle.syncState ();


        NavigationView navigationView=findViewById (R.id.nav_view);

        if(savedInstanceState==null){

            getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container,new
                    HomeAdminFragment ()).commit ();
            navigationView.setCheckedItem (R.id.home);

        }


        navigationView.setNavigationItemSelectedListener
                (new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId ( );


                switch(id){
                    case(R.id.home):
                        getSupportFragmentManager ().beginTransaction ().replace
                                (R.id.fragment_container,new HomeAdminFragment ()).commit ();
                        break;


                    case(R.id.item1):
                        getSupportFragmentManager ().beginTransaction ().replace
                                (R.id.fragment_container,new RegisterFragment ()).commit ();
                     break;

                    case(R.id.item2):
                        getSupportFragmentManager ().beginTransaction ().replace
                                (R.id.fragment_container,new CourseFragment ()).commit ();
                        break;

                    case(R.id.item3):
                        getSupportFragmentManager ().beginTransaction ().replace
                                (R.id.fragment_container,new LectureFragment ()).commit ();
                        break;


                    case(R.id.item4):
                     getSupportFragmentManager ().beginTransaction ().replace
                             (R.id.fragment_container,new AllStudentsFragment ()).commit ();
                        break;


                    case(R.id.item5):
                       getSupportFragmentManager ().beginTransaction ().replace
                               (R.id.fragment_container,new AllCoursesFragment ()).commit ();

                        break;

                    case(R.id.logout):
                        logoutAdmin (null);
                        break;
                }

                final DrawerLayout drawerLayout= findViewById (R.id.drawer_layout);


                new Handler ().postDelayed (new Runnable ( ) {
                    @Override
                    public void run() {
                        drawerLayout.closeDrawer (GravityCompat.START);

                    }
                },50);

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
        finish ();

    }


    SharedPreferences sharedPreferences;

    //method which finish the activity when press back


}
