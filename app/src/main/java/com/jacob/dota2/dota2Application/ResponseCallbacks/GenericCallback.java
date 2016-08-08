package com.jacob.dota2.dota2Application.ResponseCallbacks;

import android.support.annotation.Nullable;


public interface GenericCallback<T> {
    void onFailure(@Nullable Throwable t);
    void onSuccess(T t);
}

