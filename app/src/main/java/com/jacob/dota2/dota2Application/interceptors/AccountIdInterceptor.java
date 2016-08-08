package com.jacob.dota2.dota2Application.interceptors;

import android.content.Context;
import android.content.SharedPreferences;

import com.jacob.dota2.dota2Application.Config;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccountIdInterceptor implements Interceptor {

    private static final String QUERY_API_KEY = "key";
    private static final String QUERY_STEAM_ID = "account_id";
    public Context mContext;
    public String playerId;

    public AccountIdInterceptor(Context context) {
        mContext = context;
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("player_id", Context.MODE_PRIVATE);
        playerId = sharedPreferences.getString("player_id", null);
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        final Request req = chain.request();
        final HttpUrl url = req.url().newBuilder()
                .addQueryParameter(QUERY_API_KEY, Config.STEAM_API_KEY)
                .addQueryParameter(QUERY_STEAM_ID, playerId)
                .build();
        final Request authQuest = req.newBuilder()
                .url(url)
                .build();
        return chain.proceed(authQuest);
    }
}
