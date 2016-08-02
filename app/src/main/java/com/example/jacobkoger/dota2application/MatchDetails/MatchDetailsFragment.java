package com.example.jacobkoger.dota2application.MatchDetails;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.jacobkoger.dota2application.R;
import com.example.jacobkoger.dota2application.clients.NonLoggedInClient;
import com.example.jacobkoger.dota2application.data.detail.MDMatchDetails;
import com.example.jacobkoger.dota2application.data.hero.Hero;
import com.example.jacobkoger.dota2application.data.hero.HeroesList;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.example.jacobkoger.dota2application.util.Utils.requireNonNull;


public class MatchDetailsFragment extends Fragment
        implements MatchDetailsContract.View{
    private MatchDetailsContract.Presenter presenter;

    private static final String TAG = MatchDetailsFragment.class.getSimpleName();
    private static final String KEY_MATCH_ID = TAG + ":matchId";
    public final List<Hero> mHeroes = new ArrayList<>();
    public boolean didRadiantWin;
    TextView HelpLabel;
    TextView MatchIDTextView;
    TextView WinningTeamTextView;
    TextView DireTeamDetailsTextView;
    TextView RadiantTeamDetailsTextView;
    TextView DireTeamHeroName1;
    TextView DireTeamHeroName2;
    TextView DireTeamHeroName3;
    TextView DireTeamHeroName4;
    TextView DireTeamHeroName5;
    TextView RadiantTeamHeroName1;
    TextView RadiantTeamHeroName2;
    TextView RadiantTeamHeroName3;
    TextView RadiantTeamHeroName4;
    TextView RadiantTeamHeroName5;

    LinearLayout mProgressContainer;

    ImageButton OpenDireTeamDetailsButton;
    ImageButton OpenRadiantTeamDetailsButton;

    ImageView DireHeroImageView1;
    ImageView DireHeroImageView2;
    ImageView DireHeroImageView3;
    ImageView DireHeroImageView4;
    ImageView DireHeroImageView5;
    ImageView RadiantHeroImageView1;
    ImageView RadiantHeroImageView2;
    ImageView RadiantHeroImageView3;
    ImageView RadiantHeroImageView4;
    ImageView RadiantHeroImageView5;

    Boolean isDireDetailsVisible = false;
    Boolean isRadiantDetailsVisible = false;

    RelativeLayout fullRadiantDetails;
    RelativeLayout fullDireDetails;
    TableLayout direTable;
    TableLayout radiantTable;
    public MatchDetailsFragment() {
    }

    public static MatchDetailsFragment newInstance(String matchId) {
        final Bundle args = new Bundle();
        args.putString(KEY_MATCH_ID, matchId);

        final MatchDetailsFragment fragment = new MatchDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getContext();

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
        return inflater.inflate(R.layout.fragment_display_match_details, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MatchIDTextView = (TextView) view.findViewById(R.id.MatchIDTextView);
        WinningTeamTextView = (TextView) view.findViewById(R.id.WinningTeamTextView);
        DireTeamDetailsTextView = (TextView) view.findViewById(R.id.direTeamDetailsTextView);
        RadiantTeamDetailsTextView = (TextView) view.findViewById(R.id.radiantTeamDetailsTextView);

        DireTeamHeroName1 = (TextView) view.findViewById(R.id.direHeroName1);
        DireTeamHeroName2 = (TextView) view.findViewById(R.id.direHeroName2);
        DireTeamHeroName3 = (TextView) view.findViewById(R.id.direHeroName3);
        DireTeamHeroName4 = (TextView) view.findViewById(R.id.direHeroName4);
        DireTeamHeroName5 = (TextView) view.findViewById(R.id.direHeroName5);

        RadiantTeamHeroName1 = (TextView) view.findViewById(R.id.radiantHeroName1);
        RadiantTeamHeroName2 = (TextView) view.findViewById(R.id.radiantHeroName2);
        RadiantTeamHeroName3 = (TextView) view.findViewById(R.id.radiantHeroName3);
        RadiantTeamHeroName4 = (TextView) view.findViewById(R.id.radiantHeroName4);
        RadiantTeamHeroName5 = (TextView) view.findViewById(R.id.radiantHeroName5);

        DireHeroImageView1 = (ImageView) view.findViewById(R.id.direHeroImageView1);
        DireHeroImageView2 = (ImageView) view.findViewById(R.id.direHeroImageView2);
        DireHeroImageView3 = (ImageView) view.findViewById(R.id.direHeroImageView3);
        DireHeroImageView4 = (ImageView) view.findViewById(R.id.direHeroImageView4);
        DireHeroImageView5 = (ImageView) view.findViewById(R.id.direHeroImageView5);

        RadiantHeroImageView1 = (ImageView) view.findViewById(R.id.radiantHeroImageView1);
        RadiantHeroImageView2 = (ImageView) view.findViewById(R.id.radiantHeroImageView2);
        RadiantHeroImageView3 = (ImageView) view.findViewById(R.id.radiantHeroImageView3);
        RadiantHeroImageView4 = (ImageView) view.findViewById(R.id.radiantHeroImageView4);
        RadiantHeroImageView5 = (ImageView) view.findViewById(R.id.radiantHeroImageView5);

        OpenDireTeamDetailsButton = (ImageButton) view.findViewById(R.id.openDireTeamDetails);
        OpenRadiantTeamDetailsButton = (ImageButton) view.findViewById(R.id.openRadiantTeamDetails);

        mProgressContainer = (LinearLayout) view.findViewById(R.id.progressbarLLContainer);
        fullRadiantDetails = (RelativeLayout) view.findViewById(R.id.fullradiantdetails);
        fullDireDetails = (RelativeLayout) view.findViewById(R.id.fulldiredetails);
        direTable = (TableLayout) view.findViewById(R.id.tablelayoutdire);
        radiantTable = (TableLayout) view.findViewById(R.id.tablelayoutradiant);
        createTableTitles();
        setOnClickListenersDire();
        setOnClickListenersRadiant();
        fetchMatchDetails(getArguments().getString(KEY_MATCH_ID));
        startAnim();
    }
    private void setTableTitleText(TextView view, String text, int gravity, int padding,
                                   TableRow tablerow) {
        view.setText(text);
        view.setGravity(gravity);
        view.setPadding(padding, padding, padding, padding);
        view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tablerow.addView(view);
    }

    private void createTableTitles() {

        TableRow dtitles = new TableRow(getContext());

        TextView dlevelTv = new TextView(getContext());
        setTableTitleText(dlevelTv, " Lvl ", Gravity.START, 3, dtitles);
        TextView dkillsTv = new TextView(getContext());
        setTableTitleText(dkillsTv, " K ", Gravity.CENTER, 3, dtitles);
        TextView ddeathsTv = new TextView(getContext());
        setTableTitleText(ddeathsTv, " D ", Gravity.CENTER, 3, dtitles);
        TextView dassistsTv = new TextView(getContext());
        setTableTitleText(dassistsTv, " A ", Gravity.CENTER, 3, dtitles);
        TextView dKDA = new TextView(getContext());
        setTableTitleText(dKDA, " KDA ", Gravity.CENTER, 3, dtitles);
        TextView dgpmTv = new TextView(getContext());
        setTableTitleText(dgpmTv, " GPM ", Gravity.CENTER, 3, dtitles);
        TextView dxpmTv = new TextView(getContext());
        setTableTitleText(dxpmTv, " XPM ", Gravity.CENTER, 3, dtitles);
        TextView dlhTv = new TextView(getContext());
        setTableTitleText(dlhTv, " LH ", Gravity.CENTER, 3, dtitles);
        TextView ddnTv = new TextView(getContext());
        setTableTitleText(ddnTv, " DN ", Gravity.CENTER, 3, dtitles);
        TextView dHDTv = new TextView(getContext());
        setTableTitleText(dHDTv, " HD ", Gravity.CENTER, 3, dtitles);
        TextView dHHl = new TextView(getContext());
        setTableTitleText(dHHl, " HH ", Gravity.CENTER, 3, dtitles);
        TextView dTDTv = new TextView(getContext());
        setTableTitleText(dTDTv, " TD ", Gravity.END, 3, dtitles);

        direTable.addView(dtitles);

        TableRow rtitles = new TableRow(getContext());

        TextView rlevelTv = new TextView(getContext());
        setTableTitleText(rlevelTv, " Lvl ", Gravity.START, 3, rtitles);
        TextView rkillsTv = new TextView(getContext());
        setTableTitleText(rkillsTv, " K ", Gravity.CENTER, 3, rtitles);
        TextView rdeathsTv = new TextView(getContext());
        setTableTitleText(rdeathsTv, " D ", Gravity.CENTER, 3, rtitles);
        TextView rassistsTv = new TextView(getContext());
        setTableTitleText(rassistsTv, " A ", Gravity.CENTER, 3, rtitles);
        TextView rKDA = new TextView(getContext());
        setTableTitleText(rKDA, " KDA ", Gravity.CENTER, 3, rtitles);
        TextView rgpmTv = new TextView(getContext());
        setTableTitleText(rgpmTv, " GPM ", Gravity.CENTER, 3, rtitles);
        TextView rxpmTv = new TextView(getContext());
        setTableTitleText(rxpmTv, " XPM ", Gravity.CENTER, 3, rtitles);
        TextView rlhTv = new TextView(getContext());
        setTableTitleText(rlhTv, " LH ", Gravity.CENTER, 3, rtitles);
        TextView rdnTv = new TextView(getContext());
        setTableTitleText(rdnTv, " DN ", Gravity.CENTER, 3, rtitles);
        TextView rHDTv = new TextView(getContext());
        setTableTitleText(rHDTv, " HD ", Gravity.CENTER, 3, rtitles);
        TextView rHHl = new TextView(getContext());
        setTableTitleText(rHHl, " HH ", Gravity.CENTER, 3, rtitles);
        TextView rTDTv = new TextView(getContext());
        setTableTitleText(rTDTv, " TD ", Gravity.END, 3, rtitles);

        radiantTable.addView(rtitles);
    }
    private void setOnClickListenersRadiant() {
        OpenRadiantTeamDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRadiantDetailsVisible == false && isDireDetailsVisible == false) {
                    fullRadiantDetails.setVisibility(View.VISIBLE);
                    isRadiantDetailsVisible = true;
                    isDireDetailsVisible = false;
                } else if (isRadiantDetailsVisible == true && isDireDetailsVisible == false) {
                    fullRadiantDetails.setVisibility(View.GONE);
                    isRadiantDetailsVisible = false;
                    isDireDetailsVisible = false;
                } else if (isRadiantDetailsVisible == false && isDireDetailsVisible == true) {
                    fullDireDetails.setVisibility(View.GONE);
                    fullRadiantDetails.setVisibility(View.VISIBLE);
                    isRadiantDetailsVisible = true;
                    isDireDetailsVisible = false;
                }

                ObjectAnimator.ofFloat(OpenDireTeamDetailsButton, "rotation",
                        isDireDetailsVisible ? 180 : 0)
                        .setDuration(500)
                        .start();
                ObjectAnimator.ofFloat(OpenRadiantTeamDetailsButton, "rotation",
                        isRadiantDetailsVisible ? 180 : 0)
                        .setDuration(500)
                        .start();
            }
        });
    }
    private void setOnClickListenersDire() {
        OpenDireTeamDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDireDetailsVisible == false && isRadiantDetailsVisible == false) {
                    fullDireDetails.setVisibility(View.VISIBLE);
                    isDireDetailsVisible = true;
                    isRadiantDetailsVisible = false;
                } else if (isDireDetailsVisible == true && isRadiantDetailsVisible == false) {
                    fullDireDetails.setVisibility(View.GONE);
                    isDireDetailsVisible = false;
                    isRadiantDetailsVisible = false;
                } else if (isDireDetailsVisible == false && isRadiantDetailsVisible == true) {
                    fullRadiantDetails.setVisibility(View.GONE);
                    fullDireDetails.setVisibility(View.VISIBLE);

                    isDireDetailsVisible = true;
                    isRadiantDetailsVisible = false;
                }
                ObjectAnimator.ofFloat(OpenDireTeamDetailsButton, "rotation",
                        isDireDetailsVisible ? 180 : 0)
                        .setDuration(500)
                        .start();
                ObjectAnimator.ofFloat(OpenRadiantTeamDetailsButton, "rotation",
                        isRadiantDetailsVisible ? 180 : 0)
                        .setDuration(500)
                        .start();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void startAnim() {
        DireTeamDetailsTextView.setAlpha(0f);
        OpenDireTeamDetailsButton.setAlpha(0f);
        RadiantTeamDetailsTextView.setAlpha(0f);
        OpenRadiantTeamDetailsButton.setAlpha(0f);
        HelpLabel.setAlpha(0f);

        ObjectAnimator fadeInDTV = ObjectAnimator.ofFloat(DireTeamDetailsTextView, "alpha", 0f, 1f);
        fadeInDTV.setDuration(1000);
        ObjectAnimator fadeInDButton = ObjectAnimator.ofFloat(OpenDireTeamDetailsButton, "alpha", 0f, 1f);
        fadeInDButton.setDuration(900);
        ObjectAnimator fadeInRTV = ObjectAnimator.ofFloat(RadiantTeamDetailsTextView, "alpha", 0f, 1f);
        fadeInRTV.setDuration(1000);
        ObjectAnimator fadeInRButton = ObjectAnimator.ofFloat(OpenRadiantTeamDetailsButton, "alpha", 0f, 1f);
        fadeInRButton.setDuration(900);
        ObjectAnimator fadeInHL = ObjectAnimator.ofFloat(HelpLabel, "alpha", 0f, 1f);
        fadeInHL.setDuration(1000);

        final AnimatorSet mAnimationSet = new AnimatorSet();

        mAnimationSet.play(fadeInDTV).
                with(fadeInDButton)
                .with(fadeInRButton)
                .with(fadeInRTV)
                .with(fadeInHL)
                .after(500);

        mAnimationSet.start();
    }

    @Override
    public void setPresenter(@NonNull MatchDetailsContract.Presenter mPresenter) {
        presenter = requireNonNull(mPresenter, "Presenter can't be Null");
    }

    @Override
    public void fetchMatchDetails(String matchId) {
        final String aID;
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("player_id", Context.MODE_PRIVATE);
        aID = sharedPreferences.getString("player_id", null);
        NonLoggedInClient.with(this).enqueueMatchDetails(presenter, aID);
    }

    @Override
    public void bindMatches(List<MDMatchDetails> MatchDetails) {

    }

    private long getNewAccountID(long id) {
        long accountChange = 76561197960265728L;
        long newId = accountChange + id;
        return newId;
    }

    public interface OnFragmentInteractionListener {
    }
}