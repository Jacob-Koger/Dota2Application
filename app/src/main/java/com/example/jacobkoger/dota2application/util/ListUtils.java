package com.example.jacobkoger.dota2application.util;

import com.example.jacobkoger.dota2application.util.filter.Predicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class ListUtils {

    /** This class is never initialized */
    private ListUtils() {
        throw new AssertionError("No instances");
    }

    public static boolean isEmpty(Collection<?> list) {
        return list == null || list.isEmpty();
    }

    public static <T> List<T> emptyIfNull(List<T> list) {
        return list == null ? Collections.<T>emptyList() : list;
    }

    public static <T extends Comparable<? super T>> List<T> sort(List<T> list) {
        Collections.sort(list);
        return list;
    }

    public static <T> List<T> filter(Iterable<T> target, Predicate<T> predicate) {
        final List<T> result = new ArrayList<>(0);
        for (T element : target) {
            if (predicate.filter(element)) {
                result.add(element);
            }
        }
        return result;
    }

}
