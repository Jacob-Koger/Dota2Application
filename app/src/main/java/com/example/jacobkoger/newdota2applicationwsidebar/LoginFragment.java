package com.example.jacobkoger.newdota2applicationwsidebar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class LoginFragment extends android.support.v4.app.Fragment {
    public static final String WEBSITE = "Dota2Application";
    WebView LoginWebView;
    String userID = "";

    public LoginFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
    }

    private void setupView(View view) {
        LoginWebView = (WebView) view.findViewById(R.id.webView);
        LoginWebView.getSettings().setJavaScriptEnabled(true);
        LoginWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Uri Url = Uri.parse(url);

                if (Url.getAuthority().equals(WEBSITE.toLowerCase())) {
                    LoginWebView.stopLoading();

                    Uri userAccountUrl = Uri.parse(Url.getQueryParameter("openid.identity"));
                    userID = userAccountUrl.getLastPathSegment();

                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("player_id", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("player_id", userID);
                    editor.apply();
                    AlarmManager alm = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                    alm.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, PendingIntent.getActivity(getContext(), 0, new Intent(getContext(), MainActivity.class), 0));
                    Process.killProcess(Process.myPid());
                }
            }
        });

        String url = "https://steamcommunity.com/openid/login?openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select" +
                "&openid.identity=http://specs.openid.net/auth/2.0/identifier_select" +
                "&openid.mode=checkid_setup" +
                "&openid.ns=http://specs.openid.net/auth/2.0" +
                "&openid.realm=https://" + WEBSITE +
                "&openid.return_to=https://" + WEBSITE + "";
        LoginWebView.loadUrl(url);
    }
}