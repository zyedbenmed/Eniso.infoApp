package com.example.alaabid.eniso;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.InputStream;


public class Documents extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String nom;
    private Button mbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        nom = extras.getString("nom");

        mbtn = findViewById(R.id.button);

        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CharSequence options[] = new CharSequence[]{"DS Android 2013", "EXAMEN Android 2014", "DS Reseau 2007", "cours OS IA2"};


                final AlertDialog.Builder builder = new AlertDialog.Builder(Documents.this);

                builder.setTitle("choose file");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){


                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://jeanferdysusini.free.fr/Cours/CP48/NFA023-Examen-2012.pdf")));

                        }
                        if(which == 1){

                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://virtuelcampus.univ-msila.dz/fmi/wp-content/uploads/2017/01/corriger-examen-P-Mobile-2016-2017.pdf")));
                        }
                        if(which == 2){
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gipsa-lab.grenoble-inp.fr/~christian.bulfone/MIASHS-DCISS/PDF/EXAM_janvier-2012.pdf")));
                        }
                        if(which == 3){
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.iutbayonne.univ-pau.fr/~dalmau/documents/cours/infdus/Guide%20RTOS.pdf")));
                        }
                    }
                });

                builder.show();
            }
        });


        //********************************
        //*******************Code HERE********************
        //****************inside onCreate()****************







        //*********************END CODE*********************
        //****************************************************

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

            case R.id.nav_scheduler:
                Intent x= new Intent(getApplicationContext(),Event.class);
                x.putExtra("nom", nom);
                startActivityForResult(x, 1);
                break;
            case R.id.logout:
                CharSequence options[] = new CharSequence[]{"Stay", "log out"};


                final AlertDialog.Builder builder = new AlertDialog.Builder(Documents.this);

                builder.setTitle("Are You Sure To Log Out ?");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            Intent i = new Intent(Documents.this,Home.class);
                            i.putExtra("nom", nom);
                            startActivityForResult(i, 1);
                        }
                        if(which == 1){

                            Intent i1= new Intent(Documents.this,Login.class);
                            startActivity(i1);
                            finish();
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
