package com.example.jacobkoger.newdota2applicationwsidebar;

import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails.MatchDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MatchDetailsInterface {
    String APIKey = BuildConfig.API_KEY;
    @GET("/IDOTA2Match_{ID}/GetMatchDetails/v1?key=" + APIKey)
    Call<MatchDetails> getMatchDetails(@Path("ID" )String matchID);
}
