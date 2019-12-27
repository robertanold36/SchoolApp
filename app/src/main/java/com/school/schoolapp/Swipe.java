package com.school.schoolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Swipe extends AppCompatActivity {

     ViewPager viewPager;
     LinearLayout linearLayout;

     TextView[] mDots;

    SliderAdapter sliderAdapter;

    Button btnFirst,btnLast;
    private int mCurrentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_swipe);

        viewPager=findViewById (R.id.viewPager);
        linearLayout=findViewById (R.id.dotsLayout);

        btnFirst=findViewById (R.id.btnBack);
        btnLast=findViewById (R.id.btnNext);

        sliderAdapter=new SliderAdapter (this);
        viewPager.setAdapter (sliderAdapter);
        addDotsValue (0);

        viewPager.addOnPageChangeListener (viewListener);

        btnLast.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem (mCurrentPage+1);
            }
        });

        btnFirst.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem (mCurrentPage-1);
            }
        });



    }


    public void addDotsValue(int position){

        mDots=new TextView[3];
        linearLayout.removeAllViews ();

        for(int i=0;i<mDots.length;i++){

            mDots[i]=new TextView(this);
            mDots[i].setText (Html.fromHtml ("&#8226;"));
            mDots[i].setTextColor(getResources ().getColor (R.color.TransparentWhite));
            mDots[i].setTextSize (35);

            linearLayout.addView(mDots[i]);
        }

        if(mDots.length>0){
            mDots[position].setTextColor (getResources ().getColor (R.color.colorAccent));

        }
    }

    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener () {
        @Override
        public void onPageScrolled(int i, float v, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
               addDotsValue (i);
               mCurrentPage=i;

               if(i==0){
                   btnFirst.setEnabled (false);
                   btnLast.setEnabled (true);
                   btnFirst.setVisibility (View.INVISIBLE);

                   btnLast.setText ("next");
                   btnFirst.setText ("");

               }

               else if(i==mDots.length-1){

                   btnFirst.setEnabled (true);
                   btnLast.setEnabled (false);
                   btnFirst.setVisibility (View.VISIBLE);


                   btnLast.setText ("finish");
                   btnFirst.setText ("back");

               }

               else{

                   btnFirst.setEnabled (true);
                   btnLast.setEnabled (true);
                   btnFirst.setVisibility (View.VISIBLE);

                   btnLast.setText ("next");
                   btnFirst.setText ("back");

               }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

}
