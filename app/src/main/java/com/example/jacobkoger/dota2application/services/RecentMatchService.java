package com.example.jacobkoger.dota2application.services;

import com.example.jacobkoger.dota2application.data.history.MHMatchHistory;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecentMatchService {

    @GET("/IDOTA2Match_570/GetMatchHistory/V001/")
    Call<MHMatchHistory> getMatchHistory();

}
