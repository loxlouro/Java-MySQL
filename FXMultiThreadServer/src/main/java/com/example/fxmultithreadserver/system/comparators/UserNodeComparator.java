package com.example.fxmultithreadserver.system.comparators;

import com.example.fxmultithreadserver.controllers.User;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class UserNodeComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return Integer.valueOf(o1.getWorker_id())-Integer.valueOf(o2.getWorker_id());
    }

    @Override
    public Comparator<User> reversed() {
        return Comparator.super.reversed();
    }

    @Override
    public Comparator<User> thenComparing(Comparator<? super User> other) {
        return Comparator.super.thenComparing(other);
    }

    @Override
    public <U> Comparator<User> thenComparing(Function<? super User, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return Comparator.super.thenComparing(keyExtractor, keyComparator);
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<User> thenComparing(Function<? super User, ? extends U> keyExtractor) {
        return Comparator.super.thenComparing(keyExtractor);
    }

    @Override
    public Comparator<User> thenComparingInt(ToIntFunction<? super User> keyExtractor) {
        return Comparator.super.thenComparingInt(keyExtractor);
    }

    @Override
    public Comparator<User> thenComparingLong(ToLongFunction<? super User> keyExtractor) {
        return Comparator.super.thenComparingLong(keyExtractor);
    }

    @Override
    public Comparator<User> thenComparingDouble(ToDoubleFunction<? super User> keyExtractor) {
        return Comparator.super.thenComparingDouble(keyExtractor);
    }
}
