package com.example.jacobkoger.dota2application.ResponseCallbacks;

import android.support.annotation.Nullable;


public interface GenericCallback<T> {
    void onFailure(@Nullable Throwable t);
    void onSuccess(T t);
}

