package com.example.jacobkoger.dota2application.MatchDetails;


import android.support.annotation.NonNull;

import com.example.jacobkoger.dota2application.ResponseCallbacks.GenericCallback;
import com.example.jacobkoger.dota2application.data.detail.MDMatchDetails;

public interface MatchDetailsContract {
    interface View {
        void setPresenter(@NonNull Presenter presenter);
        void getResult();
    }
    interface Presenter extends GenericCallback<MDMatchDetails>{
        void onStart();
        void onStop();
    }
}
