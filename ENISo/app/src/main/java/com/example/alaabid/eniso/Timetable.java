package com.example.alaabid.eniso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Timetable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("IA_1_1");
        spinnerArray.add("IA_1_2");
        spinnerArray.add("IA_1_3");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinner = findViewById(R.id.sp_timetable);
        spinner.setAdapter(adapter);
        final ImageView im_timetable = findViewById(R.id.im_timetable);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(getApplicationContext(),  parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                switch (parent.getItemAtPosition(position).toString()){
                    case "IA_1_1": im_timetable.setImageResource(R.drawable.ia_1_1);break;
                    case "IA_1_2": im_timetable.setImageResource(R.drawable.ia_1_2);break;
                    case "IA_1_3": im_timetable.setImageResource(R.drawable.ia_1_3);break; //ghalet..famech IA1-3
                }
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
    }
}
