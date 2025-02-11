package com.tvd12.ezyfoxserver.client.util;

public class EzyBoolsIterator extends EzyArrayIterator<Boolean> {

    private final boolean[] array;

    public EzyBoolsIterator (boolean[] array) {
        this.array = array;
    }

    public static EzyBoolsIterator wrap (boolean[] array) {
        return new EzyBoolsIterator(array);
    }

    @Override
    protected int getLength () {
        return array.length;
    }

    @Override
    protected Boolean getItem (int index) {
        return array[index];
    }
}
