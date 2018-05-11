package com.example.alaabid.eniso;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.ImageView;
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

public class Event extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,EventDialog.EventDialogListener {
    RecyclerView recyclerView;
    EventAdapter adapter;

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;

    List<EventModel> eventList;
    boolean doEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        //************************************************
        //*******************Code HERE********************
        //************************************************

        eventList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*addNewEvent();
        addNewEvent();
        addNewEvent();
        addNewEvent();
        addNewEvent();
        addNewEvent();
        addNewEvent();
        addNewEvent();
        addNewEvent();*/
        ImageView bt_addEvent = findViewById(R.id.bt_add);

        bt_addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        loadEvents();

    }


    //***************************CODE HERE *************************************
    //************************outside of onCreate()*****************************
    //**************************************************************************

    private void loadEvents() {
        String readUrl = "http://alaabid.cf/read_from_event.php";
        StringRequest stringRequestRead = new StringRequest(Request.Method.POST, readUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("data retrieved", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String date = jsonObject.getString("date"), hour = jsonObject.getString("hour"), title = jsonObject.getString("title"), descr = jsonObject.getString("descr"), author = jsonObject.getString("author");
                        EventModel eventModel = new EventModel(id, title, hour, date, descr, author);
                        eventList.add(eventModel);
                    }

                    adapter = new EventAdapter(getApplicationContext(), eventList);
                    recyclerView.setAdapter(adapter);

                    adapter.setOnRVItemClickListener(new EventAdapter.OnRVItemClickListener() {
                        @Override
                        public void onRVItemClick(int position) {
                            //clicking a card
                        }

                        @Override
                        public void onDeleteClick(int position) {
                            deleteEvent(eventList.get(position).getId());
                            eventList.remove(position);
                            adapter.notifyItemRemoved(position);
                        }

                        @Override
                        public void onEditClick(int position) {
                            openDialog();
                            posEventToEdit = position;
                            doEdit = true;
                            //eventList.remove(position)
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error retrieving data.", Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequestRead);

    }

    private void addNewEvent(final String title, final String desc) {
        EventModel addedEvent = new EventModel(eventList.get(eventList.size() - 1).getId() + 1,
                title, "1:05:03", "1995-03-01", desc, "ala");
        eventList.add(addedEvent);
        adapter.notifyItemInserted(eventList.size() - 1);
        String writeUrl = "http://alaabid.cf/write_into_event.php";
        StringRequest stringRequestWrite = new StringRequest(Request.Method.POST, writeUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error sending data.", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("date", "'1995-03-01'");
                params.put("hour", "'1:05:03'");
                params.put("title", "'" + title + "'");
                params.put("descr", "'" + desc + "'");
                params.put("author", "'ala'");
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequestWrite);

    }

    private void deleteEvent(int id) {
        final int myId = id;
        String deleteUrl = "http://alaabid.cf/delete_event.php";
        StringRequest stringRequestDelete = new StringRequest(Request.Method.POST, deleteUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Deleted successfully.", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error sending delete request.", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", Integer.toString(myId));
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequestDelete);

    }

    private void editEvent(final int posEventToEdit, String argtitle, String argdesc) {
        final String title, desc;
        if (argtitle.equals("")) title = eventList.get(posEventToEdit).getTitle();
        else title = argtitle;
        if (argdesc.equals("")) desc = eventList.get(posEventToEdit).getDescr();
        else desc = argdesc;
        eventList.get(posEventToEdit).setTitle(title);
        eventList.get(posEventToEdit).setDescr(desc);
        adapter.notifyItemChanged(posEventToEdit);
        String editUrl = "http://alaabid.cf/edit_event.php";
        StringRequest stringRequestEdit = new StringRequest(Request.Method.POST, editUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error editing event.", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("date", "'1995-03-01'");
                params.put("hour", "'1:05:03'");
                params.put("title", "'" + title + "'");
                params.put("descr", "'" + desc + "'");
                params.put("author", "'ala'");
                params.put("id", Integer.toString(eventList.get(posEventToEdit).getId()));
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequestEdit);
        doEdit = false;
    }

    private void openDialog() {
        EventDialog eventDialog = new EventDialog();
        eventDialog.show(getSupportFragmentManager(), "Event Dialog");
    }

    //String title, desc;
    int posEventToEdit;

    @Override
    public void  applyTexts(String title, String desc) {
        if(doEdit) editEvent(posEventToEdit, title, desc);
        else addNewEvent(title, desc);
    }




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
                startActivity(h);
                break;
            case R.id.nav_Documents:
                Intent i= new Intent(getApplicationContext(),Documents.class);
                startActivity(i);
                break;
            case R.id.nav_contact:
                Intent g= new Intent(getApplicationContext(),Contacts.class);
                startActivity(g);
                break;
            case R.id.nav_Timetable:
                Intent s= new Intent(getApplicationContext(),Emploi.class);
                startActivity(s);
                break;
            case R.id.nav_Profil:
                Intent t= new Intent(getApplicationContext(),Profil.class);
                startActivity(t);
                break;
            case R.id.nav_scheduler:
                Intent x= new Intent(getApplicationContext(),Event.class);
                startActivity(x);
                break;
            case R.id.logout:
                CharSequence options[] = new CharSequence[]{"Stay", "log out"};


                final AlertDialog.Builder builder = new AlertDialog.Builder(Event.this);

                builder.setTitle("Choose option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            Intent i = new Intent(Event.this,Home.class);

                            startActivity(i);
                        }
                        if(which == 1){

                            Intent i1= new Intent(Event.this,Login.class);
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
