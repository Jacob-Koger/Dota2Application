package com.example.jacobkoger.dota2application.MatchDetails;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.jacobkoger.dota2application.data.detail.MDMatchDetails;

public class MatchDetailsPresenter implements MatchDetailsContract.Presenter {
    public final String matchID;
    private final MatchDetailsContract.View view;

    public MatchDetailsPresenter(@NonNull MatchDetailsContract.View mView, String matchId) {
        this.view = mView;
        this.matchID = matchId;
    }

    @Override
    public void onStart() {
        view.getResult();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onFailure(@Nullable Throwable t) {

    }

    @Override
    public void onSuccess(MDMatchDetails mdMatchDetails) {

    }


}
