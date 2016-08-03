package com.example.jacobkoger.dota2application.RecentMatches;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.jacobkoger.dota2application.CacheStrategy;
import com.example.jacobkoger.dota2application.data.history.MHMatchHistory;
import com.example.jacobkoger.dota2application.data.history.MHResult;

import static android.text.TextUtils.isEmpty;
import static com.example.jacobkoger.dota2application.util.ListUtils.emptyIfNull;
import static com.example.jacobkoger.dota2application.util.Utils.requireNonNull;

public class RecentMatchesPresenter extends ConnectivityPresenter {

    private final RecentMatchesContract.View mView;
    private CacheStrategy cacheStrategy = CacheStrategy.NONE;
    private boolean mIsConnected;
    public RecentMatchesPresenter(@NonNull Context context,
                                  @NonNull RecentMatchesContract.View view) {
        super(context);
        mView = requireNonNull(view, "View cannot be Null");
        mView.setPresenter(this);
    }

    public void onStart() {
        super.onStart();
        mView.fetchMatches(cacheStrategy);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onConnectivityChanged(boolean isConnected) {
        mIsConnected = isConnected;
        cacheStrategy = mIsConnected ? CacheStrategy.SOFT : CacheStrategy.HARD;
    }

    @Override
    public void onSuccess(MHMatchHistory history) {
        if (history != null) {
            final MHResult result = history.getResult();
            if (result != null) {
                mView.bindMatches(emptyIfNull(result.getMatches()));
            }
        }
    }

    @Override
    public void onFailure(@Nullable Throwable t) {
        mView.handleNetworkError(t);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mView.filterMatches(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (isEmpty(newText)) {
            mView.filterMatches(null);
            mView.fetchMatches(cacheStrategy);
            return true;
        }
        return false;
    }
    @Override
    public void onRefresh() {
        mView.fetchMatches(mIsConnected ? CacheStrategy.NONE : CacheStrategy.HARD);
    }

    @Override
    public void onSuggestHero(@NonNull String heroName) {
        mView.filterMatches(heroName);
    }
}
