package com.example.jacobkoger.newdota2applicationwsidebar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class View_Holder extends RecyclerView.ViewHolder {

    public View mView;
    TextView textView_MatchNumber, textView_StartTime, textView_LobbyType,
            textView_RadiantID, textView_DireID, textView_Players, matchID_View;

    ImageView imageView_r0, imageView_r1, imageView_r2, imageView_r3, imageView_r4, imageView_d128, imageView_d129, imageView_d130, imageView_d131, imageView_d132;
    boolean isHidden;
    FrameLayout hiddenFrameLayout;

    View_Holder(View view) {
        super(view);
        mView = view;
        matchID_View = (TextView) itemView.findViewById(R.id.matchID_view);
        textView_StartTime = (TextView) itemView.findViewById(R.id.startTime);
        textView_LobbyType = (TextView) itemView.findViewById(R.id.lobbyType);
        textView_RadiantID = (TextView) itemView.findViewById(R.id.radiantTeamId);
        textView_DireID = (TextView) itemView.findViewById(R.id.direTeamId);
//        textView_Players = (TextView) itemView.findViewById(R.id.playerCount);
        textView_MatchNumber = (TextView) itemView.findViewById(R.id.matchSeqNum);

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

        hiddenFrameLayout = (FrameLayout) itemView.findViewById(R.id.hiddenFrameLayout);

    }

    public ImageView getSlotView(int slot) {
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

}