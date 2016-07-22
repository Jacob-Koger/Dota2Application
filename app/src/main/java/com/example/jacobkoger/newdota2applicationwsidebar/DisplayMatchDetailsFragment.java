package com.example.jacobkoger.newdota2applicationwsidebar;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    TextView MatchIDTextView, WinningTeamTextView, DireTeamDetailsTextView, RadiantTeamDetailsTextView,
            DireTeamHeroName1, DireTeamHeroName2, DireTeamHeroName3, DireTeamHeroName4, DireTeamHeroName5,
            RadiantTeamHeroName1, RadiantTeamHeroName2, RadiantTeamHeroName3, RadiantTeamHeroName4, RadiantTeamHeroName5,
            Dire1GPM, Dire2GPM, Dire3GPM, Dire4GPM, Dire5GPM,
            Dire1XPM, Dire2XPM, Dire3XPM, Dire4XPM, Dire5XPM,
            Dire1HDMG, Dire2HDMG, Dire3HDMG, Dire4HDMG, Dire5HDMG,
            Dire1TDMG, Dire2TDMG, Dire3TDMG, Dire4TDMG, Dire5TDMG,
            Radiant1GPM, Radiant2GPM, Radiant3GPM, Radiant4GPM, Radiant5GPM,
            Radiant1XPM, Radiant2XPM, Radiant3XPM, Radiant4XPM, Radiant5XPM,
            Radiant1HDMG, Radiant2HDMG, Radiant3HDMG, Radiant4HDMG, Radiant5HDMG,
            Radiant1TDMG, Radiant2TDMG, Radiant3TDMG, Radiant4TDMG, Radiant5TDMG,
            Dire1Level, Dire2Level, Dire3Level, Dire4Level, Dire5Level,
            Dire1Kills, Dire2Kills, Dire3Kills, Dire4Kills, Dire5Kills,
            Dire1Deaths, Dire2Deaths, Dire3Deaths, Dire4Deaths, Dire5Deaths,
            Dire1Assists, Dire2Assists, Dire3Assists, Dire4Assists, Dire5Assists,
            Radiant1Level, Radiant2Level, Radiant3Level, Radiant4Level, Radiant5Level,
            Radiant1Kills, Radiant2Kills, Radiant3Kills, Radiant4Kills, Radiant5Kills,
            Radiant1Deaths, Radiant2Deaths, Radiant3Deaths, Radiant4Deaths, Radiant5Deaths,
            Radiant1Assists, Radiant2Assists, Radiant3Assists, Radiant4Assists, Radiant5Assists;
    LinearLayout mProgressContainer;
    ImageButton OpenDireTeamDetailsButton, OpenRadiantTeamDetailsButton;
    ImageView DireHeroImageView1, DireHeroImageView2, DireHeroImageView3, DireHeroImageView4, DireHeroImageView5,
            RadiantHeroImageView1, RadiantHeroImageView2, RadiantHeroImageView3, RadiantHeroImageView4, RadiantHeroImageView5;
    Boolean isDireDetailsVisible = false, isRadiantDetailsVisible = false;
    RelativeLayout fullRadiantDetails, fullDireDetails;

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

        Dire1Level = (TextView) view.findViewById(R.id.dire1level);
        Dire2Level = (TextView) view.findViewById(R.id.dire2level);
        Dire3Level = (TextView) view.findViewById(R.id.dire3level);
        Dire4Level = (TextView) view.findViewById(R.id.dire4level);
        Dire5Level = (TextView) view.findViewById(R.id.dire5level);

        Radiant1Level = (TextView) view.findViewById(R.id.radiant1level);
        Radiant2Level = (TextView) view.findViewById(R.id.radiant2level);
        Radiant3Level = (TextView) view.findViewById(R.id.radiant3level);
        Radiant4Level = (TextView) view.findViewById(R.id.radiant4level);
        Radiant5Level = (TextView) view.findViewById(R.id.radiant5level);

        Dire1GPM = (TextView) view.findViewById(R.id.dire1gpm);
        Dire2GPM = (TextView) view.findViewById(R.id.dire2gpm);
        Dire3GPM = (TextView) view.findViewById(R.id.dire3gpm);
        Dire4GPM = (TextView) view.findViewById(R.id.dire4gpm);
        Dire5GPM = (TextView) view.findViewById(R.id.dire5gpm);

        Dire1XPM = (TextView) view.findViewById(R.id.dire1xpm);
        Dire2XPM = (TextView) view.findViewById(R.id.dire2xpm);
        Dire3XPM = (TextView) view.findViewById(R.id.dire3xpm);
        Dire4XPM = (TextView) view.findViewById(R.id.dire4xpm);
        Dire5XPM = (TextView) view.findViewById(R.id.dire5xpm);

        Dire1TDMG = (TextView) view.findViewById(R.id.dire1tdmg);
        Dire2TDMG = (TextView) view.findViewById(R.id.dire2tdmg);
        Dire3TDMG = (TextView) view.findViewById(R.id.dire3tdmg);
        Dire4TDMG = (TextView) view.findViewById(R.id.dire4tdmg);
        Dire5TDMG = (TextView) view.findViewById(R.id.dire5tdmg);

        Dire1HDMG = (TextView) view.findViewById(R.id.dire1hdmg);
        Dire2HDMG = (TextView) view.findViewById(R.id.dire2hdmg);
        Dire3HDMG = (TextView) view.findViewById(R.id.dire3hdmg);
        Dire4HDMG = (TextView) view.findViewById(R.id.dire4hdmg);
        Dire5HDMG = (TextView) view.findViewById(R.id.dire5hdmg);

        Radiant1GPM = (TextView) view.findViewById(R.id.radiant1gpm);
        Radiant2GPM = (TextView) view.findViewById(R.id.radiant2gpm);
        Radiant3GPM = (TextView) view.findViewById(R.id.radiant3gpm);
        Radiant4GPM = (TextView) view.findViewById(R.id.radiant4gpm);
        Radiant5GPM = (TextView) view.findViewById(R.id.radiant5gpm);

        Radiant1XPM = (TextView) view.findViewById(R.id.radiant1xpm);
        Radiant2XPM = (TextView) view.findViewById(R.id.radiant2xpm);
        Radiant3XPM = (TextView) view.findViewById(R.id.radiant3xpm);
        Radiant4XPM = (TextView) view.findViewById(R.id.radiant4xpm);
        Radiant5XPM = (TextView) view.findViewById(R.id.radiant5xpm);

        Radiant1TDMG = (TextView) view.findViewById(R.id.radiant1tdmg);
        Radiant2TDMG = (TextView) view.findViewById(R.id.radiant2tdmg);
        Radiant3TDMG = (TextView) view.findViewById(R.id.radiant3tdmg);
        Radiant4TDMG = (TextView) view.findViewById(R.id.radiant4tdmg);
        Radiant5TDMG = (TextView) view.findViewById(R.id.radiant5tdmg);

        Radiant1HDMG = (TextView) view.findViewById(R.id.radiant1hdmg);
        Radiant2HDMG = (TextView) view.findViewById(R.id.radiant2hdmg);
        Radiant3HDMG = (TextView) view.findViewById(R.id.radiant3hdmg);
        Radiant4HDMG = (TextView) view.findViewById(R.id.radiant4hdmg);
        Radiant5HDMG = (TextView) view.findViewById(R.id.radiant5hdmg);

        Dire1Kills = (TextView) view.findViewById(R.id.dire1kills);
        Dire2Kills = (TextView) view.findViewById(R.id.dire2kills);
        Dire3Kills = (TextView) view.findViewById(R.id.dire3kills);
        Dire4Kills = (TextView) view.findViewById(R.id.dire4kills);
        Dire5Kills = (TextView) view.findViewById(R.id.dire5kills);

        Dire1Deaths = (TextView) view.findViewById(R.id.dire1deaths);
        Dire2Deaths = (TextView) view.findViewById(R.id.dire2deaths);
        Dire3Deaths = (TextView) view.findViewById(R.id.dire3deaths);
        Dire4Deaths = (TextView) view.findViewById(R.id.dire4deaths);
        Dire5Deaths = (TextView) view.findViewById(R.id.dire5deaths);

        Dire1Assists = (TextView) view.findViewById(R.id.dire1assists);
        Dire2Assists = (TextView) view.findViewById(R.id.dire2assists);
        Dire3Assists = (TextView) view.findViewById(R.id.dire3assists);
        Dire4Assists = (TextView) view.findViewById(R.id.dire4assists);
        Dire5Assists = (TextView) view.findViewById(R.id.dire5assists);

        Radiant1Kills = (TextView) view.findViewById(R.id.radiant1kills);
        Radiant2Kills = (TextView) view.findViewById(R.id.radiant2kills);
        Radiant3Kills = (TextView) view.findViewById(R.id.radiant3kills);
        Radiant4Kills = (TextView) view.findViewById(R.id.radiant4kills);
        Radiant5Kills = (TextView) view.findViewById(R.id.radiant5kills);

        Radiant1Deaths = (TextView) view.findViewById(R.id.radiant1deaths);
        Radiant2Deaths = (TextView) view.findViewById(R.id.radiant2deaths);
        Radiant3Deaths = (TextView) view.findViewById(R.id.radiant3deaths);
        Radiant4Deaths = (TextView) view.findViewById(R.id.radiant4deaths);
        Radiant5Deaths = (TextView) view.findViewById(R.id.radiant5deaths);

        Radiant1Assists = (TextView) view.findViewById(R.id.radiant1assists);
        Radiant2Assists = (TextView) view.findViewById(R.id.radiant2assists);
        Radiant3Assists = (TextView) view.findViewById(R.id.radiant3assists);
        Radiant4Assists = (TextView) view.findViewById(R.id.radiant4assists);
        Radiant5Assists = (TextView) view.findViewById(R.id.radiant5assists);

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
        setOnClickListenersDire();
        setOnClickListenersRadiant();
        getResult();
        startAnim();
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

        ObjectAnimator fadeInDTV = ObjectAnimator.ofFloat(DireTeamDetailsTextView, "alpha", 0f, 1f);
        fadeInDTV.setDuration(1000);
        ObjectAnimator fadeInDButton = ObjectAnimator.ofFloat(OpenDireTeamDetailsButton, "alpha", 0f, 1f);
        fadeInDButton.setDuration(900);
        ObjectAnimator fadeInRTV = ObjectAnimator.ofFloat(RadiantTeamDetailsTextView, "alpha", 0f, 1f);
        fadeInRTV.setDuration(1000);
        ObjectAnimator fadeInRButton = ObjectAnimator.ofFloat(OpenRadiantTeamDetailsButton, "alpha", 0f, 1f);
        fadeInRButton.setDuration(900);

        final AnimatorSet mAnimationSet = new AnimatorSet();

        mAnimationSet.play(fadeInDTV).with(fadeInDButton).with(fadeInRButton).with(fadeInRTV).after(500);

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
//                    Log.d("result", MDPlayer.getHeroId().toString() + " " + MDPlayer.getPlayerSlot());
                    Log.d("players", String.valueOf(result.getMDResult().getMDPlayers().size()));

                    for (final Hero hero : mHeroes) {
//                        Log.d("result", MDPlayer.getHeroId().toString() + " " + MDPlayer.getPlayerSlot());
                        Log.d("Heros", hero.getId().toString());

                        if (MDPlayer.getHeroId() == hero.getId()) {
                            Log.d("result", MDPlayer.getHeroId().toString() + " " + MDPlayer.getPlayerSlot());

                            if (MDPlayer.getPlayerSlot() > 4) {
                                Log.d("playerSlot", MDPlayer.getPlayerSlot().toString());
                                if (MDPlayer.getPlayerSlot() == 128) {
                                    DireTeamHeroName1.setText("1. " + hero.getLocalizedName() + ":");
                                    Dire1Kills.setText(MDPlayer.getKills().toString());
                                    Dire1Deaths.setText(MDPlayer.getDeaths().toString());
                                    Dire1Assists.setText(MDPlayer.getAssists().toString());
                                    Dire1GPM.setText(MDPlayer.getGoldPerMin().toString());
                                    Dire1XPM.setText(MDPlayer.getXpPerMin().toString());
                                    Dire1Level.setText(MDPlayer.getLevel().toString());
                                    Dire1HDMG.setText(MDPlayer.getHeroDamage().toString());
                                    Dire1TDMG.setText(MDPlayer.getTowerDamage().toString());
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    DireHeroImageView1.setImageResource(heroId);

                                } else if (MDPlayer.getPlayerSlot() == 129) {
                                    DireTeamHeroName2.setText("2. " + hero.getLocalizedName() + ":");
                                    Dire2Kills.setText(MDPlayer.getKills().toString());
                                    Dire2Deaths.setText(MDPlayer.getDeaths().toString());
                                    Dire2Assists.setText(MDPlayer.getAssists().toString());
                                    Dire2GPM.setText(MDPlayer.getGoldPerMin().toString());
                                    Dire2XPM.setText(MDPlayer.getXpPerMin().toString());
                                    Dire2Level.setText(MDPlayer.getLevel().toString());
                                    Dire2HDMG.setText(MDPlayer.getHeroDamage().toString());
                                    Dire2TDMG.setText(MDPlayer.getTowerDamage().toString());
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    DireHeroImageView2.setImageResource(heroId);

                                } else if (MDPlayer.getPlayerSlot() == 130) {
                                    DireTeamHeroName3.setText("3. " + hero.getLocalizedName() + ":");
                                    Dire3Kills.setText(MDPlayer.getKills().toString());
                                    Dire3Deaths.setText(MDPlayer.getDeaths().toString());
                                    Dire3Assists.setText(MDPlayer.getAssists().toString());
                                    Dire3GPM.setText(MDPlayer.getGoldPerMin().toString());
                                    Dire3XPM.setText(MDPlayer.getXpPerMin().toString());
                                    Dire3Level.setText(MDPlayer.getLevel().toString());
                                    Dire3HDMG.setText(MDPlayer.getHeroDamage().toString());
                                    Dire3TDMG.setText(MDPlayer.getTowerDamage().toString());
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    DireHeroImageView3.setImageResource(heroId);
                                } else if (MDPlayer.getPlayerSlot() == 131) {
                                    DireTeamHeroName4.setText("4. " + hero.getLocalizedName() + ":");
                                    Dire4Kills.setText(MDPlayer.getKills().toString());
                                    Dire4Deaths.setText(MDPlayer.getDeaths().toString());
                                    Dire4Assists.setText(MDPlayer.getAssists().toString());
                                    Dire4GPM.setText(MDPlayer.getGoldPerMin().toString());
                                    Dire4XPM.setText(MDPlayer.getXpPerMin().toString());
                                    Dire4Level.setText(MDPlayer.getLevel().toString());
                                    Dire4HDMG.setText(MDPlayer.getHeroDamage().toString());
                                    Dire4TDMG.setText(MDPlayer.getTowerDamage().toString());
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    DireHeroImageView4.setImageResource(heroId);
                                } else if (MDPlayer.getPlayerSlot() == 132)
                                    DireTeamHeroName5.setText("5. " + hero.getLocalizedName() + ":");
                                Dire5Kills.setText(MDPlayer.getKills().toString());
                                Dire5Deaths.setText(MDPlayer.getDeaths().toString());
                                Dire5Assists.setText(MDPlayer.getAssists().toString());
                                Dire5GPM.setText(MDPlayer.getGoldPerMin().toString());
                                Dire5XPM.setText(MDPlayer.getXpPerMin().toString());
                                Dire5Level.setText(MDPlayer.getLevel().toString());
                                Dire5HDMG.setText(MDPlayer.getHeroDamage().toString());
                                Dire5TDMG.setText(MDPlayer.getTowerDamage().toString());
                                final String heroName = hero.getName();
                                final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                DireHeroImageView5.setImageResource(heroId);
                            } else {
                                if (MDPlayer.getPlayerSlot() == 0) {
                                    RadiantTeamHeroName1.setText("1. " + hero.getLocalizedName() + ":");
                                    Radiant1Kills.setText(MDPlayer.getKills().toString());
                                    Radiant1Deaths.setText(MDPlayer.getDeaths().toString());
                                    Radiant1Assists.setText(MDPlayer.getAssists().toString());
                                    Radiant1GPM.setText(MDPlayer.getGoldPerMin().toString());
                                    Radiant1XPM.setText(MDPlayer.getXpPerMin().toString());
                                    Radiant1Level.setText(MDPlayer.getLevel().toString());
                                    Radiant1HDMG.setText(MDPlayer.getHeroDamage().toString());
                                    Radiant1TDMG.setText(MDPlayer.getTowerDamage().toString());
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    RadiantHeroImageView1.setImageResource(heroId);
                                } else if (MDPlayer.getPlayerSlot() == 1) {
                                    RadiantTeamHeroName2.setText("2. " + hero.getLocalizedName() + ":");
                                    Radiant2Kills.setText(MDPlayer.getKills().toString());
                                    Radiant2Deaths.setText(MDPlayer.getDeaths().toString());
                                    Radiant2Assists.setText(MDPlayer.getAssists().toString());
                                    Radiant2GPM.setText(MDPlayer.getGoldPerMin().toString());
                                    Radiant2XPM.setText(MDPlayer.getXpPerMin().toString());
                                    Radiant2Level.setText(MDPlayer.getLevel().toString());
                                    Radiant2HDMG.setText(MDPlayer.getHeroDamage().toString());
                                    Radiant2TDMG.setText(MDPlayer.getTowerDamage().toString());
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    RadiantHeroImageView2.setImageResource(heroId);
                                } else if (MDPlayer.getPlayerSlot() == 2) {
                                    RadiantTeamHeroName3.setText("3. " + hero.getLocalizedName() + ":");
                                    Radiant3Kills.setText(MDPlayer.getKills().toString());
                                    Radiant3Deaths.setText(MDPlayer.getDeaths().toString());
                                    Radiant3Assists.setText(MDPlayer.getAssists().toString());
                                    Radiant3GPM.setText(MDPlayer.getGoldPerMin().toString());
                                    Radiant3XPM.setText(MDPlayer.getXpPerMin().toString());
                                    Radiant3Level.setText(MDPlayer.getLevel().toString());
                                    Radiant3HDMG.setText(MDPlayer.getHeroDamage().toString());
                                    Radiant3TDMG.setText(MDPlayer.getTowerDamage().toString());
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    RadiantHeroImageView3.setImageResource(heroId);
                                } else if (MDPlayer.getPlayerSlot() == 3) {
                                    RadiantTeamHeroName4.setText("4. " + hero.getLocalizedName() + ":");
                                    Radiant4Kills.setText(MDPlayer.getKills().toString());
                                    Radiant4Deaths.setText(MDPlayer.getDeaths().toString());
                                    Radiant4Assists.setText(MDPlayer.getAssists().toString());
                                    Radiant4GPM.setText(MDPlayer.getGoldPerMin().toString());
                                    Radiant4XPM.setText(MDPlayer.getXpPerMin().toString());
                                    Radiant4Level.setText(MDPlayer.getLevel().toString());
                                    Radiant4HDMG.setText(MDPlayer.getHeroDamage().toString());
                                    Radiant4TDMG.setText(MDPlayer.getTowerDamage().toString());
                                    final String heroName = hero.getName();
                                    final int heroId = res.getIdentifier(heroName, "drawable", getContext().getPackageName());
                                    RadiantHeroImageView4.setImageResource(heroId);
                                } else if (MDPlayer.getPlayerSlot() == 4)
                                    RadiantTeamHeroName5.setText("5. " + hero.getLocalizedName() + ":");
                                Radiant5Kills.setText(MDPlayer.getKills().toString());
                                Radiant5Deaths.setText(MDPlayer.getDeaths().toString());
                                Radiant5Assists.setText(MDPlayer.getAssists().toString());
                                Radiant5GPM.setText(MDPlayer.getGoldPerMin().toString());
                                Radiant5XPM.setText(MDPlayer.getXpPerMin().toString());
                                Radiant5Level.setText(MDPlayer.getLevel().toString());
                                Radiant5HDMG.setText(MDPlayer.getHeroDamage().toString());
                                Radiant5TDMG.setText(MDPlayer.getTowerDamage().toString());
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