package com.jacob.jacobkoger.dota2Application.RecentMatches.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jacob.jacobkoger.dota2Application.MatchDetails.MatchDetailsFragment;
import com.example.jacobkoger.dota2Application.R;
import com.jacob.jacobkoger.dota2Application.RecentMatches.HeroIconContainer;
import com.jacob.jacobkoger.dota2Application.data.hero.Hero;
import com.jacob.jacobkoger.dota2Application.data.history.MHMatch;
import com.jacob.jacobkoger.dota2Application.data.history.MHPlayer;

import java.util.Objects;

import static com.jacob.jacobkoger.dota2Application.util.ListUtils.emptyIfNull;

class MatchHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView matchNum;
    private final TextView startTime;
    private final TextView lobbyType;
    private final TextView matchId;
    private final ImageView heroView;
    private final View mIconContainer;
    private final Context mContext;
    private final Resources mRes;
    private final String mPkgName;
    private final String playerid;
    private final SharedPreferences sharedPreferences;
    MatchHolder(View view) {
        super(view);
        mContext = view.getContext();
        mRes = view.getResources();
        mPkgName = mContext.getPackageName();
        itemView.setOnClickListener(this);
        heroView = (ImageView) itemView.findViewById(R.id.playerplayed);
        startTime = (TextView) itemView.findViewById(R.id.startTime);
        matchId = (TextView) itemView.findViewById(R.id.matchid);
        lobbyType = (TextView) itemView.findViewById(R.id.lobbyType);
        matchNum = (TextView) itemView.findViewById(R.id.matchSeqNum);
        mIconContainer = view.findViewById(R.id.adapter_match_history_icon_container);
        sharedPreferences = mContext.getSharedPreferences("player_id", Context.MODE_PRIVATE);
        playerid = sharedPreferences.getString("player_id", "none");
    }

    void bind(MHMatch match, Iterable<Hero> heroes) {
        lobbyType.setText("Lobby Type:  " + match.getLobbyType());
        matchId.setText("Match ID: " + match.getMatchid());
        startTime.setText("Start Time:  " + match.getStartTime());
        matchNum.setText("Match Number: " + match.getMatchSeqNum());

        setImages(match, heroes);
    }

    private void setImages(MHMatch match, Iterable<Hero> heroes) {
        for (int i = 0, len = emptyIfNull(match.getPlayers()).size(); i < len; i++) {
            final MHPlayer player = match.getPlayers().get(i);
            for (final Hero hero : heroes) {
                if (player.getHeroId() == hero.getId()) {
                    final int resId = mRes.getIdentifier(hero.getName(), "drawable", mPkgName);
                    ((HeroIconContainer) mIconContainer).bindIcons(i, resId);
                    if(Objects.equals(playerid,
                            String.valueOf(getNewAccountID(player.getAccountId())))){
                        heroView.setImageResource(resId);
                    }
                }
            }
        }
    }


    @Override
    public void onClick(View view) {
        Log.d("click", String.valueOf(getAdapterPosition()));
        String fullmatchtext = matchId.getText().toString();
        String matchid = fullmatchtext.replace("Match ID: ", "");
        Log.d("click", matchid);
        FragmentManager fm = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();
        MatchDetailsFragment matchDetailsFragment = MatchDetailsFragment.newInstance(matchid);
        fm.beginTransaction().replace(R.id.flContent, matchDetailsFragment).addToBackStack(null).commit();

    }

    private long getNewAccountID(long id) {
        long accountchange = 76561197960265728L;
        long newid = accountchange + id;
        return newid;
    }
}
