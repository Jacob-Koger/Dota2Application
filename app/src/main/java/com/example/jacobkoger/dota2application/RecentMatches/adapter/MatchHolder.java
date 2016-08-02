package com.example.jacobkoger.dota2application.RecentMatches.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jacobkoger.dota2application.MatchDetails.MatchDetailsFragment;
import com.example.jacobkoger.dota2application.R;
import com.example.jacobkoger.dota2application.data.hero.Hero;
import com.example.jacobkoger.dota2application.data.history.MHMatch;
import com.example.jacobkoger.dota2application.data.history.MHPlayer;
import com.squareup.picasso.Picasso;

import static com.example.jacobkoger.dota2application.util.ListUtils.emptyIfNull;

class MatchHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView matchNum;
    private final TextView startTime;
    private final TextView lobbyType;
    private final TextView radiantId;
    private final TextView direId;
    private final TextView matchId;

    private final ImageView imageView_r0;
    private final ImageView imageView_r1;
    private final ImageView imageView_r2;
    private final ImageView imageView_r3;
    private final ImageView imageView_r4;

    private final ImageView imageView_d128;
    private final ImageView imageView_d129;
    private final ImageView imageView_d130;
    private final ImageView imageView_d131;
    private final ImageView imageView_d132;

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
        matchId = (TextView) itemView.findViewById(R.id.matchId);
        lobbyType = (TextView) itemView.findViewById(R.id.lobbyType);
        radiantId = (TextView) itemView.findViewById(R.id.radiantTeamId);
        direId = (TextView) itemView.findViewById(R.id.direTeamId);
        matchNum = (TextView) itemView.findViewById(R.id.matchSeqNum);

        imageView_r0 = (ImageView) itemView.findViewById(R.id.rHeroImageView0);
        imageView_r1 = (ImageView) itemView.findViewById(R.id.rHeroImageView1);
        imageView_r2 = (ImageView) itemView.findViewById(R.id.rHeroImageView2);
        imageView_r3 = (ImageView) itemView.findViewById(R.id.rHeroImageView3);
        imageView_r4 = (ImageView) itemView.findViewById(R.id.rHeroImageView4);

        imageView_d128 = (ImageView) itemView.findViewById(R.id.dHeroImageView128);
        imageView_d129 = (ImageView) itemView.findViewById(R.id.dHeroImageView129);
        imageView_d130 = (ImageView) itemView.findViewById(R.id.dHeroImageView130);
        imageView_d131 = (ImageView) itemView.findViewById(R.id.dHeroImageView131);
        imageView_d132 = (ImageView) itemView.findViewById(R.id.dHeroImageView132);
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
        for (final MHPlayer player : emptyIfNull(match.getPlayers())) {
            for (final Hero hero : heroes) {
                if (player.getHeroId() == hero.getId()) {
                    final String name = hero.getName();
                    final int resId = mRes.getIdentifier(name, "drawable", mPkgName);
                    if (resId == 0) {
                        continue;
                    }
                    final ImageView iv = getSlotView(player.getPlayerSlot());
                    Picasso.with(mContext).load(resId).into(iv);
                }
            }
        }
    }

    private ImageView getSlotView(int slot) {
        switch (slot) {
            case 0:
                return imageView_r0;
            case 1:
                return imageView_r1;
            case 2:
                return imageView_r2;
            case 3:
                return imageView_r3;
            case 4:
                return imageView_r4;
            case 128:
                return imageView_d128;
            case 129:
                return imageView_d129;
            case 130:
                return imageView_d130;
            case 131:
                return imageView_d131;
            case 132:
                return imageView_d132;
        }
        return null;
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
