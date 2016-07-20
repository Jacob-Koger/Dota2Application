package com.example.jacobkoger.newdota2applicationwsidebar;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails.MDMatchDetails;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails.MDPlayer;

import java.io.IOException;

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
    public boolean didRadiantWin;
    String url = "https://api.steampowered.com";
    TextView MatchIDTextView, WinningTeamTextView, DireTeamDetailsTextView, RadiantTeamDetailsTextView;
    LinearLayout mProgressContainer, direDetails, radiantDetails;
    ImageButton OpenDireTeamDetailsButton, OpenRadiantTeamDetailsButton;
    Boolean isDireDetailsVisible = false, isRadiantDetailsVisible = false;

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
        return inflater.inflate(R.layout.fragment_display_match_details, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MatchIDTextView = (TextView) view.findViewById(R.id.MatchIDTextView);
        WinningTeamTextView = (TextView) view.findViewById(R.id.WinningTeamTextView);
        DireTeamDetailsTextView = (TextView) view.findViewById(R.id.direTeamDetailsTextView);
        RadiantTeamDetailsTextView = (TextView) view.findViewById(R.id.radiantTeamDetailsTextView);

        OpenDireTeamDetailsButton = (ImageButton) view.findViewById(R.id.openDireTeamDetails);
        OpenRadiantTeamDetailsButton = (ImageButton) view.findViewById(R.id.openRadiantTeamDetails);

        mProgressContainer = (LinearLayout) view.findViewById(R.id.progressbarLLContainer);
        direDetails = (LinearLayout) view.findViewById(R.id.direTeamDetailsView);
        radiantDetails = (LinearLayout) view.findViewById(R.id.radiantTeamDetailsView);

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
                    radiantDetails.setVisibility(View.VISIBLE);
                    isRadiantDetailsVisible = true;
                    isDireDetailsVisible = false;
                } else if (isRadiantDetailsVisible == true && isDireDetailsVisible == false) {
                    radiantDetails.setVisibility(View.GONE);
                    isRadiantDetailsVisible = false;
                    isDireDetailsVisible = false;
                } else if (isRadiantDetailsVisible == false && isDireDetailsVisible == true) {
                    direDetails.setVisibility(View.GONE);
                    radiantDetails.setVisibility(View.VISIBLE);
                    isRadiantDetailsVisible = true;
                    isDireDetailsVisible = false;
                }
            }
        });
    }

    private void setOnClickListenersDire() {
        OpenDireTeamDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDireDetailsVisible == false && isRadiantDetailsVisible == false) {
                    direDetails.setVisibility(View.VISIBLE);
                    isDireDetailsVisible = true;
                    isRadiantDetailsVisible = false;
                } else if (isDireDetailsVisible == true && isRadiantDetailsVisible == false) {
                    direDetails.setVisibility(View.GONE);
                    isDireDetailsVisible = false;
                    isRadiantDetailsVisible = false;
                } else if (isDireDetailsVisible == false && isRadiantDetailsVisible == true) {
                    radiantDetails.setVisibility(View.GONE);
                    direDetails.setVisibility(View.VISIBLE);
                    isDireDetailsVisible = true;
                    isRadiantDetailsVisible = false;
                }
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

        mAnimationSet.play(fadeInDTV).with(fadeInDButton).with(fadeInRButton).with(fadeInRTV).after(1000);

        mAnimationSet.start();
    }

    public void getResult() {
        final String matchId = getArguments().getString(KEY_MATCH_ID);
        Log.d("blah", matchId);

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
                MatchIDTextView.setText(result.getMDResult().getMatchId());
                didRadiantWin = result.getMDResult().getRadiantWin();
                if (didRadiantWin) {
                    WinningTeamTextView.setText(R.string.radiantWin);
                } else {
                    WinningTeamTextView.setText(R.string.direWin);
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
                Log.e("blah", "fail", t);
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