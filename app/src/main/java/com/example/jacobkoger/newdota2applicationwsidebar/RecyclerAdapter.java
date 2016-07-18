package com.example.jacobkoger.newdota2applicationwsidebar;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import com.example.jacobkoger.newdota2applicationwsidebar.POJO_Heroes.Hero;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_Heroes.HeroesList;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchHistory.Match;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends android.support.v7.widget.RecyclerView.Adapter<View_Holder> {

    private final static int FADE_DURATION = 250;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rows, parent, false);
        View expandedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expanded_row, parent, false);
        final View_Holder holder = new View_Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final View_Holder holder, int position) {
        final Match match = result.get(holder.getAdapterPosition());
        setFadeAnimation(holder.textView_LobbyType);
        setFadeAnimation(holder.textView_StartTime);
        setFadeAnimation(holder.textView_RadiantID);
        setFadeAnimation(holder.textView_DireID);
        setFadeAnimation(holder.textView_MatchNumber);
        setFadeAnimation(holder.matchID_View);
//        setFadeAnimation(holder.textView_Players);
        holder.textView_LobbyType.setText("Lobby Type:  " + match.getLobbyType());
        holder.textView_StartTime.setText("Start Time:  " + match.getStartTime());
        holder.textView_RadiantID.setText("Radiant ID: " + match.getRadiantTeamId());
        holder.textView_DireID.setText("Dire ID " + match.getDireTeamId());
//        holder.textView_Players.setText("Player Count: " + match.getPlayers());
        holder.textView_MatchNumber.setText("Match Number: " + match.getMatchSeqNum());

    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
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