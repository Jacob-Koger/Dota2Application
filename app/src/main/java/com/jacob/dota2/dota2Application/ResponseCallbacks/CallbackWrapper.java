package com.jacob.dota2.dota2Application.ResponseCallbacks;

import android.support.annotation.NonNull;

import java.lang.ref.SoftReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jacob.dota2.dota2Application.util.Utils.requireNonNull;

public class CallbackWrapper<T> implements Callback<T> {

    private final SoftReference<GenericCallback<T>> mCallback;

    private CallbackWrapper(@NonNull GenericCallback<T> callback) {
        mCallback = requireNonNull(new SoftReference<>(callback), "GenericCallback must not be null");
    }

    public static <T> CallbackWrapper<T> wrap(@NonNull GenericCallback<T> dotaCallback) {
        return new CallbackWrapper<>(dotaCallback);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        final GenericCallback<T> callback = mCallback.get();
        final T t = response.body() != null ? response.body() : null;
        if (callback != null) {
            if (t != null) {
                callback.onSuccess(t);
            } else {
                callback.onFailure(null);
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        final GenericCallback<T> callback = mCallback.get();
        if (callback != null) {
            callback.onFailure(t);
        }
    }

}
