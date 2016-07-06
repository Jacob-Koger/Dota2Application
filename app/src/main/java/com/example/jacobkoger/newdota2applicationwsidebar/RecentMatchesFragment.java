package com.example.jacobkoger.newdota2applicationwsidebar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchHistory.MatchHistory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RecentMatchesFragment extends android.support.v4.app.Fragment {
    String url = "https://api.steampowered.com";
    private RecyclerView recyclerView;
    private ArrayList<MatchHistory> data;
    private RecyclerAdapter adapter=new RecyclerAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycler_view, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        initViews();
        return recyclerView;
    }

    public void initViews(){
        Context context = getContext();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        getResult();
    }

    public void getResult() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                final Request orig = chain.request();
                HttpUrl origUrl = orig.url();
                return chain.proceed(orig.newBuilder()
                        .url(origUrl.newBuilder().addQueryParameter("key",BuildConfig.API_KEY)
                                .build())
                        .build());
            }
        });
        Retrofit retrofit = new Retrofit.Builder().client(builder.build())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API_Interface service = retrofit.create(API_Interface.class);
        Call<MatchHistory> callMH = service.getMatchHistory();
        callMH.enqueue(new Callback<MatchHistory>() {
            @Override
            public void onResponse(Call<MatchHistory> call, Response<MatchHistory> response) {
                MatchHistory matchHistory = response.body();

                data = new ArrayList<>(Collections.singletonList(matchHistory));
                adapter.addData(matchHistory.getResult().getMatches());
            }

            @Override
            public void onFailure(Call<MatchHistory> call, Throwable t) {
            }
        });
    }
}
