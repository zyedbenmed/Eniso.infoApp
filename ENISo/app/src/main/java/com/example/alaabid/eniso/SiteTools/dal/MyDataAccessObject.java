package com.example.alaabid.eniso.SiteTools.dal;

import android.util.Log;

import com.example.alaabid.eniso.SiteTools.common.OnResult;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by vpc on 3/10/18.
 */

public class MyDataAccessObject {
    private static final String TAG = MyDataAccessObject.class.getSimpleName();
    private String session=null;
    public void forgetSession(){
        session=null;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public void callVrServer(final String url, final OnResult<JsonObject> result) {
        new Thread(){
            @Override
            public void run() {
                try{
                    String url2=url;
                    if(session!=null){
                        url2=url+"?JSESSIONID="+session;
                    }
                    Log.i(TAG, "open url " + url2);
                    ByteArrayOutputStream os=new ByteArrayOutputStream();
                    URL url3 = new URL(url2);
                    URLConnection cnx = url3.openConnection();
                    Log.i(TAG, "connexion open " + url2);
                    if(session==null) {
                        String k = cnx.getHeaderField("Set-Cookie");
                        session = k.substring(k.indexOf('=')+1,k.indexOf(';'));
                        Log.i(TAG, "got sessionid " + session);
                    }
                    InputStream inputStream = url3.openStream();
                    byte[] bytes=new byte[1024];
                    int count=0;
                    while((count=inputStream.read(bytes))>0){
                        os.write(bytes,0,count);
                    }
                    String stringResult = new String(os.toByteArray());
                    Log.i(TAG, "got result " + stringResult);
                    result.onSuccess(new Gson().fromJson(stringResult,JsonObject.class));
                }catch (Exception ex){
                    Log.i(TAG, "error " + ex);
                    result.onError(ex);
                }
            }
        }.start();
    }
}
