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

import com.school.datasource.DatabaseSource;
import com.school.datasource.Student;

import java.util.ArrayList;
import java.util.List;

public class AllStudentsFragment extends Fragment {

    DatabaseSource db;
    List<Student> students;
    AllStudentsAdapter allStudentsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.all_students,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);

        db=new DatabaseSource (getActivity ());
        students=new ArrayList<> ();

        students.addAll (db.viewAllStudents ());
        RecyclerView recyclerView=getActivity ().findViewById (R.id.recycler_view);
        recyclerView.setLayoutManager (new LinearLayoutManager (getActivity ()));
        allStudentsAdapter=new AllStudentsAdapter (getActivity (),students);
        recyclerView.setAdapter (allStudentsAdapter);

    }

}
