package com.example.jacobkoger.newdota2applicationwsidebar;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;


public class View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView textView_MatchNumber, textView_StartTime, textView_LobbyType,
            textView_RadiantID, textView_DireID, textView_Players, matchID_View;

    public View mView;

    private boolean mExpanded;
    private int mOrigHeight;
    private int mExpandedPos = -1;

    View_Holder(View view) {
        super(view);
        mView = view;
        itemView.setOnClickListener(this);
        matchID_View = (TextView) itemView.findViewById(R.id.matchID_view);
        textView_StartTime = (TextView) itemView.findViewById(R.id.startTime);
        textView_LobbyType = (TextView) itemView.findViewById(R.id.lobbyType);
        textView_RadiantID = (TextView) itemView.findViewById(R.id.radiantTeamId);
        textView_DireID = (TextView) itemView.findViewById(R.id.direTeamId);
//        textView_Players = (TextView) itemView.findViewById(R.id.playerCount);
        textView_MatchNumber = (TextView) itemView.findViewById(R.id.matchSeqNum);
        updateHeight();
    }

    @Override
    public void onClick(View view) {
        Log.d("tag", "onClick " + getAdapterPosition() + " ");

        mExpanded = itemView.getHeight() > mOrigHeight;
        mExpandedPos = mExpanded ? getAdapterPosition() : -1;
    }

    public void setExpanded() {
        updateHeight();
        itemView.getLayoutParams().height =  mOrigHeight * 2;
        itemView.requestLayout();
    }

    public void setCollapsed() {
        updateHeight();
        itemView.getLayoutParams().height =  mOrigHeight;
        itemView.requestLayout();
    }

    public int getExpandedPos() {
        return mExpandedPos;
    }

    private void updateHeight(){
        if(mOrigHeight == -1){
            itemView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    itemView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    mOrigHeight = itemView.getHeight();
                }
            });
        }
    }

}