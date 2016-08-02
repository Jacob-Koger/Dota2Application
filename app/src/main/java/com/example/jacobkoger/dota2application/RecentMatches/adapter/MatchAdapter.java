package com.example.jacobkoger.dota2application.RecentMatches.adapter;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jacobkoger.dota2application.R;
import com.example.jacobkoger.dota2application.data.hero.Hero;
import com.example.jacobkoger.dota2application.data.hero.HeroesList;
import com.example.jacobkoger.dota2application.data.history.MHMatch;
import com.example.jacobkoger.dota2application.util.filter.HeroPredicate;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.jacobkoger.dota2application.util.ListUtils.emptyIfNull;
import static com.example.jacobkoger.dota2application.util.ListUtils.filter;

public class MatchAdapter extends RecyclerView.Adapter<MatchHolder> {

    private final List<Hero> mHeroes = new ArrayList<>(0);
    private final SortedList<MHMatch> mData;

    public MatchAdapter(Context context) {
        mData = new SortedList<>(MHMatch.class, new SortedMatchCallback(this));
        try (InputStream in = context.getAssets().open("Heroes.json")) {
            final HeroesList hl = new Gson().fromJson(new InputStreamReader(in), HeroesList.class);
            mHeroes.addAll(hl.getHeroes());
        } catch (IOException ignored) {
        }
    }

    @Override
    public MatchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rows, parent, false);
        return new MatchHolder(v);
    }

    @Override
    public void onBindViewHolder(MatchHolder holder, int position) {
        final MHMatch match = mData.get(holder.getAdapterPosition());
        holder.bind(match, mHeroes);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void filterMatches(String query) {
        final Collection<MHMatch> target = new ArrayList<>(getItemCount());
        for (int i = 0, len = getItemCount(); i < len; i++) {
            target.add(mData.get(i));
        }
        mData.clear();
        addAll(filter(target, new HeroPredicate(mHeroes, query)));
    }

    public void addAll(List<MHMatch> history) {
        mData.beginBatchedUpdates();
        for (final MHMatch match : emptyIfNull(history)) {
            if (10 == emptyIfNull(match.getPlayers()).size()) {
                if (!"8".equals(match.getLobbyType())) {
                    mData.add(match);
                }
            }
        }
        mData.endBatchedUpdates();
    }

    static final class SortedMatchCallback extends SortedListAdapterCallback<MHMatch> {

        SortedMatchCallback(MatchAdapter adapter) {
            super(adapter);
        }

        @Override
        public int compare(MHMatch o1, MHMatch o2) {
            return Long.valueOf(o2.getRawStartTime()).compareTo(o1.getRawStartTime());
        }

        @Override
        public boolean areContentsTheSame(MHMatch oldItem, MHMatch newItem) {
            return oldItem.getStartTime().equals(newItem.getStartTime());
        }

        @Override
        public boolean areItemsTheSame(MHMatch item1, MHMatch item2) {
            return TextUtils.equals(item1.getMatchSeqNum(), item2.getMatchSeqNum());
        }

    }

}
