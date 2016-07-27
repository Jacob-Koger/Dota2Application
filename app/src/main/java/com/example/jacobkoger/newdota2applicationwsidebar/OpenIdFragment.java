package com.example.jacobkoger.newdota2applicationwsidebar;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class OpenIdFragment extends Fragment {
    public static final String WEBSITE = "Dota2Application";
    String userID = "";
    WebView webView;
    private OnFragmentInteractionListener mListener;

    public OpenIdFragment() {
    }

    public static OpenIdFragment newInstance() {
        OpenIdFragment fragment = new OpenIdFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_open_id, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = (WebView) view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Uri Url = Uri.parse(url);

                if (Url.getAuthority().equals(WEBSITE.toLowerCase())) {
                    webView.stopLoading();

                    Uri userAccountUrl = Uri.parse(Url.getQueryParameter("openid.identity"));
                    userID = userAccountUrl.getLastPathSegment();

                    Toast.makeText(getContext(), userID, Toast.LENGTH_LONG).show();
                    Log.i("UserID", userID);

                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("special", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("player_id", userID);
                    editor.apply();
                }
            }
        });

        String url = "https://steamcommunity.com/openid/login?openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select" +
                "&openid.identity=http://specs.openid.net/auth/2.0/identifier_select" +
                "&openid.mode=checkid_setup" +
                "&openid.ns=http://specs.openid.net/auth/2.0" +
                "&openid.realm=https://" + WEBSITE +
                "&openid.return_to=https://" + WEBSITE + "";
        webView.loadUrl(url);
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
}
