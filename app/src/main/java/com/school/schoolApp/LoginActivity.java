package com.school.schoolApp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.widget.Toast;

import com.school.datasource.DatabaseSource;
import com.school.datasource.User;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText mUsername,mPassoword;
    DatabaseSource db;
    User user;
    String roles;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        login = findViewById (R.id.loginBtn);
        mUsername = findViewById (R.id.username);
        mPassoword = findViewById (R.id.password);

        db = new DatabaseSource (this);

        final SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences ("user_details", MODE_PRIVATE);


        if (sharedPreferences.contains ("username") && sharedPreferences.contains ("password"))
            if (Objects.equals (sharedPreferences.getString ("username", null),
                    "admin")) {
                startActivity (new Intent (getApplicationContext ( ), AdminActivity.class));
                finish ( );
            }
           else if (Objects.equals (sharedPreferences.getString ("roles", null),
                    "student")) {
                startActivity (new Intent (getApplicationContext ( ), StudentActivity.class));
                finish ( );

            } else if(Objects.equals (sharedPreferences.getString ("roles", null),
                    "staff")){
                startActivity (new Intent (getApplicationContext ( ), LectureActivity.class));
                finish ();

            }



        login.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText ().toString ().trim ();
                String password = mPassoword.getText ().toString ().trim ();


                if (username.equals ("admin") && password.equals ("4444333221")) {

                    SharedPreferences.Editor editor = sharedPreferences.edit ( );
                    editor.putString ("username", username);
                    editor.putString ("password", password);
                    editor.apply ();
                    Toast.makeText (LoginActivity.this, "your now login as admin"
                            , Toast.LENGTH_SHORT).show ();
                    startActivity (new Intent (getApplicationContext (), AdminActivity.class));

                    finish ();

                } else {

                  boolean res=db.checkUser (username,password);
                  if(res){

                      user=db.getRoles (username);
                      roles=user.getRole ();

                      if (roles.equals ("student")){
                          SharedPreferences.Editor editor = sharedPreferences.edit ( );
                          editor.putString ("username", username);
                          editor.putString ("password", password);
                          editor.putString ("roles",roles);

                          editor.apply ();
                          Toast.makeText (LoginActivity.this, "your now login"
                                  , Toast.LENGTH_SHORT).show ();
                     startActivity (new Intent (getApplicationContext (), StudentActivity.class));

                          finish ();


                      }

                      else if(roles.equals ("staff")){
                          SharedPreferences.Editor editor = sharedPreferences.edit ( );
                          editor.putString ("username", username);
                          editor.putString ("password", password);
                          editor.putString ("roles",roles);
                          editor.apply ();
                          Toast.makeText (LoginActivity.this, "your now login"
                                  , Toast.LENGTH_SHORT).show ( );
                          startActivity (new Intent (getApplicationContext (),
                                  LectureActivity.class));


                          finish ();

                      }

                      else{

                          Toast.makeText (LoginActivity.this, "you don't have any roles to login"
                                  , Toast.LENGTH_SHORT).show ( );
                      }
                  }

                  else {
                      Toast.makeText (LoginActivity.this, "username or password not exists"
                              , Toast.LENGTH_SHORT).show ( );
                  }

                }
            }
        });

    }

}
