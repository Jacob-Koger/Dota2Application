package com.jacob.jacobkoger.dota2Application.MatchDetails;


import android.support.annotation.NonNull;

import com.jacob.jacobkoger.dota2Application.ResponseCallbacks.GenericCallback;
import com.jacob.jacobkoger.dota2Application.data.detail.MDMatchDetails;

public interface MatchDetailsContract {
    interface View {
        void setPresenter(@NonNull Presenter presenter);
        void getResult();
    }
    interface Presenter extends GenericCallback<MDMatchDetails> {
        void onStart();
        void onStop();
    }
}
