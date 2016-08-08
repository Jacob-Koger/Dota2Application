package com.jacob.dota2.dota2Application.RecentMatches;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jacob.dota2.dota2Application.CacheStrategy;
import com.jacob.dota2.dota2Application.data.history.MHMatchHistory;
import com.jacob.dota2.dota2Application.data.history.MHResult;
import com.jacob.dota2.dota2Application.util.ListUtils;

import static android.text.TextUtils.isEmpty;
import static com.jacob.dota2.dota2Application.util.Utils.requireNonNull;

public class RecentMatchesPresenter extends ConnectivityPresenter {

    private final RecentMatchesContract.View mView;
    private CacheStrategy cacheStrategy = CacheStrategy.NONE;
    private boolean mIsConnected;
    private SharedPreferences sharedPreferences;
    private String accountID;

    public RecentMatchesPresenter(@NonNull Context context,
                                  @NonNull RecentMatchesContract.View view) {
        super(context);
        sharedPreferences = context.getSharedPreferences("player_id", Context.MODE_PRIVATE);
        mView = requireNonNull(view, "View cannot be Null");
        mView.setPresenter(this);
    }

    public void onStart() {
        super.onStart();
        accountID = sharedPreferences.getString("player_id", "none");
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
                mView.bindMatches(ListUtils.emptyIfNull(result.getMatches()));
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
