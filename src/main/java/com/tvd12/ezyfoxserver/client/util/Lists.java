package com.tvd12.ezyfoxserver.client.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Lists {

    private Lists () {}

    @SafeVarargs
    public static <T> List<T> newArrayList (T... items) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, items);
        return list;
    }

}
