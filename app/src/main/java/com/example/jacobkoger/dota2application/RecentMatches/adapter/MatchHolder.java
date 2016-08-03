package com.example.jacobkoger.dota2application.RecentMatches.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jacobkoger.dota2application.MatchDetails.MatchDetailsFragment;
import com.example.jacobkoger.dota2application.R;
import com.example.jacobkoger.dota2application.RecentMatches.HeroIconContainer;
import com.example.jacobkoger.dota2application.data.hero.Hero;
import com.example.jacobkoger.dota2application.data.history.MHMatch;
import com.example.jacobkoger.dota2application.data.history.MHPlayer;

import static com.example.jacobkoger.dota2application.util.ListUtils.emptyIfNull;

class MatchHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView matchNum;
    private final TextView startTime;
    private final TextView lobbyType;
    private final TextView radiantId;
    private final TextView direId;
    private final TextView matchId;

    private final View mIconContainer;


    private final Context mContext;
    private final Resources mRes;
    private final String mPkgName;


    MatchHolder(View view) {
        super(view);
        mContext = view.getContext();
        mRes = view.getResources();
        mPkgName = mContext.getPackageName();
        itemView.setOnClickListener(this);
        startTime = (TextView) itemView.findViewById(R.id.startTime);
        matchId = (TextView) itemView.findViewById(R.id.matchid);
        lobbyType = (TextView) itemView.findViewById(R.id.lobbyType);
        radiantId = (TextView) itemView.findViewById(R.id.radiantTeamId);
        direId = (TextView) itemView.findViewById(R.id.direTeamId);
        matchNum = (TextView) itemView.findViewById(R.id.matchSeqNum);
        mIconContainer = view.findViewById(R.id.adapter_match_history_icon_container);


    }

    void bind(MHMatch match, Iterable<Hero> heroes) {
        lobbyType.setText("Lobby Type:  " + match.getLobbyType());
        matchId.setText("Match ID: " + match.getMatchid());
        startTime.setText("Start Time:  " + match.getStartTime());
        radiantId.setText("Radiant ID: " + match.getRadiantTeamId());
        direId.setText("Dire ID " + match.getDireTeamId());
        matchNum.setText("MHMatch Number: " + match.getMatchSeqNum());
        setImages(match, heroes);
    }

    private void setImages(MHMatch match, Iterable<Hero> heroes) {
        for (int i = 0, len = emptyIfNull(match.getPlayers()).size(); i < len; i++) {
            final MHPlayer player = match.getPlayers().get(i);
            for (final Hero hero : heroes) {
                if (player.getHeroId() == hero.getId()) {
                    final int resId = mRes.getIdentifier(hero.getName(), "drawable", mPkgName);
                    ((HeroIconContainer) mIconContainer).bindIcons(i, resId);
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

}
