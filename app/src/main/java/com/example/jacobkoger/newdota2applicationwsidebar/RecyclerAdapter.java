package com.example.jacobkoger.newdota2applicationwsidebar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchHistory.Match;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends android.support.v7.widget.RecyclerView.Adapter<View_Holder> {

    public ArrayList<Match> result = new ArrayList<>();

    public RecyclerAdapter() {
    }

    void addData(List<Match> matchHistory){
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





