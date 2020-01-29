package com.school.schoolApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.school.datasource.Student;

import java.util.List;

public class AllStudentsAdapter extends RecyclerView.Adapter<AllStudentsAdapter.AllStudentsHolder> {

    private Context context;
    private List<Student> students;

    public AllStudentsAdapter(Context context,List<Student> students){
        this.context=context;
        this.students=students;
    }

    public class AllStudentsHolder extends RecyclerView.ViewHolder{

        public TextView username;
        public TextView programme;
        public TextView year;

        public AllStudentsHolder(@NonNull View itemView) {
            super (itemView);
            username =itemView.findViewById (R.id.mUsername);
            programme=itemView.findViewById (R.id.mProgramme);
            year=itemView.findViewById (R.id.mYear);

        }
    }


    @NonNull
    @Override
    public AllStudentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from (context);
        View view=inflater.inflate (R.layout.all_students_adapter,parent,false);
        return new AllStudentsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllStudentsHolder holder, int position) {

        Student student=students.get (position);
        holder.username.setText (student.getUsername ());
        holder.programme.setText (student.getProgramme ());
        holder.year.setText (student.getYear ());

    }

    @Override
    public int getItemCount()
    {
        return students.size ();
    }
}
