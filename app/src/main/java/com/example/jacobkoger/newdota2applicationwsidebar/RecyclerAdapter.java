package com.example.jacobkoger.newdota2applicationwsidebar;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_Heroes.Hero;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_Heroes.HeroesList;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchHistory.Match;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchHistory.Player;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends android.support.v7.widget.RecyclerView.Adapter<View_Holder> {

    public final List<Match> result = new ArrayList<>();
    public final List<Hero> mHeroes = new ArrayList<>();

    private Context mContext;

    public RecyclerAdapter(Context context) {
        mContext = context;

        final Gson gson = new Gson();
        final AssetManager am = context.getAssets();

        InputStream in = null;
        try {
            in = am.open("Heroes.json");
            final HeroesList hl = gson.fromJson(new InputStreamReader(in), HeroesList.class);
            mHeroes.addAll(hl.getHeroes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void addData(List<Match> matchHistory) {
        result.clear();
        result.addAll(matchHistory);
        notifyDataSetChanged();
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rows, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(View_Holder holder, int i) {
        final Match match = result.get(holder.getAdapterPosition());
        int[] images = new int[]{R.drawable.bottle_arcane, R.drawable.bottle_bounty, R.drawable.bottle_haste,
                R.drawable.bottle_doubledamage, R.drawable.bottle_empty, R.drawable.bottle_invisibility,
                R.drawable.bottle_illusion};

        final Resources res = mContext.getResources();
        for (final Player player : match.getPlayers()) {
            for (final Hero hero : mHeroes) {
                if (player.getHeroId() == hero.getId()) {
                    final int heroId = res.getIdentifier(hero.getName(), "drawable", mContext.getPackageName());
                    Glide.with(mContext).load(heroId).into(holder.imageViewBottle);
                }
            }
        }

        holder.textView_LobbyType.setText("Lobby Type:  " + match.getLobbyType());
        holder.textView_StartTime.setText("Start Time:  " + match.getStartTime());
        holder.textView_RadiantID.setText("Radiant ID: " + match.getRadiantTeamId());
        holder.textView_DireID.setText("Dire ID " + match.getDireTeamId());
        holder.textView_Players.setText("Player Count: " + match.getPlayers());
        holder.textView_MatchNumber.setText("Match Number: " + match.getMatchSeqNum());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }


}