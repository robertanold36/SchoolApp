package com.school.schoolApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.school.datasource.Course;
import com.school.datasource.DatabaseSource;
import com.school.datasource.Student;

import java.util.ArrayList;
import java.util.List;


public class ListCourseFragment extends Fragment {

    private CourseAdapter courseAdapter;
    private List<Course> courses=new ArrayList<> ();
    private TextView mUsername;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate (R.layout.activity_list_course,container,false);

    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);

        DatabaseSource db=new DatabaseSource (getActivity ());

        mUsername=getActivity ().findViewById (R.id.textView);

        String username=mUsername.getText ().toString ().trim ();

        Student student=db.getStudentProgramme (username);
        String programme= student.getProgramme ();


        courses.addAll (db.getCourses (programme));
        RecyclerView recyclerView=getActivity ().findViewById (R.id.recycler_view);
        recyclerView.setLayoutManager (new LinearLayoutManager (getActivity ()));
        courseAdapter =new CourseAdapter (getActivity (), courses);

        recyclerView.setAdapter (courseAdapter);


    }
}
