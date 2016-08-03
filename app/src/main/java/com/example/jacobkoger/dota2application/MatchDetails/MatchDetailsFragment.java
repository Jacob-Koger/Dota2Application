package com.example.jacobkoger.dota2application.MatchDetails;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
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

import com.example.jacobkoger.dota2application.CacheStrategy;
import com.example.jacobkoger.dota2application.R;
import com.example.jacobkoger.dota2application.ResponseCallbacks.GenericCallback;
import com.example.jacobkoger.dota2application.clients.NonLoggedInClient;
import com.example.jacobkoger.dota2application.data.detail.MDMatchDetails;
import com.example.jacobkoger.dota2application.data.detail.MDPlayer;
import com.example.jacobkoger.dota2application.data.detail.MDResult;
import com.example.jacobkoger.dota2application.data.hero.Hero;
import com.example.jacobkoger.dota2application.data.hero.HeroesList;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MatchDetailsFragment extends Fragment {

    private static final String TAG = MatchDetailsFragment.class.getSimpleName();
    private static final String KEY_MATCH_ID = TAG + ":matchId";
    final List<Hero> mHeroes = new ArrayList<>();
    private final HeroClickListener mHeroClickListener = new HeroClickListener();
    boolean mRadiantWon;
    Boolean isDireDetailsVisible = false;
    Boolean isRadiantDetailsVisible = false;
    TextView mMatchId;
    TextView mWinTeam;
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

    RelativeLayout fullRadiantDetails;
    RelativeLayout fullDireDetails;
    TableLayout direTable;
    TableLayout radiantTable;

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMatchId = (TextView) view.findViewById(R.id.MatchIDTextView);
        mWinTeam = (TextView) view.findViewById(R.id.WinningTeamTextView);
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
        createTableTitles(view);
        setOnClickListenersDire();
        setOnClickListenersRadiant();
        setOnClickListenerRadiantHeroName();
        setOnClickListenerDireHeroName();
        getResult();
    }

    private void setOnClickListenerRadiantHeroName() {
        RadiantTeamHeroName1.setOnClickListener(mHeroClickListener);
        RadiantTeamHeroName2.setOnClickListener(mHeroClickListener);
        RadiantTeamHeroName3.setOnClickListener(mHeroClickListener);
        RadiantTeamHeroName4.setOnClickListener(mHeroClickListener);
        RadiantTeamHeroName5.setOnClickListener(mHeroClickListener);
    }

    private void setOnClickListenerDireHeroName() {
        DireTeamHeroName1.setOnClickListener(mHeroClickListener);
        DireTeamHeroName2.setOnClickListener(mHeroClickListener);
        DireTeamHeroName3.setOnClickListener(mHeroClickListener);
        DireTeamHeroName4.setOnClickListener(mHeroClickListener);
        DireTeamHeroName5.setOnClickListener(mHeroClickListener);
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

    private void getResult() {
        final String matchId = getArguments().getString(KEY_MATCH_ID);
        if (TextUtils.isEmpty(matchId)) {
            return;
        }

        NonLoggedInClient.with(this)
                .cacheStrategy(CacheStrategy.SOFT)
                .enqueueMatchDetails(matchId, new GenericCallback<MDMatchDetails>() {

                    @Override
                    public void onSuccess(MDMatchDetails response) {
                        MDResult result = response.getResult();
                        mMatchId.setText(result.getMatchId());
                        mRadiantWon = result.getRadiantWin();
                        SharedPreferences sharedPreferences = getContext().getSharedPreferences("player_id", Context.MODE_PRIVATE);
                        String playerid = sharedPreferences.getString("player_id", "none");
                        String username = sharedPreferences.getString("username", "none");
                        if (mRadiantWon) {
                            mWinTeam.setText(R.string.radiantWin);
                        } else {
                            mWinTeam.setText(R.string.direWin);
                        }
                        final Resources res = getContext().getResources();
                        for (final MDPlayer MDPlayer : result.getPlayers()) {
                            for (final Hero hero : mHeroes) {
                                if (MDPlayer.getHeroId() == hero.getId()) {
                                    if (MDPlayer.getPlayerSlot() > 4) {
                                        if (MDPlayer.getPlayerSlot() == 128) {
                                            TableRow row1d = new TableRow(getContext());
                                            addTableRows(direTable, row1d, MDPlayer, 7, 7, 3);
                                            if (Objects.equals(playerid, String.valueOf(getNewAccountID(MDPlayer.getAccountId())))) {
                                                DireTeamHeroName1.setText("1. " + username);
                                            } else {
                                                DireTeamHeroName1.setText("1. " + hero.getLocalizedName() + ":");
                                            }
                                            final String heroName = hero.getName();
                                            final int heroId = res.getIdentifier(heroName, "drawable",
                                                    getContext().getPackageName());
                                            DireHeroImageView1.setImageResource(heroId);


                                        } else if (MDPlayer.getPlayerSlot() == 129) {
                                            TableRow row2d = new TableRow(getContext());
                                            addTableRows(direTable, row2d, MDPlayer, 23, 19, 3);
                                            if (Objects.equals(playerid, String.valueOf(getNewAccountID(MDPlayer.getAccountId())))) {
                                                DireTeamHeroName2.setText("2. " + username);
                                            } else {
                                                DireTeamHeroName2.setText("2. " + hero.getLocalizedName() + ":");
                                            }
                                            final String heroName = hero.getName();
                                            final int heroId = res.getIdentifier(heroName, "drawable",
                                                    getContext().getPackageName());
                                            DireHeroImageView2.setImageResource(heroId);


                                        } else if (MDPlayer.getPlayerSlot() == 130) {
                                            TableRow row3d = new TableRow(getContext());
                                            addTableRows(direTable, row3d, MDPlayer, 7, 19, 3);
                                            if (Objects.equals(playerid, String.valueOf(getNewAccountID(MDPlayer.getAccountId())))) {
                                                DireTeamHeroName3.setText("3. " + username);
                                            } else {
                                                DireTeamHeroName3.setText("3. " + hero.getLocalizedName() + ":");
                                            }
                                            final String heroName = hero.getName();
                                            final int heroId = res.getIdentifier(heroName, "drawable",
                                                    getContext().getPackageName());
                                            DireHeroImageView3.setImageResource(heroId);


                                        } else if (MDPlayer.getPlayerSlot() == 131) {
                                            TableRow row4d = new TableRow(getContext());
                                            addTableRows(direTable, row4d, MDPlayer, 7, 19, 3);
                                            if (Objects.equals(playerid, String.valueOf(getNewAccountID(MDPlayer.getAccountId())))) {
                                                DireTeamHeroName4.setText("4. " + username);
                                            } else {
                                                DireTeamHeroName4.setText("4. " + hero.getLocalizedName() + ":");
                                            }
                                            final String heroName = hero.getName();
                                            final int heroId = res.getIdentifier(heroName, "drawable",
                                                    getContext().getPackageName());
                                            DireHeroImageView4.setImageResource(heroId);


                                        } else if (MDPlayer.getPlayerSlot() == 132) {
                                            TableRow row5d = new TableRow(getContext());
                                            addTableRows(direTable, row5d, MDPlayer, 7, 19, 3);
                                            if (Objects.equals(playerid, String.valueOf(getNewAccountID(MDPlayer.getAccountId())))) {
                                                DireTeamHeroName1.setText("5. " + username);
                                            } else {
                                                DireTeamHeroName5.setText("5. " + hero.getLocalizedName() + ":");
                                            }
                                            final String heroName = hero.getName();
                                            final int heroId = res.getIdentifier(heroName, "drawable",
                                                    getContext().getPackageName());
                                            DireHeroImageView5.setImageResource(heroId);


                                        }
                                    } else {
                                        if (MDPlayer.getPlayerSlot() == 0) {
                                            TableRow row1r = new TableRow(getContext());
                                            addTableRows(radiantTable, row1r, MDPlayer, 7, 7, 3);
                                            if (Objects.equals(playerid, String.valueOf(getNewAccountID(MDPlayer.getAccountId())))) {
                                                DireTeamHeroName1.setText("5. " + username);
                                            } else {
                                                RadiantTeamHeroName1.setText("1. " + hero.getLocalizedName() + ":");
                                            }
                                            final String heroName = hero.getName();
                                            final int heroId = res.getIdentifier(heroName, "drawable",
                                                    getContext().getPackageName());
                                            RadiantHeroImageView1.setImageResource(heroId);


                                        } else if (MDPlayer.getPlayerSlot() == 1) {
                                            TableRow row2r = new TableRow(getContext());
                                            addTableRows(radiantTable, row2r, MDPlayer, 23, 19, 3);
                                            if (Objects.equals(playerid, String.valueOf(getNewAccountID(MDPlayer.getAccountId())))) {
                                                DireTeamHeroName1.setText("5. " + username);
                                            } else {
                                                RadiantTeamHeroName2.setText("2. " + hero.getLocalizedName() + ":");
                                            }
                                            final String heroName = hero.getName();
                                            final int heroId = res.getIdentifier(heroName, "drawable",
                                                    getContext().getPackageName());
                                            RadiantHeroImageView2.setImageResource(heroId);


                                        } else if (MDPlayer.getPlayerSlot() == 2) {
                                            TableRow row3r = new TableRow(getContext());
                                            addTableRows(radiantTable, row3r, MDPlayer, 7, 19, 3);
                                            if (Objects.equals(playerid, String.valueOf(getNewAccountID(MDPlayer.getAccountId())))) {
                                                DireTeamHeroName1.setText("5. " + username);
                                            } else {
                                                RadiantTeamHeroName3.setText("3. " + hero.getLocalizedName() + ":");
                                            }
                                            final String heroName = hero.getName();
                                            final int heroId = res.getIdentifier(heroName, "drawable",
                                                    getContext().getPackageName());
                                            RadiantHeroImageView3.setImageResource(heroId);


                                        } else if (MDPlayer.getPlayerSlot() == 3) {
                                            TableRow row4r = new TableRow(getContext());
                                            addTableRows(radiantTable, row4r, MDPlayer, 7, 19, 3);
                                            if (Objects.equals(playerid, String.valueOf(getNewAccountID(MDPlayer.getAccountId())))) {
                                                DireTeamHeroName1.setText("5. " + username);
                                            } else {
                                                RadiantTeamHeroName4.setText("4. " + hero.getLocalizedName() + ":");
                                            }
                                            final String heroName = hero.getName();
                                            final int heroId = res.getIdentifier(heroName, "drawable",
                                                    getContext().getPackageName());
                                            RadiantHeroImageView4.setImageResource(heroId);


                                        } else if (MDPlayer.getPlayerSlot() == 4) {
                                            TableRow row5r = new TableRow(getContext());
                                            addTableRows(radiantTable, row5r, MDPlayer, 7, 19, 3);
                                            if (Objects.equals(playerid, String.valueOf(getNewAccountID(MDPlayer.getAccountId())))) {
                                                DireTeamHeroName1.setText("5. " + username);
                                            } else {
                                                RadiantTeamHeroName5.setText("5. " + hero.getLocalizedName() + ":");
                                            }
                                            final String heroName = hero.getName();
                                            final int heroId = res.getIdentifier(heroName, "drawable",
                                                    getContext().getPackageName());
                                            RadiantHeroImageView5.setImageResource(heroId);


                                        }
                                    }
                                }
                            }
                        }

                        final ProgressHolder xp = new ProgressHolder();
                        final ProgressHolder gpm = new ProgressHolder();
                        final ProgressHolder gold = new ProgressHolder();

                        for (MDPlayer player : result.getPlayers()) {
                            final int currXpm = player.getXpPerMin();
                            final int currGpm = player.getGoldPerMin();
                            final int currGold = player.getGoldSpent();
                            final int slot = player.getPlayerSlot();
                            if (slot >= 0 && slot <= 4) {
                                xp.radiant += currXpm;
                                gpm.radiant += currGpm;
                                gold.radiant += currGold;
                            } else {
                                xp.dire += currXpm;
                                gpm.dire += currGpm;
                                gold.dire += currGold;
                            }
                        }

                        final MatchDetailsView killProgress = inflateProgressView();
                        killProgress.bindKills(result);
                        mProgressContainer.addView(killProgress);

                        final MatchDetailsView xpProgress = inflateProgressView();
                        xpProgress.bindXpmAverage(xp.radiant, xp.dire);
                        mProgressContainer.addView(xpProgress);

                        final MatchDetailsView gpmProgress = inflateProgressView();
                        gpmProgress.bindGpmAverage(gpm.radiant, gpm.dire);
                        mProgressContainer.addView(gpmProgress);

                        final MatchDetailsView goldProgress = inflateProgressView();
                        goldProgress.bindGoldSpent(gold.radiant, gold.dire);
                        mProgressContainer.addView(goldProgress);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.d("mData", "fail", t);
                    }

                });

    }
    private void addTableRows(TableLayout tablename, TableRow row, MDPlayer MDPlayer,
                              int paddingt, int paddingb, int paddinglr) {
        float ikills = MDPlayer.getKills();
        float iassists = MDPlayer.getAssists();
        float ideaths = MDPlayer.getDeaths();
        float ka = ikills + iassists;
        double ikda = ka / ideaths;
        DecimalFormat decimalformat = new DecimalFormat("##.00");
        String rkda = decimalformat.format(ikda);
        TextView level = new TextView(getContext());
        setTableText(level, String.valueOf(MDPlayer.getLevel()), Gravity.START,
                paddingt, paddingb, paddinglr, row);
        TextView kills = new TextView(getContext());
        setTableText(kills, String.valueOf(MDPlayer.getKills()), Gravity.CENTER,
                paddingt, paddingb, paddinglr, row);
        TextView deaths = new TextView(getContext());
        setTableText(deaths, String.valueOf(MDPlayer.getDeaths()), Gravity.CENTER,
                paddingt, paddingb, paddinglr, row);
        TextView assists = new TextView(getContext());
        setTableText(assists, String.valueOf(MDPlayer.getAssists()), Gravity.CENTER,
                paddingt, paddingb, paddinglr, row);
        TextView kda = new TextView(getContext());
        setTableText(kda, rkda, Gravity.CENTER, paddingt, paddingb, paddinglr, row);
        TextView GPM = new TextView(getContext());
        setTableText(GPM, String.valueOf(MDPlayer.getGoldPerMin()), Gravity.CENTER,
                paddingt, paddingb, paddinglr, row);
        TextView XPM = new TextView(getContext());
        setTableText(XPM, String.valueOf(MDPlayer.getXpPerMin()), Gravity.CENTER,
                paddingt, paddingb, paddinglr, row);
        TextView LH = new TextView(getContext());
        setTableText(LH, String.valueOf(MDPlayer.getLastHits()), Gravity.CENTER,
                paddingt, paddingb, paddinglr, row);
        TextView DN = new TextView(getContext());
        setTableText(DN, String.valueOf(MDPlayer.getDenies()), Gravity.CENTER,
                paddingt, paddingb, paddinglr, row);
        TextView HDmg = new TextView(getContext());
        setTableText(HDmg, String.valueOf(MDPlayer.getHeroDamage()), Gravity.CENTER,
                paddingt, paddingb, paddinglr, row);
        TextView HHl = new TextView(getContext());
        setTableText(HHl, String.valueOf(MDPlayer.getHeroHealing()), Gravity.CENTER,
                paddingt, paddingb, paddinglr, row);
        TextView TDmg = new TextView(getContext());
        setTableText(TDmg, String.valueOf(MDPlayer.getTowerDamage()), Gravity.END,
                paddingt, paddingb, paddinglr, row);
        tablename.addView(row);
    }
    private void setTableText(TextView view, String text, int gravity, int paddingt, int paddingb,
                              int paddinglr, TableRow tablerow) {
        int paddingtpx = dpToPx(paddingt);
        int paddinglrpx = dpToPx(paddinglr);
        int paddingbpx = dpToPx(paddingb);
        view.setText(text);
        view.setGravity(gravity);
        view.setPadding(paddinglrpx, paddingtpx, paddinglrpx, paddingbpx);
        view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tablerow.addView(view);
    }

    private void setTableTitleText(TextView view, String text, int gravity, int padding,
                                   TableRow tablerow) {
        view.setText(text);
        view.setGravity(gravity);
        view.setPadding(padding, padding, padding, padding);
        view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tablerow.addView(view);
    }

    private void createTableTitles(View view) {

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

    MatchDetailsView inflateProgressView() {
        final LayoutInflater inflater = LayoutInflater.from(getContext());
        return (MatchDetailsView) inflater.inflate(R.layout.progressbar, mProgressContainer, false);
    }

    private long getNewAccountID(long id) {
        long accountchange = 76561197960265728L;
        long newid = accountchange + id;
        return newid;
    }

    static class ProgressHolder {

        int dire;
        int radiant;

    }

    private int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
    static class HeroClickListener implements View.OnClickListener {

        private static final String GAME_PEDIA = "http://dota2.gamepedia.com/%s";

        @Override
        public void onClick(View view) {
            final String hero = String.valueOf(((TextView) view).getText());
            final String num = String.valueOf(hero.charAt(hero.indexOf('.') + 1));
            final String name = hero.replace(num, "").replace(":", "").replace(" ", "_");
            view.getContext().startActivity(
                    new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(GAME_PEDIA, name))));
        }
    }
}
