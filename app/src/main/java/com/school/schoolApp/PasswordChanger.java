package com.school.schoolApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.school.datasource.DatabaseSource;

public class PasswordChanger extends Fragment {


   private EditText old_password,new_password,confirm_password;
    private DatabaseSource db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.activity_password_changer,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);

        TextView mUsername = getActivity ( ).findViewById (R.id.textView);
        final String username= mUsername.getText ().toString ().trim ();

        db=new DatabaseSource (getActivity ());

        old_password=getActivity ().findViewById (R.id.first);
        new_password=getActivity ().findViewById (R.id.new_password);
        confirm_password=getActivity ().findViewById (R.id.confirm_password);

        Toast.makeText (getActivity (), "welcome"+username, Toast.LENGTH_SHORT).show ( );

        Button save = getActivity ( ).findViewById (R.id.submit);

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

                   if(res){
                       startActivity (new Intent (getActivity ().getApplicationContext (),
                               StudentActivity.class));

                       Toast.makeText (getActivity (), "password changed successfully",
                               Toast.LENGTH_SHORT).show ();

                       getActivity ().finish ();

                   }

                   else{

                       Toast.makeText (getActivity (), "password not exist",
                               Toast.LENGTH_SHORT).show ( );
                   }

                }
            }
        });


    }
}
