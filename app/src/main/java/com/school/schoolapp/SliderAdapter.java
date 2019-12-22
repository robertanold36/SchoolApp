package com.school.schoolapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;



    public SliderAdapter(Context context){
        this.context=context;
    }

    //array

    public int[] slide_image={

        R.drawable.diamond3,
        R.drawable.diamond2,
        R.drawable.diamond3
    };

    public String[] slide_heading={
      "LOVE",
        "DIAMOND",
        "GOLD"

    };

    @Override
    public int getCount() {
        return slide_image.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService (context.LAYOUT_INFLATER_SERVICE);

       View view=layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView= view.findViewById (R.id.slide_image);

        TextView slideTextView= view.findViewById (R.id.slide_heading);

        slideImageView.setImageResource (slide_image[position]);
        slideTextView.setText (slide_heading[position]);

        container.addView (view);

        return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView ((RelativeLayout) object);
    }
}
