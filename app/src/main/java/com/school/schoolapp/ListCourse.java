package com.school.schoolapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.school.datasource.DatabaseSource;
import com.school.datasource.Student;

import java.util.ArrayList;
import java.util.Objects;


public class ListCourse extends AppCompatActivity {


    ListView listView;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_list_course);



        listView=findViewById (R.id.list_view);


        final String username;

        username= Objects.requireNonNull (getIntent ( ).getExtras ( )).getString ("value");

        DatabaseSource db=new DatabaseSource (this);
        Student student=db.getDetails (username);
        String programme= student.getProgramme ();

        Toast.makeText (this, "your are " + programme, Toast.LENGTH_SHORT).show ( );

        ArrayList<String> list=new ArrayList<>();
        ArrayList<String> list2=new ArrayList<>();


        Cursor cursor=db.getCourse (programme);
        if(cursor!=null){
            while(cursor.moveToNext ()) {
                 list.add (cursor.getString (1));
                 list.add (cursor.getString (2));
            }



            String[] list_course=new String[list.size ()];



            for(int i=0;i<list.size ();i++){

                list_course[i]=list.get (i);

            }



            ArrayAdapter<String> adapter=new ArrayAdapter<> (this,android.R.layout.simple_list_item_1,list_course);
            listView.setAdapter (adapter);


        }


        else{

            Toast.makeText (this, "no course selected to your programme", Toast.LENGTH_SHORT).show ();
        }



    }
}
