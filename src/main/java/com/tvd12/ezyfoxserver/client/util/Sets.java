package com.tvd12.ezyfoxserver.client.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class Sets {

    private Sets () {}

    @SafeVarargs
    public static <E> Set<E> newHashSet (E... items) {
        Set<E> set = new HashSet<>();
        Collections.addAll(set, items);
        return set;
    }
}
