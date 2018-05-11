package com.example.alaabid.eniso.SiteTools.presentation;

import android.app.Activity;

import com.example.alaabid.eniso.SiteTools.common.OnResult;



public class UIHelper {
    public static OnResult applyUI(final Activity a, final OnResult r){
        return new OnResult() {
            @Override
            public void onSuccess(final Object result) {
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        r.onSuccess(result);
                    }
                });
            }

            @Override
            public void onError(final Throwable error) {
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        r.onError(error);
                    }
                });
            }
        };
    }
}
