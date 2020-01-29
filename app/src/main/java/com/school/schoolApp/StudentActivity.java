package com.school.schoolApp;

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
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.school.datasource.DatabaseSource;


import java.util.Objects;

public class StudentActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    DatabaseSource db;
    TextView header_title;


    //method which finish the activity when press back
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            finish ();
        }

        return super.onKeyDown (keyCode,event);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        db=new DatabaseSource (this);


        sharedPreferences=getSharedPreferences ("user_details",MODE_PRIVATE);

        final String mUsername= sharedPreferences.getString ("username",null);



        header_title=findViewById (R.id.textView);
        header_title.setText (mUsername);

        header_title=findViewById (R.id.header_info);

        Toolbar toolbar=findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        Objects.requireNonNull (getSupportActionBar ( )).setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setTitle ("School App");

        final DrawerLayout drawerLayout=findViewById (R.id.drawer_layout);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle (this,drawerLayout,toolbar
                ,R.string.navigation_drawer_open,R.string.navigation_drawer_close);


        drawerLayout.addDrawerListener (toggle);
        toggle.syncState ();

        final NavigationView navigationView=findViewById (R.id.nav_view);

        if(savedInstanceState==null){

            getSupportFragmentManager ().beginTransaction ().replace
                    (R.id.fragment_container,new HomeStudentFragment ()).commit ();

            navigationView.setCheckedItem (R.id.item1);
        }

        navigationView.setNavigationItemSelectedListener (new
                          NavigationView.OnNavigationItemSelectedListener ( ) {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId ()){
                    case R.id.item1:
                        getSupportFragmentManager ().beginTransaction ().replace
                                (R.id.fragment_container,new HomeStudentFragment ()).commit ();
                        break;

                    case R.id.item2:
                        getSupportFragmentManager ().beginTransaction ().replace
                                (R.id.fragment_container,new ListCourseFragment ()).commit ();

                        break;

                    case R.id.item3:
                          getSupportFragmentManager ().beginTransaction ().replace
                               (R.id.fragment_container,new PersonalDetailsFragment ()).commit ();
                        break;

                    case R.id.item4:
                        getSupportFragmentManager ().beginTransaction ().replace
                                (R.id.fragment_container,new PasswordChanger ()).commit ();
                        break;

                    case R.id.item5:
                           logout (null);  //call logout method
                        break;
                }


                final DrawerLayout drawerLayout= findViewById (R.id.drawer_layout);

                Handler handler=new Handler ();
                handler.postDelayed (new Runnable ( ) {
                    @Override
                    public void run() {
                        drawerLayout.closeDrawer (GravityCompat.START);
                    }
                },50);

                return true;
            }
        });


    }

    public void logout(View view){

            SharedPreferences.Editor editor=sharedPreferences.edit ();
            editor.clear ();
            editor.apply ();
            startActivity (new Intent (getApplicationContext (),LoginActivity.class));
            finish ();
    }


}
