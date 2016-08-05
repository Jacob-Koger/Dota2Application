package com.jacob.jacobkoger.dota2Application.RecentMatches;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;

import com.jacob.jacobkoger.dota2Application.CacheStrategy;
import com.jacob.jacobkoger.dota2Application.RecentMatches.adapter.HeroSearchView;
import com.jacob.jacobkoger.dota2Application.ResponseCallbacks.GenericCallback;
import com.jacob.jacobkoger.dota2Application.data.history.MHMatch;
import com.jacob.jacobkoger.dota2Application.data.history.MHMatchHistory;

import java.util.List;


public interface RecentMatchesContract {

    interface View {

        void setPresenter(@NonNull Presenter mPresenter);
        void fetchMatches(@NonNull CacheStrategy cacheStrategy);
        void filterMatches(@Nullable String query);
        void bindMatches(@NonNull List<MHMatch> matches);
        void handleNetworkError(@Nullable Throwable t);

    }

    interface Presenter extends
            SwipeRefreshLayout.OnRefreshListener,
            SearchView.OnQueryTextListener,
            GenericCallback<MHMatchHistory>,
            HeroSearchView.OnHeroSuggestionListener{

        void onStart();
        void onStop();
        void onConnectivityChanged(boolean isConnected);

    }

}
