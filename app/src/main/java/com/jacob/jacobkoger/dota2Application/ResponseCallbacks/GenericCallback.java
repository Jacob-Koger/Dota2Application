package com.jacob.jacobkoger.dota2Application.ResponseCallbacks;

import android.support.annotation.Nullable;


public interface GenericCallback<T> {
    void onFailure(@Nullable Throwable t);
    void onSuccess(T t);
}

