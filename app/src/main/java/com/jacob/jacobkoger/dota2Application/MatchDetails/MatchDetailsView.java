package com.jacob.jacobkoger.dota2Application.MatchDetails;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jacobkoger.dota2Application.R;
import com.jacob.jacobkoger.dota2Application.data.detail.MDResult;

public class MatchDetailsView extends RelativeLayout {

    ProgressBar mProgress;
    TextView mRadiantKill;
    TextView mDireKill;
    TextView ProgressBarLabel;

    public MatchDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mProgress = (ProgressBar) findViewById(R.id.progressBarKills);
        mRadiantKill = (TextView) findViewById(R.id.radiantKillsTextView);
        mDireKill = (TextView) findViewById(R.id.direKillsTextView);
        ProgressBarLabel = (TextView) findViewById(R.id.progressBarLabelTextView);
    }

    public void bindXpmAverage(int radiantXp, int direXp) {
        final int radiantAveXpm = radiantXp / 5;
        final int direAveXpm = direXp / 5;
        bindData(radiantAveXpm + direAveXpm, radiantAveXpm, "Dire XPM: " + direAveXpm, "Radiant XPM: " + radiantAveXpm, "Team XPM");
    }

    public void bindGpmAverage(int radiantGpm, int direGpm) {
        final int radiantAveGpm = radiantGpm / 5;
        final int direAveGpm = direGpm / 5;
        bindData(radiantAveGpm + direAveGpm, radiantAveGpm, "Dire GPM: " + direAveGpm, "Radiant XPM: " + radiantAveGpm, "Team GPM");
    }

    public void bindGoldSpent(int radiantGoldSpent, int direGoldSpent) {
        bindData(radiantGoldSpent + direGoldSpent, radiantGoldSpent, "Dire Gold Spent: " + direGoldSpent, "Radiant Gold Spent: " + radiantGoldSpent, "Gold Spent");
    }

    public void bindKills(MDResult MDResult) {
        final int direScore = MDResult.getDireScore();
        final int radiantScore = MDResult.getRadiantScore();
        bindData(radiantScore + direScore, radiantScore, "Dire Kills: " + direScore, "Radiant Kills: " + radiantScore, "Kills");
    }

    private void bindData(int max, int currProg, String dire, String rad, String label) {
        mProgress.setMax(max);
        mProgress.setProgress(currProg);
        mDireKill.setText(dire);
        mRadiantKill.setText(rad);
        ProgressBarLabel.setText(label);
    }

}
