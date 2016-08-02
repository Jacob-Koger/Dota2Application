package com.example.jacobkoger.dota2application.util;

import android.content.Context;

import com.example.jacobkoger.dota2application.data.hero.Hero;
import com.example.jacobkoger.dota2application.data.hero.HeroesList;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JacobKoger on 8/2/16.
 */
public class HeroUtils {
    /**
     * Hero related helpers
     */
    /**
     * This class is never initialized
     */
    private HeroUtils() {
        throw new AssertionError("No instances");
    }

    /**
     * Used to return our hardcoded array of heroes
     *
     * @param context The {@link Context} to use
     * @return Our hardcoded array of heroes
     */
    public static List<Hero> getHeroes(Context context) {
        final List<Hero> heroes = new ArrayList<>(0);
        try (InputStream in = context.getAssets().open("Heroes.json")) {
            final HeroesList hl = new Gson().fromJson(new InputStreamReader(in), HeroesList.class);
            heroes.addAll(hl.getHeroes());
        } catch (IOException ignored) {
            // Nothing to do
        }
        return heroes;
    }

    /**
     * Used to return a {@link List} of our localized hero names for autocompletion
     *
     * @param context The {@link Context} to use
     * @return A {@link List} of our localized hero names for autocompletion
     */
    public static List<String> getLocalizedNames(Context context) {
        final List<Hero> heroes = getHeroes(context);
        final List<String> heroNames = new ArrayList<>(heroes.size());
        for (final Hero hero : getHeroes(context)) {
            heroNames.add(hero.getLocalizedName());
        }
        return heroNames;
    }
}

