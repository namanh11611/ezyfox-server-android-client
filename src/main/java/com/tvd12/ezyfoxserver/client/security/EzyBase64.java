package com.tvd12.ezyfoxserver.client.security;

import android.util.Base64;

import com.tvd12.ezyfoxserver.client.io.EzyStrings;


public final class EzyBase64 {

    private EzyBase64 () {}

    public static byte[] encode (byte[] input) {
        return Base64.encode(input, Base64.NO_WRAP);
    }

    public static byte[] decode (byte[] input) {
        return Base64.decode(input, Base64.NO_WRAP);
    }

    public static byte[] decode (String input) {
        return Base64.decode(input, Base64.NO_WRAP);
    }

    public static byte[] encode (String input) {
        byte[] bytes = EzyStrings.getUtfBytes(input);
        return Base64.encode(bytes, Base64.NO_WRAP);
    }

    public static String encodeUtf (String input) {
        byte[] bytes = EzyStrings.getUtfBytes(input);
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    public static String decodeUtf (String input) {
        byte[] bytes = Base64.decode(input, Base64.NO_WRAP);
        return EzyStrings.newUtf(bytes);
    }

    public static String encode2utf (byte[] input) {
        return EzyStrings.newUtf(encode(input));
    }

    public static String decode2utf (byte[] input) {
        return EzyStrings.newUtf(decode(input));
    }
}
