package com.school.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences=getSharedPreferences ("user_details",MODE_PRIVATE);




    }

    public void logout(View view){

            SharedPreferences.Editor editor=sharedPreferences.edit ();
            editor.clear ();
            editor.apply ();
            startActivity (new Intent (getApplicationContext (),LoginActivity.class));
    }


}
