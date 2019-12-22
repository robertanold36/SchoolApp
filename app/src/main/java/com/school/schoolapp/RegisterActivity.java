package com.school.schoolapp;

//import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.ColorSpace;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.school.datasource.DatabaseSource;

public class RegisterActivity extends AppCompatActivity {
    EditText mEmail,mPassword,mName,mUsername;
    Button mRegister;
    TextView mLogin;
    DatabaseSource db;
    //Button change;
    //Color red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db=new DatabaseSource (this);

        mEmail=findViewById(R.id.email);
        mName=findViewById(R.id.name);
        mPassword=findViewById(R.id.password);
        mUsername=findViewById(R.id.username);

        mRegister=findViewById(R.id.register);
        mLogin=findViewById(R.id.login);


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        mRegister.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                String name=mName.getText().toString().trim();
                String email=mEmail.getText().toString().trim();
                String username=mUsername.getText().toString().trim();
                String password=mPassword.getText().toString().trim();

                if(TextUtils.isEmpty (name)){
                    mName.setError ("name field is required");
                }

                else if(TextUtils.isEmpty (email)){
                    mEmail.setError ("email field is required");
                }

                else if(TextUtils.isEmpty (username)){

                    mUsername.setError ("username field is required");
                }

                else if(TextUtils.isEmpty (password)){

                    mPassword.setError ("passoword field is required");
                }

                else if(password.length ()<8){

                    mPassword.setError ("password must at least 8 character");
                }

                else{

                    long val =db.createUser (name,email,username,password);
                    if(val>0){

                        Toast.makeText (RegisterActivity.this, "succedd registered ",
                                Toast.LENGTH_SHORT).show ( );
                        startActivity (new Intent (getApplicationContext (),MainActivity.class));
                    }

                    else
                    {
                        Toast.makeText (RegisterActivity.this, "fail to register" +
                                " the user ", Toast.LENGTH_SHORT).show ( );

                    }
                }

            }
        });
        /**

    public void colorChanger(View view){
        change=findViewById (R.id.regist);
        if(change.isClickable ()) {

            view.setBackgroundColor (Color.parseColor ("red"));
            getWindow ( ).getDecorView ( ).setBackgroundColor (Color.parseColor ("blue"));
        }

        else{

        }**/



    }
}
