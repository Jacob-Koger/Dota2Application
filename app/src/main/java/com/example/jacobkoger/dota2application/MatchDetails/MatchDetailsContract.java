package com.example.jacobkoger.dota2application.MatchDetails;


import android.support.annotation.NonNull;

import com.example.jacobkoger.dota2application.ResponseCallbacks.GenericCallback;
import com.example.jacobkoger.dota2application.data.detail.MDMatchDetails;

import java.util.List;

public interface MatchDetailsContract {
    interface View {
        void setPresenter(@NonNull Presenter presenter);
        void fetchMatchDetails(String matchId);
        void bindMatches(List<MDMatchDetails> MatchDetails);
    }
    interface Presenter extends GenericCallback<MDMatchDetails>{
        void onStart();
        void onStop();
    }
}
