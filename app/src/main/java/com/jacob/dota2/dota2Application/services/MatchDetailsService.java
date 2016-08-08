package com.jacob.dota2.dota2Application.services;

import com.jacob.dota2.dota2Application.data.detail.MDMatchDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MatchDetailsService {
    @GET("/IDOTA2Match_570/GetMatchDetails/V001/")
    Call<MDMatchDetails> getMatchDetails(@Query("match_id") String matchID);
}

