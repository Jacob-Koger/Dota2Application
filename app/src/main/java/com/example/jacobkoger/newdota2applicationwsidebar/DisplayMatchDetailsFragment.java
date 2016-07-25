package com.example.jacobkoger.newdota2applicationwsidebar;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.example.jacobkoger.newdota2applicationwsidebar.POJO_Heroes.Hero;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_Heroes.HeroesList;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails.MDMatchDetails;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails.MDPlayer;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DisplayMatchDetailsFragment extends Fragment {
    private static final String TAG = DisplayMatchDetailsFragment.class.getSimpleName();
    private static final String KEY_MATCH_ID = TAG + ":matchId";
    public final List<Hero> mHeroes = new ArrayList<>();
    public boolean didRadiantWin;
    String url = "https://api.steampowered.com";
    TextView HelpLabel, MatchIDTextView, WinningTeamTextView, DireTeamDetailsTextView, RadiantTeamDetailsTextView,
            DireTeamHeroName1, DireTeamHeroName2, DireTeamHeroName3, DireTeamHeroName4, DireTeamHeroName5,
            RadiantTeamHeroName1, RadiantTeamHeroName2, RadiantTeamHeroName3, RadiantTeamHeroName4, RadiantTeamHeroName5;

    LinearLayout mProgressContainer;
    ImageButton OpenDireTeamDetailsButton, OpenRadiantTeamDetailsButton;
    ImageView DireHeroImageView1, DireHeroImageView2, DireHeroImageView3, DireHeroImageView4, DireHeroImageView5,
            RadiantHeroImageView1, RadiantHeroImageView2, RadiantHeroImageView3, RadiantHeroImageView4, RadiantHeroImageView5;
    Boolean isDireDetailsVisible = false, isRadiantDetailsVisible = false;
    RelativeLayout fullRadiantDetails, fullDireDetails;
    TableLayout direTable, radiantTable;

    public DisplayMatchDetailsFragment() {
    }

    public static DisplayMatchDetailsFragment newInstance(String matchId) {
        final Bundle args = new Bundle();
        args.putString(KEY_MATCH_ID, matchId);

        final DisplayMatchDetailsFragment fragment = new DisplayMatchDetailsFragment();
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
        HelpLabel = (TextView) view.findViewById(R.id.helplabel);

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
        startAnim();
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
        TextView dgpmTv = new TextView(getContext());
        setTableTitleText(dgpmTv, " GPM ", Gravity.CENTER, 3, dtitles);
        TextView dxpmTv = new TextView(getContext());
        setTableTitleText(dxpmTv, " XPM ", Gravity.CENTER, 3, dtitles);
        TextView dlhTv = new TextView(getContext());
        setTableTitleText(dlhTv, " LH ", Gravity.CENTER, 3, dtitles);
        TextView ddnTv = new TextView(getContext());
        setTableTitleText(ddnTv, " DN ", Gravity.CENTER, 3, dtitles);
        TextView dHDTv = new TextView(getContext());
        setTableTitleText(dHDTv, " HDmg ", Gravity.CENTER, 3, dtitles);
        TextView dTDTv = new TextView(getContext());
        setTableTitleText(dTDTv, " TDmg ", Gravity.CENTER, 3, dtitles);

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
        TextView rgpmTv = new TextView(getContext());
        setTableTitleText(rgpmTv, " GPM ", Gravity.CENTER, 3, rtitles);
        TextView rxpmTv = new TextView(getContext());
        setTableTitleText(rxpmTv, " XPM ", Gravity.CENTER, 3, rtitles);
        TextView rlhTv = new TextView(getContext());
        setTableTitleText(rlhTv, " LH ", Gravity.CENTER, 3, rtitles);
        TextView rdnTv = new TextView(getContext());
        setTableTitleText(rdnTv, " DN ", Gravity.CENTER, 3, rtitles);
        TextView rHDTv = new TextView(getContext());
        setTableTitleText(rHDTv, " HDmg ", Gravity.CENTER, 3, rtitles);
        TextView rTDTv = new TextView(getContext());
        setTableTitleText(rTDTv, " TDmg ", Gravity.CENTER, 3, rtitles);

        radiantTable.addView(rtitles);
    }

    private void setTableText(TextView view, String text, int gravity, int paddingt, int paddingb, int paddinglr, TableRow tablerow) {
        view.setText(text);
        view.setGravity(gravity);
        view.setPadding(paddinglr, paddingt, paddinglr, paddingb);
        view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tablerow.addView(view);
    }

    public void setTableTitleText(TextView view, String text, int gravity, int padding, TableRow tablerow) {
        view.setText(text);
        view.setGravity(gravity);
        view.setPadding(padding, padding, padding, padding);
        view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tablerow.addView(view);
    }

    private void setOnClickListenerRadiantHeroName() {
        RadiantTeamHeroName1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hero_namewnum = RadiantTeamHeroName1.getText().toString();
                String hero_name = hero_namewnum.replace("1. ", "").replace(":", "").replace(" ", "_");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dota2.gamepedia.com/" + hero_name));
                startActivity(intent);
            }
        });
        RadiantTeamHeroName2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hero_namewnum = RadiantTeamHeroName2.getText().toString();
                String hero_name = hero_namewnum.replace("2. ", "").replace(":", "").replace(" ", "_");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dota2.gamepedia.com/" + hero_name));
                startActivity(intent);
            }
        });
        RadiantTeamHeroName3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hero_namewnum = RadiantTeamHeroName3.getText().toString();
                String hero_name = hero_namewnum.replace("3. ", "").replace(":", "").replace(" ", "_");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dota2.gamepedia.com/" + hero_name));
                startActivity(intent);
            }
        });
        RadiantTeamHeroName4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hero_namewnum = RadiantTeamHeroName4.getText().toString();
                String hero_name = hero_namewnum.replace("4. ", "").replace(":", "").replace(" ", "_");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dota2.gamepedia.com/" + hero_name));
                startActivity(intent);
            }
        });
        RadiantTeamHeroName5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hero_namewnum = RadiantTeamHeroName5.getText().toString();
                String hero_name = hero_namewnum.replace("5. ", "").replace(":", "").replace(" ", "_");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dota2.gamepedia.com/" + hero_name));
                startActivity(intent);
            }
        });
    }

    private void setOnClickListenerDireHeroName() {
        DireTeamHeroName1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hero_namewnum = DireTeamHeroName1.getText().toString();
                String hero_name = hero_namewnum.replace("1. ", "").replace(":", "").replace(" ", "_");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dota2.gamepedia.com/" + hero_name));
                startActivity(intent);
            }
        });
        DireTeamHeroName2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hero_namewnum = DireTeamHeroName2.getText().toString();
                String hero_name = hero_namewnum.replace("2. ", "").replace(":", "").replace(" ", "_");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dota2.gamepedia.com/" + hero_name));
                startActivity(intent);
            }
        });
        DireTeamHeroName3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hero_namewnum = DireTeamHeroName3.getText().toString();
                String hero_name = hero_namewnum.replace("3. ", "").replace(":", "").replace(" ", "_");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dota2.gamepedia.com/" + hero_name));
                startActivity(intent);
            }
        });
        DireTeamHeroName4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hero_namewnum = DireTeamHeroName4.getText().toString();
                String hero_name = hero_namewnum.replace("4. ", "").replace(":", "").replace(" ", "_");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dota2.gamepedia.com/" + hero_name));
                startActivity(intent);
            }
        });
        DireTeamHeroName5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hero_namewnum = DireTeamHeroName5.getText().toString();
                String hero_name = hero_namewnum.replace("5. ", "").replace(":", "").replace(" ", "_");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dota2.gamepedia.com/" + hero_name));
                startActivity(intent);
            }
        });
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

        mAnimationSet.play(fadeInDTV).with(fadeInDButton).with(fadeInRButton).with(fadeInRTV).with(fadeInHL).after(500);

        mAnimationSet.start();
    }

    public void getResult() {
        final String matchId = getArguments().getString(KEY_MATCH_ID);
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                final Request orig = chain.request();
                HttpUrl origUrl = orig.url();
                return chain.proceed(orig.newBuilder()
                        .url(origUrl.newBuilder().addQueryParameter("key", BuildConfig.API_KEY)
                                .build())
                        .build());
            }
        });

        final Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MatchDetailsInterface service = retrofit.create(MatchDetailsInterface.class);
        Call<MDMatchDetails> callMH = service.getMatchDetails(matchId);
        callMH.enqueue(new Callback<MDMatchDetails>() {

            @Override
            public void onResponse(Call<MDMatchDetails> call, Response<MDMatchDetails> response) {
                MDMatchDetails result = response.body();
                MDPlayer players = result.getMDResult().getMDPlayers().get(0);
                MatchIDTextView.setText(result.getMDResult().getMatchId());
                didRadiantWin = result.getMDResult().getRadiantWin();
                if (didRadiantWin) {
                    WinningTeamTextView.setText(R.string.radiantWin);
                } else {
                    WinningTeamTextView.setText(R.string.direWin);
                }
                final Resources res = getContext().getResources();
                for (final MDPlayer MDPlayer : result.getMDResult().getMDPlayers()) {
                    for (final Hero hero : mHeroes) {
                        if (MDPlayer.getHeroId() == hero.getId()) {
                            if (MDPlayer.getPlayerSlot() > 4) {
                                if (MDPlayer.getPlayerSlot() == 128) {
                                    TableRow row1d = new TableRow(getContext());

                                    DireTeamHeroName1.setText("1. " + hero.getLocalizedName() + ":");
                                    TextView dp1level = new TextView(getContext());
                                    setTableText(dp1level, MDPlayer.getLevel().toString(), Gravity.START, 3, 3, 3, row1d);
                                    TextView dp1kills = new TextView(getContext());
                                    setTableText(dp1kills, MDPlayer.getKills().toString(), Gravity.CENTER, 3, 3, 3, row1d);
                                    TextView dp1deaths = new TextView(getContext());
                                    setTableText(dp1deaths, MDPlayer.getDeaths().toString(), Gravity.CENTER, 3, 3, 3, row1d);
                                    TextView dp1assists = new TextView(getContext());
                                    setTableText(dp1assists, MDPlayer.getAssists().toString(), Gravity.CENTER, 3, 3, 3, row1d);
                                    TextView dp1GPM = new TextView(getContext());
                                    setTableText(dp1GPM, MDPlayer.getGoldPerMin().toString(), Gravity.CENTER, 3, 3, 3, row1d);
                                    TextView dp1XPM = new TextView(getContext());
                                    setTableText(dp1XPM, MDPlayer.getXpPerMin().toString(), Gravity.CENTER, 3, 3, 3, row1d);
                                    TextView dp1LH = new TextView(getContext());
                                    setTableText(dp1LH, MDPlayer.getLastHits().toString(), Gravity.CENTER, 3, 3, 3, row1d);
                                    TextView dp1DN = new TextView(getContext());
                                    setTableText(dp1DN, MDPlayer.getDenies().toString(), Gravity.CENTER, 3, 3, 3, row1d);
                                    TextView dp1HDmg = new TextView(getContext());
                                    setTableText(dp1HDmg, MDPlayer.getHeroDamage().toString(), Gravity.CENTER, 3, 3, 3, row1d);
                                    TextView dp1TDmg = new TextView(getContext());
                                    setTableText(dp1TDmg, MDPlayer.getTowerDamage().toString(), Gravity.CENTER, 3, 3, 3, row1d);
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    DireHeroImageView1.setImageResource(heroId);
                                    direTable.addView(row1d);
                                } else if (MDPlayer.getPlayerSlot() == 129) {
                                    DireTeamHeroName2.setText("2. " + hero.getLocalizedName() + ":");
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    DireHeroImageView2.setImageResource(heroId);
                                } else if (MDPlayer.getPlayerSlot() == 130) {
                                    DireTeamHeroName3.setText("3. " + hero.getLocalizedName() + ":");
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    DireHeroImageView3.setImageResource(heroId);
                                } else if (MDPlayer.getPlayerSlot() == 131) {
                                    DireTeamHeroName4.setText("4. " + hero.getLocalizedName() + ":");
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    DireHeroImageView4.setImageResource(heroId);
                                } else if (MDPlayer.getPlayerSlot() == 132)
                                    DireTeamHeroName5.setText("5. " + hero.getLocalizedName() + ":");
                                final String heroName = hero.getName();
                                final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                DireHeroImageView5.setImageResource(heroId);
                            } else {
                                if (MDPlayer.getPlayerSlot() == 0) {
                                    RadiantTeamHeroName1.setText("1. " + hero.getLocalizedName() + ":");
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    RadiantHeroImageView1.setImageResource(heroId);
                                } else if (MDPlayer.getPlayerSlot() == 1) {
                                    RadiantTeamHeroName2.setText("2. " + hero.getLocalizedName() + ":");
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    RadiantHeroImageView2.setImageResource(heroId);
                                } else if (MDPlayer.getPlayerSlot() == 2) {
                                    RadiantTeamHeroName3.setText("3. " + hero.getLocalizedName() + ":");
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    RadiantHeroImageView3.setImageResource(heroId);
                                } else if (MDPlayer.getPlayerSlot() == 3) {
                                    RadiantTeamHeroName4.setText("4. " + hero.getLocalizedName() + ":");
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    RadiantHeroImageView4.setImageResource(heroId);
                                } else if (MDPlayer.getPlayerSlot() == 4)
                                    RadiantTeamHeroName5.setText("5. " + hero.getLocalizedName() + ":");

                                final String heroName = hero.getName();
                                final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                RadiantHeroImageView5.setImageResource(heroId);

                            }
                        }
                    }
                }

                int radiantXp = 0;
                int direXp = 0;
                for (MDPlayer MDPlayer : result.getMDResult().getMDPlayers()) {
                    final int currXpm = MDPlayer.getXpPerMin();
                    if (MDPlayer.getPlayerSlot() <= 4) {
                        radiantXp += currXpm;
                    } else {
                        direXp += currXpm;
                    }
                }
                int radiantGpm = 0;
                int direGpm = 0;
                for (MDPlayer MDPlayer : result.getMDResult().getMDPlayers()) {
                    final int currGpm = MDPlayer.getGoldPerMin();
                    if (MDPlayer.getPlayerSlot() <= 4) {
                        radiantGpm += currGpm;
                    } else {
                        direGpm += currGpm;
                    }
                }
                int radiantGoldSpent = 0;
                int direGoldSpent = 0;
                for (MDPlayer MDPlayer : result.getMDResult().getMDPlayers()) {
                    final int currGoldSpent = MDPlayer.getGoldSpent();
                    if (MDPlayer.getPlayerSlot() <= 4) {
                        radiantGoldSpent += currGoldSpent;
                    } else {
                        direGoldSpent += currGoldSpent;
                    }
                }
                final MatchProgressView killProgress = inflateProgressView();
                killProgress.bindKills(result.getMDResult());
                mProgressContainer.addView(killProgress);

                final MatchProgressView xpProgress = inflateProgressView();
                xpProgress.bindXpmAverage(radiantXp, direXp);
                mProgressContainer.addView(xpProgress);

                final MatchProgressView GpmProgress = inflateProgressView();
                GpmProgress.bindGpmAverage(radiantGpm, direGpm);
                mProgressContainer.addView(GpmProgress);

                final MatchProgressView GoldSpentProgress = inflateProgressView();
                GoldSpentProgress.bindGoldSpent(radiantGoldSpent, direGoldSpent);
                mProgressContainer.addView(GoldSpentProgress);

            }

            @Override
            public void onFailure(Call<MDMatchDetails> call, Throwable t) {
                Log.d("result", "fail", t);

            }

        });

    }

    private MatchProgressView inflateProgressView() {
        final LayoutInflater inflater = LayoutInflater.from(getContext());
        return (MatchProgressView) inflater.inflate(R.layout.progressbar, mProgressContainer, false);
    }

    public interface OnFragmentInteractionListener {
    }

}