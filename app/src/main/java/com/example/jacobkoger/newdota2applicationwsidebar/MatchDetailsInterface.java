package com.example.jacobkoger.newdota2applicationwsidebar;

import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails.MDMatchDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MatchDetailsInterface {

    @GET("/IDOTA2Match_570/GetMatchDetails/V001/")
    Call<MDMatchDetails> getMatchDetails(@Query("match_id") String matchID);

    Call<MDMatchDetails> getMatchDetails(@Query("steamids") List<String> ids);

}