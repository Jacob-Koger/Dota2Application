package com.example.jacobkoger.newdota2applicationwsidebar;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_Heroes.Hero;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_Heroes.HeroesList;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_Lobbies.LobbiesList;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_Lobbies.Lobby;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchHistory.MHMatch;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchHistory.MHPlayer;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecyclerAdapter extends android.support.v7.widget.RecyclerView.Adapter<View_Holder> {

    private final static int FADE_DURATION = 250;
    public static final List<MHMatch> result = new ArrayList<>();
    public final List<Hero> mHeroes = new ArrayList<>();
    public final List<Lobby> mLobby = new ArrayList<>();
    private Context mContext;
    private Integer heroID;
    private int playerslot;

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
        try {
            in = am.open("Lobbies.json");
            final LobbiesList ll = gson.fromJson(new InputStreamReader(in), LobbiesList.class);
            mLobby.addAll(ll.getLobbies());
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

    void addData(List<MHMatch> MHMatchHistory) {
        for (MHMatch count : MHMatchHistory)
            if (10 == (count.getMHPlayers().size())) {
                final List<MHMatch> history = new ArrayList<>();
                for (MHMatch match : MHMatchHistory) {
                    if (!"8".equals(match.getLobbyType())) {
                        history.add(match);
                    }
                }
                result.clear();
                result.addAll(history);
                notifyDataSetChanged();
            }
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rows, parent, false);
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(final View_Holder holder, int position) {
        final MHMatch MHMatch = result.get(holder.getAdapterPosition());

        setFadeAnimation(holder.textView_LobbyType);
        setFadeAnimation(holder.textView_StartTime);
        setFadeAnimation(holder.textView_RadiantID);
        setFadeAnimation(holder.textView_DireID);
        setFadeAnimation(holder.textView_MatchNumber);
        setFadeAnimation(holder.matchID_View);
        for (final Lobby lobby : mLobby) {
            if(Objects.equals(lobby.getId().toString(), MHMatch.getLobbyType()))
            holder.textView_LobbyType.setText("Lobby Type: " + lobby.getName());
        }
        holder.textView_StartTime.setText("Start Time:  " + MHMatch.getStartTime());
        holder.textView_RadiantID.setText("Radiant ID: " + MHMatch.getRadiantTeamId());
        holder.textView_DireID.setText("Dire ID " + MHMatch.getDireTeamId());
        holder.textView_MatchNumber.setText("Match Number: " + MHMatch.getMatchSeqNum());
        holder.matchID_View.setText("Match ID: " + MHMatch.getMatchId());

        setImages(holder);
    }

    private void setImages(final View_Holder holder) {
        final Resources res = mContext.getResources();

        final MHMatch MHMatch = result.get(holder.getAdapterPosition());
        for (final MHPlayer mhPlayer : MHMatch.getMHPlayers()) {
            for (final Hero hero : mHeroes) {
                if (mhPlayer.getHeroId() == hero.getId()) {
                    final String heroName = hero.getName();
                    final int heroId = res.getIdentifier(heroName, "drawable", mContext.getPackageName());
                    final ImageView iv = holder.getSlotView((int) mhPlayer.getPlayerSlot());
                    Glide.with(mContext).load(heroId).fitCenter().crossFade().into(iv);
                }
            }
        }
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