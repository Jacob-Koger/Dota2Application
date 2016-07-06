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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails.MatchDetails;

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
    String url = "https://api.steampowered.com";
    TextView DireKillsTextView, RadiantKillsTextView, MatchIDTextView;
    ProgressBar KillsProgressBar;
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
        DireKillsTextView = (TextView) view.findViewById(R.id.direKillsTextView);
        RadiantKillsTextView = (TextView) view.findViewById(R.id.radiantKillsTextView);
        MatchIDTextView = (TextView) view.findViewById(R.id.MatchIDTextView);
        KillsProgressBar = (ProgressBar) view.findViewById(R.id.progressBarKills);
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

        Retrofit retrofit = new Retrofit.Builder()
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
                DireKillsTextView.setText("Dire Kills: " + result.getResult().getDireScore());
                RadiantKillsTextView.setText("Radiant Kills:" + result.getResult().getRadiantScore());
                MatchIDTextView.setText(result.getResult().getMatchId());
            }

            @Override
            public void onFailure(Call<MatchDetails> call, Throwable t) {
                Log.e("blah", "fail", t);
            }

        });

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}