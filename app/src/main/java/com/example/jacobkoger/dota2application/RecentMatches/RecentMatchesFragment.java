package com.example.jacobkoger.dota2application.RecentMatches;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jacobkoger.dota2application.CacheStrategy;
import com.example.jacobkoger.dota2application.R;
import com.example.jacobkoger.dota2application.RecentMatches.adapter.HeroSearchView;
import com.example.jacobkoger.dota2application.RecentMatches.adapter.MatchAdapter;
import com.example.jacobkoger.dota2application.clients.LoggedInClient;
import com.example.jacobkoger.dota2application.clients.NonLoggedInClient;
import com.example.jacobkoger.dota2application.data.history.MHMatch;

import java.util.List;

import static com.example.jacobkoger.dota2application.util.Utils.requireNonNull;


public class RecentMatchesFragment extends Fragment implements RecentMatchesContract.View {

    private MatchAdapter adapter;
    private RecentMatchesContract.Presenter presenter;
    private String filter;
    private SwipeRefreshLayout refresher;
    private HeroSearchView mSearchView;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        adapter = new MatchAdapter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refresher = (SwipeRefreshLayout) view;
        refresher.setOnRefreshListener(presenter);
        final RecyclerView recycler = (RecyclerView) view.findViewById(R.id.list);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);
        mSearchView = (HeroSearchView) menu.findItem(R.id.menu_search).getActionView();
        mSearchView.setOnQueryTextListener(presenter);
        mSearchView.setAutoCompleteAdapter(presenter);
    }

    @Override
    public void setPresenter(@NonNull RecentMatchesContract.Presenter mPresenter) {
        presenter = requireNonNull(mPresenter, "Presenter must not be null");

    }

    @Override
    public void fetchMatches(@NonNull CacheStrategy cacheStrategy) {
        requireNonNull(cacheStrategy, "CacheStrategy must not be null");
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("player_id", Context.MODE_PRIVATE);

            if (sharedPreferences.contains("player_id")) {
                Log.d("client", "loggedinclient");
                LoggedInClient.with(this).cacheStrategy(cacheStrategy).enqueueMatchHistory(presenter);
            } else {
                Log.d("client", "nonloggedinclient");
                NonLoggedInClient.with(this).cacheStrategy(cacheStrategy).enqueueMatchHistory(presenter);
            }

    }

    @Override
    public void bindMatches(@NonNull List<MHMatch> matches) {
        adapter.addAll(requireNonNull(matches, "match history must not be null"));
        refresher.setRefreshing(false);
        filterMatches(filter);
    }

    @Override
    public void filterMatches(@Nullable String query) {
        if (TextUtils.equals(filter, query)) {
            // Nothing to do, already filtered
            return;
        }
        filter = query;
        mSearchView.setQuery(filter, false);
        if (!TextUtils.isEmpty(filter)) {
            adapter.filterMatches(filter);
        }
    }

    @Override
    public void handleNetworkError(@Nullable Throwable t) {
        refresher.setRefreshing(false);
    }
}