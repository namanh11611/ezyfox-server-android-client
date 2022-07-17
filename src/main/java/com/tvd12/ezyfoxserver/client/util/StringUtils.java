package com.tvd12.ezyfoxserver.client.util;

public final class StringUtils {

    private StringUtils () {}

    public static boolean isEmpty (String str) {
        if (str == null) {
            return true;
        }
        return str.isEmpty();
    }
}
