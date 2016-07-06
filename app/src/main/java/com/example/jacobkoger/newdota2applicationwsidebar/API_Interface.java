package com.example.jacobkoger.newdota2applicationwsidebar;



import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchHistory.MatchHistory;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Interface {
    String APIKey = BuildConfig.API_KEY;

    @GET("/IDOTA2Match_570/GetMatchHistory/V001/?key=" + APIKey)
    Call<MatchHistory> getMatchHistory();

}


