package com.example.alaabid.eniso;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.alaabid.eniso.EventSchedulerPackage.EventAdapter;
import com.example.alaabid.eniso.EventSchedulerPackage.EventDialog;
import com.example.alaabid.eniso.EventSchedulerPackage.EventModel;
import com.example.alaabid.eniso.EventSchedulerPackage.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contacts extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;
    private ListView listView;
    String nom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        nom = extras.getString("nom");

        listView = findViewById(R.id.listV);

        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);




        //***********************************************************
        //*******************CODE HERE*******************************
        //*******************inside of onCreate()********************







        //**********************END CODE***************************
        //***********************************************************

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.navUsrName);
        navUsername.setText(nom);
        navigationView.setNavigationItemSelectedListener(this);
    }


    //***************************CODE HERE *************************************
    //************************outside of onCreate()*****************************
    //**************************************************************************

    int [] IMAGES ={R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar,R.drawable.default_avatar};
    String [] EMAIL ={"aref.meddeb@infcom.rnu.tn","gamaoun@gmail.com","belhadjtahar.jamel@gmail.com","asma.benrhouma@yahoo.fr","t.hassine@gnet.tn","mekki.hassen@gmail.com","walid.chainbi@gmail.com","bouraoui.mahmoud@fsm.rnu.tn","chokri.bouraoui@enim.rnu.tn","taha.bensalah@gmail.com","sameh.gassab@gmail.com","moezmr@hotmail.fr","Ben_Khalifa_Anouar@yahoo.fr","Amir.Mili@eniso.rnu.tn","Nizar.Somai@eniso.rnu.tn","sami.bennour@gmail.com","jellelisaida45@gmail.com","lemak2008@gmail.com","raoufscol@gmail.com","mahranfisc@gmail.com","abdaouinadia@gmail.com","fakhriberhouma@gmail.com","naouefelbenaziza@gmail.com","sofiene.guedri@yahou.fr","benahmednehla@gmail.com","amene.amine@gmail.com","farid.jmili@eniso.rnu.tn","service.mastere.eniso@gmail.com","souguir.atf@yahoo.fr","rym.rym@hotmail.com","sana.abid.moussa@gmail.com","wadal2007@yahoo.fr","anouar.tabbabi@eniso.rnu.tn"};
    String [] NAMES ={"Mr Aref Meddeb","Mr Fehmi Gamaoun","Mr Jamel Bel Hadj Tahar","Mme Asma Ben Rhouma","Mr Tarek Hassine","Mr Hassen Makki","Mr Walid Chainbi","Mr Mahmoud Bouraoui","Mr Chokri Bouraoui","Mr Taha Ben Salah","Mme Sameh Kassab","Mr Moez Neifer","Mr Anouar Ben Khalifa","Mr Med Amir Mili","Mr Nizar Somaî","Mr Sami Bannour","Mlle Saîda Jallali","Mr Kamel Omrane","Mr Raouf Hachem","Mr Mahran Khattat","Mlle Nadia Abdaoui","Mr Fakhri Ben Rhouma","Mr Naouefel Ben Aziza","Mr Soufiéne Guedri","Mme Nehla Ben Ahmed","Mr Noureddine Jbali","Mr Farid Jmili","Mme Imene Machalli","Mlle Atf Souguir","Mme Rim Ben Rached","Mme Sana Abid","Mr Walid Dallel","Mr Anouar Tabbabi"};

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.user_single_layout,null);

            TextView name = convertView.findViewById(R.id.user_single_name);
            final TextView mail = convertView.findViewById(R.id.user_single_email);
            CircleImageView image = convertView.findViewById(R.id.user_single_image);

            // convertView = getLayoutInflater().inflate(R.layout.users_single_layout,null);

            name.setText(NAMES[position]);
            mail.setText(EMAIL[position]);
            image.setImageResource(IMAGES[position]);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                    CharSequence options[] = new CharSequence[]{"Show Informations", "Send Email"};

                    final AlertDialog.Builder builder = new AlertDialog.Builder(Contacts.this);

                    builder.setTitle("Choose option");
                    builder.setItems(options, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String ch = EMAIL[position];


                            if(which == 1){
                                Intent i =new Intent(Intent.ACTION_SENDTO);
                                i.setType("text/plain");
                                i.setData(Uri.parse("mailto:" + ch));
                                //i.putExtra(Intent.EXTRA_SUBJECT,"Q");
                                //i.putExtra(Intent.EXTRA_TEXT,"tester le tp4 android");
                                startActivity(i);
                            }

                        }
                    });

                    builder.show();

                }
            });

            return convertView;

        }



    }

















    //******************************END CODE******************************************
    //*********************************************************************************


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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


                final AlertDialog.Builder builder = new AlertDialog.Builder(Contacts.this);

                builder.setTitle("Are You Sure To Log Out ?");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            Intent i = new Intent(Contacts.this,Home.class);
                            i.putExtra("nom", nom);
                            startActivityForResult(i, 1);
                        }
                        if(which == 1){

                            Intent i1= new Intent(Contacts.this,Login.class);
                            startActivity(i1);
                            finish();


                        }
                    }
                });

                builder.show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}