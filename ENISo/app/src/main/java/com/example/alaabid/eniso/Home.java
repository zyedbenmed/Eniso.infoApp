package com.example.alaabid.eniso;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Home extends AppCompatActivity {



  /*  private ViewPager viewPager;
    private SlideAdapter myAdapter;

    public void doIntent(int n){
        Intent i = new Intent(Home.this, Timetable.class);
        startActivity(i);
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button b_timetable=(Button)findViewById(R.id.b_home);
        b_timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),Timetable.class);
                startActivity(i);
            }
        });

    }



        /*
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        myAdapter = new SlideAdapter(this);
        ImageView ImageSlide= findViewById(R.id.slideimg);
            ImageSlide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //if(position==0){

                        Log.i("m6r6i66h63","0000000");

                    //}
                    //else if(position==1){

                        Log.i("m6r6i66h63","111111111");

                    //}


                }
            });

        viewPager.setAdapter(myAdapter);

        //final ImageButton imageButton =(ImageButton) viewPager.findViewById(R.id.slideimg);





        }




        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list_images[position]==R.drawable.publication){
                    Intent i = new Intent(getApplicationContext(),Timetable.class);
                    startActivity(i);
                }
            }
        });

        //Button bt_timetable = findViewById(R.id.bt_timetable);


        bt_timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Timetable.class);
                startActivity(i);
            }
        });*/
    }

