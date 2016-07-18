package com.example.jacobkoger.newdota2applicationwsidebar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails.MatchDetails;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails.Player;

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
    TextView MatchIDTextView, WinningTeamTextView;
    LinearLayout mProgressContainer;

    private OnFragmentInteractionListener mListener;

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
        mProgressContainer = (LinearLayout) view.findViewById(R.id.progressbarLLContainer);
        getResult();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        Call<MatchDetails> callMH = service.getMatchDetails(matchId);
        callMH.enqueue(new Callback<MatchDetails>() {
            @Override
            public void onResponse(Call<MatchDetails> call, Response<MatchDetails> response) {
                MatchDetails result = response.body();
                MatchIDTextView.setText(result.getResult().getMatchId());
                didRadiantWin = result.getResult().getRadiantWin();
                if (didRadiantWin) {
                    WinningTeamTextView.setText(R.string.radiantWin);
                } else {
                    WinningTeamTextView.setText(R.string.direWin);
                }

                int radiantXp = 0;
                int direXp = 0;
                for (Player player : result.getResult().getPlayers()) {
                    final int currXpm = player.getXpPerMin();
                    if (player.getPlayerSlot() <= 4) {
                        radiantXp += currXpm;
                    } else {
                        direXp += currXpm;
                    }
                }
                int radiantGpm = 0;
                int direGpm = 0;
                for (Player player : result.getResult().getPlayers()) {
                    final int currGpm = player.getGoldPerMin();
                    if (player.getPlayerSlot() <= 4) {
                        radiantGpm += currGpm;
                    } else {
                        direGpm += currGpm;
                    }
                }
                int radiantGoldSpent = 0;
                int direGoldSpent = 0;
                for (Player player : result.getResult().getPlayers()) {
                    final int currGoldSpent = player.getGoldSpent();
                    if (player.getPlayerSlot() <= 4) {
                        radiantGoldSpent += currGoldSpent;
                    } else {
                        direGoldSpent += currGoldSpent;
                    }
                }
                final MatchProgressView killProgress = inflateProgressView();
                killProgress.bindKills(result.getResult());
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
            public void onFailure(Call<MatchDetails> call, Throwable t) {
                Log.e("blah", "fail", t);
            }

        });

    }

    private MatchProgressView inflateProgressView() {
        final LayoutInflater inflater = LayoutInflater.from(getContext());
        return (MatchProgressView) inflater.inflate(R.layout.progressbar, mProgressContainer, false);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}