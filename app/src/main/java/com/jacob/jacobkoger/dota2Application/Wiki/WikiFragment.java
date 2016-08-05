package com.jacob.jacobkoger.dota2Application.Wiki;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.jacobkoger.dota2Application.R;
import com.jacob.jacobkoger.dota2Application.activities.MainActivity;


public class WikiFragment extends Fragment {
    WebView WikiWebView;
    private String BaseUrl = "http://www.dota2.com/hero/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wiki, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String orig_hero = getArguments().getString("hero");
        ((MainActivity) getActivity()).setActionBarTitle(orig_hero);
        String hero = orig_hero.replace(" ", "_");
        WikiWebView = (WebView) view.findViewById(R.id.wikiWebView);
        WikiWebView.getSettings().setJavaScriptEnabled(true);
        WikiWebView.setInitialScale(150);
        WikiWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WikiWebView.loadUrl(BaseUrl + hero);

    }
}
