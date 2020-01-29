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


public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseHolder> {

    private Context mContext;
    private List<Course> courses;



    public CourseAdapter(Context context, List<Course> courses) {
           this.mContext=context;
           this.courses=courses;
    }

    public class CourseHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView code;

        public CourseHolder(@NonNull View itemView) {

            super (itemView);
            name=itemView.findViewById (R.id.course_nme);
            code=itemView.findViewById (R.id.course_cde);

        }
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from (mContext);
        View view=inflater.inflate (R.layout.course_list,parent,false);
        return new CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {

                   Course course=courses.get(position);

                   holder.name.setText (course.getCourse_name ());
                   holder.code.setText (course.getCourse_code ());

    }

    @Override
    public int getItemCount()
    {
        return courses.size ();
    }


}
