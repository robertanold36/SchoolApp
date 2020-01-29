package com.school.schoolApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.school.datasource.DatabaseSource;
import com.school.datasource.Student;


public class PersonalDetailsFragment extends Fragment {

    private TextView first_nme, last_nme,mYear,mProgramme,mGender,mEmail,Title,mUsername;
    DatabaseSource db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.personal_details,container,false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);

        db=new DatabaseSource (getActivity ());

        first_nme =getActivity ().findViewById (R.id.first_name);
        last_nme =getActivity ().findViewById (R.id.last_name);
        mYear=getActivity ().findViewById (R.id.year);
        mProgramme=getActivity ().findViewById (R.id.programme);
        mGender=getActivity ().findViewById (R.id.gender);
        mEmail=getActivity ().findViewById (R.id.email);
        mUsername=getActivity ().findViewById (R.id.textView);
        Title=getActivity ().findViewById (R.id.title);


        String username=mUsername.getText ().toString ().trim ();
        Title.setText ("Welcome "+username);


        Student student;
        student=db.getStudentDetails (username);
        first_nme.setText (student.getFirst_name ());
        last_nme.setText (student.getLast_name ());
        mYear.setText (student.getYear ());
        mProgramme.setText (student.getProgramme ());
        mGender.setText (student.getGender ());
        mEmail.setText (student.getEmail ());


    }
}
