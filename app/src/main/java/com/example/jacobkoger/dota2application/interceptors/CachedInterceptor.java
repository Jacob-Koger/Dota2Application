package com.example.jacobkoger.dota2application.interceptors;

import com.example.jacobkoger.dota2application.CacheStrategy;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/** An {@link Interceptor} used to request new data or load from our response cache */
public class CachedInterceptor implements Interceptor {

    // //////////////////////////////////////////////////////////////////////////
    // Cache headers
    // /////////////////////////////////////////////////////////////////////////
    private static final String HEADER_PRAGMA = "Pragma";
    private static final String HEADER_CACHE_CONTROL = "Cache-Control";

    /** Cache-Control header strategy */
    private CacheStrategy mCacheStrategy = CacheStrategy.NONE;

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String cacheStrategy = mCacheStrategy.value();
        final Request orig = chain.request();
        final Response response = chain.proceed(orig);
        return response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .addHeader(HEADER_PRAGMA, cacheStrategy)
                .addHeader(HEADER_CACHE_CONTROL, cacheStrategy)
                .build();
    }

    /** Used to update our {@link CacheStrategy} */
    public void setCacheStrategy(CacheStrategy cacheStrategy) {
        mCacheStrategy = cacheStrategy;
    }

}
