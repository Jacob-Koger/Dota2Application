package com.example.jacobkoger.newdota2applicationwsidebar;


import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchHistory.MHMatchHistory;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Interface {

    @GET("/IDOTA2Match_570/GetMatchHistory/V001/")
    Call<MHMatchHistory> getMatchHistory();

}