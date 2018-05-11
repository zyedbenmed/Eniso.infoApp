package com.example.alaabid.eniso.SiteTools.service;

import android.util.Log;

import com.example.alaabid.eniso.SiteTools.common.OnResult;
import com.example.alaabid.eniso.SiteTools.dal.MyDataAccessObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Created by vpc on 3/10/18.
 */

public class MyService {
    MyDataAccessObject dao=new MyDataAccessObject();
    public void login(String login, String password, final OnResult<Object> onResult){
        dao.callVrServer("http://www.eniso.info/ws/core/login/" + login + "?password=" + password, new OnResult<JsonObject>() {
            @Override
            public void onSuccess(JsonObject result) {
                String res=result.toString();
                if(res.charAt(3)=='1')onResult.onSuccess(result);
                else onResult.onError(new Throwable());
            }

            @Override
            public void onError(Throwable error) {
                onResult.onError(error);
            }
        });
    }

    public void save(Object o,final OnResult<Boolean> onResult){
        String v = new Gson().toJson(o);
        String entityName=o.getClass().getSimpleName();
        dao.callVrServer("http://www.eniso.info/ws/core/ws?s=Return(bean('core')" +
                        ".save('"+entityName+"',"+v+"))"
                , new OnResult<JsonObject>() {
            @Override
            public void onSuccess(JsonObject result) {
                onResult.onSuccess(true);
            }

            @Override
            public void onError(Throwable error) {
                onResult.onSuccess(false);
            }
        });
    }

    public void logout(final OnResult<Boolean> onResult){
        dao.callVrServer("http://www.eniso.info/ws/core/logout", new OnResult<JsonObject>() {
            @Override
            public void onSuccess(JsonObject result) {
                onResult.onSuccess(true);
            }

            @Override
            public void onError(Throwable error) {
                onResult.onSuccess(false);
            }
        });
    }
}
