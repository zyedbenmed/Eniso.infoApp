package com.example.alaabid.eniso.SiteTools.common;

/**
 * Created by vpc on 3/10/18.
 */

public interface OnResult<T> {
    void onSuccess(T result);
    void onError(Throwable error);
}
