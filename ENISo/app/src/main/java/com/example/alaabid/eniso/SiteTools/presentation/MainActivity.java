/*package com.example.alaabid.eniso.SiteTools.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import  com.example.alaabid.eniso.R;
import com.example.alaabid.eniso.SiteTools.common.OnResult;
import com.example.alaabid.eniso.SiteTools.presentation.UIHelper;
import com.example.alaabid.eniso.SiteTools.service.MyService;
import com.google.gson.JsonObject;

public class MainActivity extends AppCompatActivity {
    MyService service=new MyService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView viewById = findViewById(R.id.myTextView);
        try {
            service.login("toto", "toto1243", UIHelper.applyUI(this,new OnResult<Object>() {
                @Override
                public void onSuccess(Object result) {
                    viewById.setText(result.toString());
                }

                @Override
                public void onError(Throwable error) {
                    viewById.setText(error.toString());
                }
            }));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}*/
