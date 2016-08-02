package com.example.jacobkoger.dota2application.util.filter;

import java.util.List;

/**
 * An interface definition used to determine how to filter a {@link List}
 *
 * @param <T> The type of {@link List} to filter
 */
public interface Predicate<T> {

    /**
     * Used to determine which items to filter
     *
     * @param type The type of data to filter against
     * @return True if we should add an item to our filtered results, false otherwise
     */
    boolean filter(T type);

}
