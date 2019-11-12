package com.example.alaabid.eniso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LearnMore extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);


        final Button b_back =(Button)findViewById(R.id.back);

        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(),Login.class);
                startActivity(j);
            }
        });
    }
    //******************************************************
    //******************************************************
    // slide.xml
    /* <?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    android:id="@+id/slidelinearlayout">

    <ImageView
        android:id="@+id/slideimg"
        android:layout_width="200dp"
        android:layout_height="200dp" />
    <TextView
        android:id="@+id/texttitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="text here"
        android:textSize="30sp"
        android:textStyle="bold"
        android:layout_marginTop="40dp"/>
    <TextView
        android:id="@+id/textdesc"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="40dp"
        android:textSize="17sp"
        android:text="description  here!"
        android:textAlignment="center"/>
</LinearLayout> */

    //****************************************************************
    //****************************************************************
    //slide Adpter.java

    /*public class SlideAdapter  extends PagerAdapter{


    Context context;
    LayoutInflater inflater;

    //list of images
    public int[] list_images={
            ////////// to be completed
            R.drawable.publication,
            R.drawable.document,
            R.drawable.document,
            R.drawable.document,
            R.drawable.document

    };

    //list of title
    public String[] liste_title={
            "Publications",
            "Documents",
            "Timetable",
            "Abscence",
            "Profil"
    };

    //list of description
    public String[] liste_desc={
            "desc1",
            "desc1",
            "desc1",
            "desc1",
            "desc1"
    };

    //liste_backgroundColor
    public int[] liste_BackgroundColor={
            Color.rgb(50,50,50),
            Color.rgb(50,50,50),
            Color.rgb(50,50,50),
            Color.rgb(50,50,50),
            Color.rgb(50,50,50)

    };


    public SlideAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return liste_title.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.slide,container,false);

        LinearLayout LayoutSilde=(LinearLayout) view.findViewById(R.id.slidelinearlayout);
        ImageView ImageSlide=(ImageView) view.findViewById(R.id.slideimg);
        final Home home = new Home();
        ImageSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //home.doIntent(position);
                    Log.i("dddd","ddddd");





}
        });
                TextView TexttTitle=(TextView)view.findViewById(R.id.texttitle);
                TextView TexttDesc=(TextView)view.findViewById(R.id.textdesc);

                LayoutSilde.setBackgroundColor(liste_BackgroundColor[position]);
                ImageSlide.setImageResource(list_images[position]);
                TexttTitle.setText(liste_title[position]);
                TexttDesc.setText(liste_desc[position]);

                container.addView(view);

                return view;
                }

@Override
public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
        }



        }*/
}
