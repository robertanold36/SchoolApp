package com.school.schoolApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.school.datasource.Course;

import java.util.List;

public class AllCoursesAdapter extends RecyclerView.Adapter<AllCoursesAdapter.AllCoursesHolder> {
    private Context context;
    private List<Course> courses;

    public AllCoursesAdapter(Context context,List<Course> courses){
        this.context=context;
        this.courses=courses;

    }


    public class AllCoursesHolder extends RecyclerView.ViewHolder{

        public TextView name_course;
        public TextView code_course;
        public TextView category_course;


        public AllCoursesHolder(@NonNull View itemView) {
            super (itemView);

            name_course=itemView.findViewById (R.id.name);
            code_course=itemView.findViewById (R.id.code);
            category_course=itemView.findViewById (R.id.category);


        }
    }

    @NonNull
    @Override
    public AllCoursesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from (context);
        View view=inflater.inflate (R.layout.all_courses_adapter,parent,false);
        return new AllCoursesHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCoursesHolder holder, int position) {

        Course course=courses.get (position);
        holder.name_course.setText (course.getCourse_name ());
        holder.code_course.setText (course.getCourse_code ());
        holder.category_course.setText (course.getCourse_category ());





    }

    @Override
    public int getItemCount() {
        return courses.size ();
    }


}
