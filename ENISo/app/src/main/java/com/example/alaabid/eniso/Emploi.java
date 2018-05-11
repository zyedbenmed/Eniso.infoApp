package com.example.alaabid.eniso;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Emploi extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String nom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emploi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        nom = extras.getString("nom");
         //*******************************************************
        //********************* CODE HERE**************************
        //*********************inside of onCreate()****************


        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("IA_1_1");
        spinnerArray.add("IA_1_2");
        spinnerArray.add("IA_2_1");
        spinnerArray.add("IA_2_2");
        spinnerArray.add("IA_2_3");
        spinnerArray.add("GTE_1_1");
        spinnerArray.add("EI_1_1");
        spinnerArray.add("EI_1_2");
        spinnerArray.add("EI_1_3");
        spinnerArray.add("EI_2_1");
        spinnerArray.add("EI_2_2");
        spinnerArray.add("EI_2_3");
        spinnerArray.add("Meca_1_1");
        spinnerArray.add("Meca_1_2");
        spinnerArray.add("Meca_2_1");
        spinnerArray.add("Meca_2_2");
        spinnerArray.add("Meca_2_3");
        spinnerArray.add("GTE_1_1");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, spinnerArray);
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
                    case "IA_2_1": im_timetable.setImageResource(R.drawable.ia_2_1);break;
                    case "IA_2_2": im_timetable.setImageResource(R.drawable.ia_2_2);break;
                    case "IA_2_3": im_timetable.setImageResource(R.drawable.ia_2_3);break;
                    case "GTE_1_1": im_timetable.setImageResource(R.drawable.gte_1_1);break;
                    case "EI_1_1": im_timetable.setImageResource(R.drawable.ei_1_1);break;
                    case "EI_1_2": im_timetable.setImageResource(R.drawable.ei_1_2);break;
                    case "EI_1_3": im_timetable.setImageResource(R.drawable.ei_1_3);break;
                    case "EI_2_1": im_timetable.setImageResource(R.drawable.ei_2_1);break;
                    case "EI_2_2": im_timetable.setImageResource(R.drawable.ei_2_2);break;
                    case "EI_2_3": im_timetable.setImageResource(R.drawable.ei_2_3);break;
                    case "Meca_1_1": im_timetable.setImageResource(R.drawable.meca_1_1);break;
                    case "Meca_1_2": im_timetable.setImageResource(R.drawable.meca_1_2);break;
                    case "Meca_2_1": im_timetable.setImageResource(R.drawable.meca_2_1);break;
                    case "Meca_2_2": im_timetable.setImageResource(R.drawable.meca_2_2);break;
                    case "Meca_2_3": im_timetable.setImageResource(R.drawable.meca_2_3);break;
                    case "GP_1_1": im_timetable.setImageResource(R.drawable.gp_1_1);break;
                }
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });


        //*****************END CODE**********************
        //*************************************************

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.navUsrName);
        navUsername.setText(nom);
        navigationView.setNavigationItemSelectedListener(this);
    }


    //***************************CODE HERE *************************************
    //************************outside of onCreate()*****************************
    //**************************************************************************


















    //******************************END CODE******************************************
    //*********************************************************************************




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){

            case R.id.nav_home:
                Intent h= new Intent(getApplicationContext(),Home.class);
                h.putExtra("nom", nom);
                startActivityForResult(h, 1);
                break;
            case R.id.nav_Documents:
                Intent i= new Intent(getApplicationContext(),Documents.class);
                i.putExtra("nom", nom);
                startActivityForResult(i, 1);
                break;
            case R.id.nav_contact:
                Intent g= new Intent(getApplicationContext(),Contacts.class);
                g.putExtra("nom", nom);
                startActivityForResult(g, 1);
                break;
            case R.id.nav_Timetable:
                Intent s= new Intent(getApplicationContext(),Emploi.class);
                s.putExtra("nom", nom);
                startActivityForResult(s, 1);
                break;
            case R.id.nav_Profil:
                Intent t= new Intent(getApplicationContext(),Profil.class);
                t.putExtra("nom", nom);
                startActivityForResult(t, 1);
                break;
            case R.id.nav_scheduler:
                Intent x= new Intent(getApplicationContext(),Event.class);
                x.putExtra("nom", nom);
                startActivityForResult(x, 1);
                break;
            case R.id.logout:
                CharSequence options[] = new CharSequence[]{"Stay", "log out"};


                final AlertDialog.Builder builder = new AlertDialog.Builder(Emploi.this);

                builder.setTitle("Choose option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            Intent i = new Intent(Emploi.this,Home.class);
                            i.putExtra("nom", nom);
                            startActivityForResult(i, 1);
                        }
                        if(which == 1){
                            Intent i1= new Intent(Emploi.this,Login.class);
                            startActivity(i1);

                        }
                    }
                });

                builder.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
