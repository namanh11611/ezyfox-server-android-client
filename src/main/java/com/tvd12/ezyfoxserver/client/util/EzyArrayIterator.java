package com.tvd12.ezyfoxserver.client.util;

import java.util.Iterator;

public abstract class EzyArrayIterator<E> implements Iterator<E> {

    private int rindex;

    @Override
    public boolean hasNext () {
        return rindex < getLength();
    }

    @Override
    public E next () {
        return getItem(rindex++);
    }

    protected abstract int getLength ();

    protected abstract E getItem (int index);

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
