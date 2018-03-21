package com.example.alaabid.eniso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Join extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        EditText ed_name=(EditText)findViewById(R.id.name);
        EditText ed_surname=(EditText)findViewById(R.id.surname);
        EditText ed_cin=(EditText)findViewById(R.id.cin);
        EditText ed_branch=(EditText)findViewById(R.id.branch);
        EditText ed_group=(EditText)findViewById(R.id.group);
        EditText ed_mail=(EditText)findViewById(R.id.mail);

        Button b_signup=(Button)findViewById(R.id.signup);

        b_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"under construction sorry!!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
