package com.school.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.school.datasource.DatabaseSource;

public class LoginActivity extends AppCompatActivity {

    Button login,Admin;
    TextView Register;
    EditText mUsername,mPassoword;
    DatabaseSource db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        login = findViewById (R.id.loginBtn);
        mUsername = findViewById (R.id.username);
        mPassoword = findViewById (R.id.password);
        Admin = findViewById (R.id.admin);
        db = new DatabaseSource (this);
        final SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences ("user_details", MODE_PRIVATE);


        if (sharedPreferences.contains ("username") && sharedPreferences.contains ("password"))
            if (sharedPreferences.getString ("username", null).equals ("admin")) {
                startActivity (new Intent (getApplicationContext ( ), AdminActivity.class));
            } else {
                startActivity (new Intent (getApplicationContext ( ), MainActivity.class));

            }


        login.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText ( ).toString ( ).trim ( );
                String password = mPassoword.getText ( ).toString ( ).trim ( );

                boolean res = db.checkUser (username, password);
                if (res) {
                    SharedPreferences.Editor editor = sharedPreferences.edit ( );
                    editor.putString ("username", username);
                    editor.putString ("password", password);
                    editor.apply ( );
                    Toast.makeText (LoginActivity.this, "succed login"
                            , Toast.LENGTH_SHORT).show ( );

                    startActivity (new Intent (getApplicationContext ( ), MainActivity.class));

                } else {

                    Toast.makeText (LoginActivity.this, "no user found"
                            , Toast.LENGTH_SHORT).show ( );

                }
            }
        });

        Admin.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText ( ).toString ( ).trim ( );
                String password = mPassoword.getText ( ).toString ( ).trim ( );


                if (username.equals ("admin") && password.equals ("4444333221")) {

                    SharedPreferences.Editor editor = sharedPreferences.edit ( );
                    editor.putString ("username", username);
                    editor.putString ("password", password);
                    editor.apply ( );
                    Toast.makeText (LoginActivity.this, "your now login as admin"
                            , Toast.LENGTH_SHORT).show ( );
                    startActivity (new Intent (getApplicationContext ( ), AdminActivity.class));
                } else {

                    Toast.makeText (LoginActivity.this, "fail to login"
                            , Toast.LENGTH_SHORT).show ( );
                }

            }
        });

    }
}
