package com.school.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.school.datasource.DatabaseSource;

public class PasswordChanger extends AppCompatActivity {

    EditText old_password,new_password,confirm_password;
    Button save;
    DatabaseSource db;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_password_changer);

        username=getIntent ().getExtras ().getString ("value");
        db=new DatabaseSource (this);

        old_password=findViewById (R.id.first);
        new_password=findViewById (R.id.new_password);
        confirm_password=findViewById (R.id.confirm_password);

        Toast.makeText (this, "welcome"+username, Toast.LENGTH_SHORT).show ( );

        save=findViewById (R.id.submit);

        save.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                String older_password=old_password.getText ().toString ().trim ();
                String newer_password=new_password.getText ().toString ().trim ();
                String confirm=confirm_password.getText ().toString ().trim ();


                if(TextUtils.isEmpty (older_password)){

                    old_password.setError ("filled is empty");
                }
                else if(newer_password.length ()<8){
                    new_password.setError ("password must at least 8 character");
                }
               else if (!newer_password.equals (confirm)){
                    confirm_password.setError ("password not match");
                }

               else{
                   boolean res=db.changePassword (username,older_password,newer_password);

                   if(res==true){
                       startActivity (new Intent (getApplicationContext (),StudentActivity.class));
                       Toast.makeText (PasswordChanger.this, "password changed successfully",
                               Toast.LENGTH_SHORT).show ();
                       finish ();

                   }

                   else{

                       Toast.makeText (PasswordChanger.this, "password not exist",
                               Toast.LENGTH_SHORT).show ( );
                   }

                }
            }
        });


    }
}
