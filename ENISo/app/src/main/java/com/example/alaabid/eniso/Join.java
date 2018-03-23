package com.example.alaabid.eniso;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Join extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String[] branches= new String[]{"IA","GTE","EI","Meca","GP"};//existing braches
        final String[] groups= new String[]{"1.1","1.2","1.3","2.1","2.2","2.3","3.1","3.2","3.3"};//existing groups

        setContentView(R.layout.activity_join);




        EditText ed_name=(EditText)findViewById(R.id.name);
        EditText ed_surname=(EditText)findViewById(R.id.surname);
        EditText ed_cin=(EditText)findViewById(R.id.cin);
        EditText ed_mail=(EditText)findViewById(R.id.mail);

        final Spinner spinBranches = (Spinner) findViewById(R.id.branch);
        ArrayAdapter<String> adapterB = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, branches);
        spinBranches.setAdapter(adapterB);

        final Spinner spinGroups = (Spinner) findViewById(R.id.group);
        ArrayAdapter<String> adapterG = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groups);
        spinGroups.setAdapter(adapterG);

        Button b_signup=(Button)findViewById(R.id.signup);

        b_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"under construction sorry!!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
