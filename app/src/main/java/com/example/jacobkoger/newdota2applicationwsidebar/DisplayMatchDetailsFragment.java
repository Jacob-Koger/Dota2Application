package com.example.jacobkoger.newdota2applicationwsidebar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails.MatchDetails;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchDetails.Result;

import java.io.IOException;
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

    String url = "https://api.steampowered.com";
    private OnFragmentInteractionListener mListener;
    TextView DireKillsTextView, RadiantKillsTextView, MatchIDTextView;
    ProgressBar KillsProgressBar;
    private static final String TAG = DisplayMatchDetailsFragment.class.getSimpleName();
    private static final String KEY_MATCH_ID = TAG +":matchId";
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String matchId = getArguments().getString(KEY_MATCH_ID);
        Toast.makeText(getContext(), matchId, Toast.LENGTH_LONG).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display_match_details, container, false);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DireKillsTextView = (TextView)view.findViewById(R.id.direKillsTextView);
        RadiantKillsTextView = (TextView)view.findViewById(R.id.radiantKillsTextView);
        MatchIDTextView = (TextView)view.findViewById(R.id.MatchIDTextView);
        KillsProgressBar = (ProgressBar)view.findViewById(R.id.progressBarKills);
        getResult();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
    public void getResult() {
        final EditText matchInput = (EditText) getView().findViewById(R.id.editText);
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                final Request orig = chain.request();
                HttpUrl origUrl = orig.url();
                return chain.proceed(orig.newBuilder()
                        .url(origUrl.newBuilder().addQueryParameter("key",BuildConfig.API_KEY)
                                .build())
                        .build());
            }
        });
        Retrofit retrofit = new Retrofit.Builder().client(builder.build())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MatchDetailsInterface service = retrofit.create(MatchDetailsInterface.class);
        Call<MatchDetails> callMH = service.getMatchDetails(String.valueOf(matchInput));
        callMH.enqueue(new Callback<MatchDetails>() {
            @Override
            public void onResponse(Call<MatchDetails> call, Response<MatchDetails> response) {
            MatchDetails result = response.body();
            DireKillsTextView.setText("Dire Kills: " + result.getResult().getDireScore().toString());
            RadiantKillsTextView.setText("Radiant Kills:" + result.getResult().getRadiantScore().toString());
            MatchIDTextView.setText(result.getResult().getMatchId().toString());
            }

            @Override
            public void onFailure(Call<MatchDetails> call, Throwable t) {

            }

        });
    }

}