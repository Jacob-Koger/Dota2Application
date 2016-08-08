package com.jacob.dota2.dota2Application.services;

import com.jacob.dota2.dota2Application.data.history.MHMatchHistory;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecentMatchService {

    @GET("/IDOTA2Match_570/GetMatchHistory/V001/")
    Call<MHMatchHistory> getMatchHistory();

}
