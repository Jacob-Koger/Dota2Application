package com.jacob.jacobkoger.dota2Application.interceptors;

import com.jacob.jacobkoger.dota2Application.Config;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class KeyInteceptor implements Interceptor{
    private static final String QUERY_API_KEY = "key";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        final Request req = chain.request();
        final HttpUrl url = req.url().newBuilder()
                .addQueryParameter(QUERY_API_KEY, Config.STEAM_API_KEY)
                .build();
        final Request authQuest = req.newBuilder()
                .url(url)
                .build();
        return chain.proceed(authQuest);
    }
}
