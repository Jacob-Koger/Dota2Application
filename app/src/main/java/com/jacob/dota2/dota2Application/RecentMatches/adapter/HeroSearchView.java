package com.jacob.dota2.dota2Application.RecentMatches.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.jacob.dota2.dota2Application.util.HeroUtils;

import static com.jacob.dota2.dota2Application.util.Utils.requireNonNull;

/**
 * A {@link SearchView} used to autocomplete our hero names
 */
public class HeroSearchView extends SearchView implements AdapterView.OnItemClickListener {
    /**
     * The {@link AppCompatAutoCompleteTextView} to auto complete our hero names
     */
    private final SearchAutoComplete mAutoComplete;
    /**
     * Used to listen for a hero name to be clicked from our dropdown
     */
    private OnHeroSuggestionListener mListener;

    /**
     * Constructor for {@code HeroSearchView}
     *
     * @param context The {@link Context} to use
     */
    public HeroSearchView(Context context) {
        super(context);
        mAutoComplete = (SearchAutoComplete)
                findViewById(android.support.v7.appcompat.R.id.search_src_text);
        mAutoComplete.setOnItemClickListener(this);
        mAutoComplete.setThreshold(1);
    }

    /**
     * Used to bind our autocomplete adapter
     *
     * @param listener The {@link OnHeroSuggestionListener} listener to use
     */
    public void setAutoCompleteAdapter(OnHeroSuggestionListener listener) {
        final Context context = getContext();
        mAutoComplete.setAdapter(new ArrayAdapter<>(
                context, android.R.layout.simple_dropdown_item_1line, HeroUtils.getLocalizedNames(context)));
        mListener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final String heroName = (String) adapterView.getAdapter().getItem(i);
        mListener.onSuggestHero(requireNonNull(heroName, "hero name must not be null"));
    }

    /**
     * An interface definition used to callback when a hero name is clicked
     */
    public interface OnHeroSuggestionListener {
        /**
         * Called when a hero name is clicked
         *
         * @param heroName The hero name that was clicked
         */
        void onSuggestHero(@NonNull String heroName);
    }
}