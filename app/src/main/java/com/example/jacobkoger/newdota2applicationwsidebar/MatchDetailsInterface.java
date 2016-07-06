package com.example.jacobkoger.newdota2applicationwsidebar;

import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails.MatchDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MatchDetailsInterface {

    @GET("/IDOTA2Match_570/GetMatchDetails/V001/")
    Call<MatchDetails> getMatchDetails(@Query("match_id") String matchID);
}