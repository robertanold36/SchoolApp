package com.school.schoolApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.school.datasource.Course;
import com.school.datasource.DatabaseSource;

import java.util.ArrayList;
import java.util.List;

public class AllCoursesFragment extends Fragment {

    AllCoursesAdapter allCoursesAdapter;
    DatabaseSource db;
    List<Course> courses;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate (R.layout.all_courses,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);

        db=new DatabaseSource (getActivity ());

        courses=new ArrayList<> ();
        courses.addAll (db.viewAllCourses ());
        RecyclerView recyclerView=getActivity ().findViewById (R.id.recycler_view);
        recyclerView.setLayoutManager (new LinearLayoutManager (getActivity ()));
        allCoursesAdapter=new AllCoursesAdapter (getActivity (),courses);
        recyclerView.setAdapter (allCoursesAdapter);

    }
}
