package com.example.alaabid.eniso;

import android.app.Activity;
import android.content.Intent;
import android.provider.Contacts;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alaabid.eniso.SiteTools.common.OnResult;
import com.example.alaabid.eniso.SiteTools.presentation.UIHelper;
import com.example.alaabid.eniso.SiteTools.service.MyService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONObject;


public class Login extends AppCompatActivity {
    Activity act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        act = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button b_more =(Button)findViewById(R.id.more);
        Button b_join =(Button)findViewById(R.id.join);
        Button b_login =(Button)findViewById(R.id.login);

        final EditText ed_username=(EditText)findViewById(R.id.username);
        final EditText ed_password=(EditText)findViewById(R.id.password);

        b_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LearnMore.class);
                startActivity(i);

            }
        });
        final Activity activity = this;
        b_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Join.class);
                startActivity(i);
            }
        });





        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryConnect(ed_username.getText().toString(), ed_password.getText().toString());
            }
        });
    }

    void tryConnect(String id, String pw){
        final MyService service=new MyService();
        try {
            service.login(id, pw, UIHelper.applyUI(act,new OnResult<JsonObject>() {
                @Override
                    public void onSuccess(JsonObject result) {
                    String res="x";
                    String resultString = result.toString();
                    try {
                        JSONObject jsonObject = new JSONObject(resultString);
                        org.json.JSONObject dollarUn = jsonObject.getJSONObject("$1");
                        res = dollarUn.get("userFullTitle").toString();
                        /*NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                        View headerView = navigationView.getHeaderView(0);
                        TextView navUsername = (TextView) headerView.findViewById(R.id.navUsrName);
                        navUsername.setText(res);*/

                    }
                    catch (Exception ex){
                        Log.i("ERRRRRR", ex.toString());
                    }


                    Log.i("dzdzzdzdzd",res);
                    Intent i = new Intent(getApplicationContext(),Home.class);
                    i.putExtra("nom", res);
                    startActivityForResult(i, 1);
                }

                @Override
                public void onError(Throwable error) {
                    Toast.makeText(getApplicationContext(), "Invalid login or password", Toast.LENGTH_LONG).show();
                }
            }));
        }catch(Exception ex){
            ex.printStackTrace();}
    }
}
