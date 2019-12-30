package com.school.schoolapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.school.datasource.Course;
import com.school.datasource.DatabaseSource;
import com.school.datasource.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ListCourse extends AppCompatActivity {


    private CourseDetails courseDetails;
    private List<Course> courses=new ArrayList<> ();


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_list_course);

        DatabaseSource db=new DatabaseSource (this);




        final String username;

        username= Objects.requireNonNull (getIntent ( ).getExtras ( )).getString ("value");

        Student student=db.getDetails (username);
        String programme= student.getProgramme ();

        Toast.makeText (this, "your are " + programme, Toast.LENGTH_SHORT).show ( );


        courses.addAll (db.getCourse (programme));
        RecyclerView recyclerView=findViewById (R.id.recycler_view);
        recyclerView.setLayoutManager (new LinearLayoutManager (this));
        courseDetails=new CourseDetails (this, courses);

        recyclerView.setAdapter (courseDetails);


    }
}
