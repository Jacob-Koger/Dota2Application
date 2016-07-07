package com.example.jacobkoger.newdota2applicationwsidebar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class View_Holder extends RecyclerView.ViewHolder {

    TextView textView_MatchNumber, textView_StartTime, textView_LobbyType,
            textView_RadiantID, textView_DireID, textView_Players, matchID_View;
    ImageView imageViewBottle;
    MatchProgressView matchProgressView;

    View_Holder(View itemView) {
        super(itemView);
        matchID_View = (TextView) itemView.findViewById(R.id.matchID_view);
        textView_StartTime = (TextView) itemView.findViewById(R.id.startTime);
        textView_LobbyType = (TextView) itemView.findViewById(R.id.lobbyType);
        textView_RadiantID = (TextView) itemView.findViewById(R.id.radiantTeamId);
        textView_DireID = (TextView) itemView.findViewById(R.id.direTeamId);
        textView_Players = (TextView) itemView.findViewById(R.id.playerCount);
        textView_MatchNumber = (TextView) itemView.findViewById(R.id.matchSeqNum);
        imageViewBottle = (ImageView) itemView.findViewById(R.id.imageViewBottle);
    }
}