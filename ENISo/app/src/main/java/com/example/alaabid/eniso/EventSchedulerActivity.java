package com.example.alaabid.eniso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import com.example.alaabid.eniso.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventSchedulerActivity extends AppCompatActivity implements EventDialog.EventDialogListener {

    RecyclerView recyclerView;
    EventAdapter adapter;

    List<EventModel> eventList;
    boolean doEdit=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);

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

private void loadEvents(){
        String readUrl = "http://alaabid.cf/read_from_event.php";
        StringRequest stringRequestRead = new StringRequest(Request.Method.POST, readUrl, new Response.Listener<String>() {
@Override
public void onResponse(String response) {
        Log.d("data retrieved", response);
        try {
        JSONArray jsonArray = new JSONArray(response);
        for (int i =0; i<jsonArray.length();i++){
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        int id = jsonObject.getInt("id");
        String date = jsonObject.getString("date"), hour = jsonObject.getString("hour")
        , title = jsonObject.getString("title"), descr = jsonObject.getString("descr")
        , author = jsonObject.getString("author");
        EventModel eventModel = new EventModel(id, title,hour,date,descr,author);
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
        doEdit=true;
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

private void addNewEvent(final String title, final String desc){
        EventModel addedEvent = new EventModel(eventList.get(eventList.size()-1).getId()+1,
        title,"1:05:03","1995-03-01", desc, "ala" );
        eventList.add(addedEvent);
        adapter.notifyItemInserted(eventList.size()-1);
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
        }){
@Override
protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();
        params.put("date","'1995-03-01'");
        params.put("hour","'1:05:03'");
        params.put("title","'"+title+"'");
        params.put("descr","'"+desc+"'");
        params.put("author","'ala'");
        return params;
        }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequestWrite);

        }

private void deleteEvent(int id){
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
        }){
@Override
protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();
        params.put("id",Integer.toString(myId));
        return params;
        }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequestDelete);

        }

private void editEvent(final int posEventToEdit, String argtitle, String argdesc){
final String title,desc;
        if(argtitle.equals(""))title=eventList.get(posEventToEdit).getTitle();
        else title=argtitle;
        if(argdesc.equals("")) desc=eventList.get(posEventToEdit).getDescr();
        else desc=argdesc;
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
        }){
@Override
protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();
        params.put("date","'1995-03-01'");
        params.put("hour","'1:05:03'");
        params.put("title","'"+title+"'");
        params.put("descr","'"+desc+"'");
        params.put("author","'ala'");
        params.put("id",Integer.toString(eventList.get(posEventToEdit).getId()));
        return params;
        }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequestEdit);
        doEdit = false;
        }

private void openDialog(){
        EventDialog eventDialog = new EventDialog();
        eventDialog.show(getSupportFragmentManager(),"Event Dialog");
        }

        //String title, desc;
        int posEventToEdit;
@Override
public void  applyTexts(String title, String desc) {
        if(doEdit) editEvent(posEventToEdit, title, desc);
        else addNewEvent(title, desc);
        }
        }
