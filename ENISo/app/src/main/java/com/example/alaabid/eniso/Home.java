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
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    String nom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //***********************************************************
        //*******************CODE HERE*******************************
        //*******************inside of onCreate()********************
        String[] arr = {"Soutenances Projets Innovation / App PS (update 1)\n\nLes soutenances de projet Innovation sont planifiées ce Samedi à partir de 8h30 pour les IA1, GTE1 et IA2. \n" +
                "Le planning est joint\n\nécrit par Taha Ben Salah - (le 11 May 2018) ",
                "Quatre Offres de stages d'été au groupe STUNAS Industries sousse\n\n" +
                        "Mission: Participation à la mise en place  d'outil ERP (Enterprise Ressource Planning) pour  le pilotage de l’entreprise. \n" +
                        "\n" +
                        "Durée: La durée du stage est de deux mois avec possibilité de continuité en projet innovation rémunérée.\n" +
                        "\n" +
                        "http://www.stunasindustries.com \n" +
                        "\n" +
                        "\n" +
                        "Pour plus d'information contacter M. Imed Bennour, Service des stages.\n écrit par Imed Bennour - (le 10 May 2018) ",
                        "Réunion TCPC\n\n"+"Tous les élèves intéressés de faire partie de l'équipe d'irganisation du TCPC2018 sont priés de nous joindre auj lundi 07/05/2018 à 14H à l'amphi.\n\n"
                        + "\n\nécrit par Taha Ben Salah - (le 07 May 2018)",
                        "Matériel département Informatique\n\n"+"Merci de rendre tout le matériel du département informatique ( raspberry, roue moteurs, servo moteurs, cartes commande capteurs,.....) avant le jeudi 26/04/2018 ( pour Eniso IoT challenge du 28/04/2018 au 01/05/2018).\n\n" +
                                "Vous pouvez le récupérer à partir de mercredi 02/05/2018.\n\nécrit par Sameh Gassab - (le 21 Apr 2018) ",
                        "DS Compilation, Round 3\n\n"+"Le DS Compilation est programmé le Samedi  5 mai à 14H."+
                                "\n\nécrit par Mohamed Nazih Omri - (le 19 Apr 2018) "

        };
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, arr);
        ListView listView = (ListView) findViewById(R.id.articlesLV);
        listView.setAdapter(adapter);



        //**********************END CODE***************************
        //***********************************************************

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Bundle extras = getIntent().getExtras();
        nom = extras.getString("nom");
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


                final AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);

                builder.setTitle("Are You Sure To Log Out ?");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            Intent i = new Intent(Home.this,Home.class);
                            i.putExtra("nom", nom);
                            startActivityForResult(i, 1);


                        }
                        if(which == 1){

                            Intent i1= new Intent(Home.this,Login.class);
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
