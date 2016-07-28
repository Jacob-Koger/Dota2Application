package com.example.jacobkoger.newdota2applicationwsidebar;

import com.example.jacobkoger.newdota2applicationwsidebar.POJO_AccountInfo.AccountInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AccountDetailsInterface {
    @GET("/ISteamUser/GetPlayerSummaries/v0002/")
    Call<AccountInfo> getResponse();
}
