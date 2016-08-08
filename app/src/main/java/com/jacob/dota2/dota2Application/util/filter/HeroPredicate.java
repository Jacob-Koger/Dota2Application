package com.jacob.dota2.dota2Application.util.filter;

import com.jacob.dota2.dota2Application.data.hero.Hero;
import com.jacob.dota2.dota2Application.data.history.MHMatch;
import com.jacob.dota2.dota2Application.data.history.MHPlayer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.jacob.dota2.dota2Application.util.ListUtils.emptyIfNull;

/**
 * A {@link Predicate} used to determine if a search query matches a hero's name
 */
public class HeroPredicate implements Predicate<MHMatch> {

    /** Our heroes */
    private final Collection<Hero> mHeroes;
    /** The query parameter to check against */
    private final Collection<String> mQueries;
    /**
     * Constructor for HeroPredicate
     *
     * @param heroes  Our heroes
     * @param queries The query parameter to check against
     */
    public HeroPredicate(List<Hero> heroes, String... queries) {
        mHeroes = emptyIfNull(heroes);
        mQueries = emptyIfNull(Arrays.asList(queries));
    }

    @Override
    public boolean filter(MHMatch type) {
        for (final String query : mQueries) {
            for (MHPlayer player : emptyIfNull(type.getPlayers())) {
                for (Hero hero : mHeroes) {
                    if (player.getHeroId() == hero.getId()) {
                        if (hero.getLocalizedName().equalsIgnoreCase(query)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
