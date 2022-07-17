package com.tvd12.ezyfoxserver.client.entity;

import java.util.Collection;
import java.util.Iterator;

public interface EzyArray extends EzyRoArray {

    /**
     * Add item to array.
     *
     * @param <T>  the value type
     * @param item the item to add
     */
    <T> void add (T item);

    /**
     * Add items to array.
     *
     * @param <T>   the value type
     * @param items the items to add
     */
    @SuppressWarnings("unchecked")
    <T> void add (T... items);

    /**
     * Add items to array.
     *
     * @param items the items to add
     */
    void add (Collection<?> items);

    /**
     * Set value at the index.
     *
     * @param <T>   the value type
     * @param index the index
     * @param item  the item to set
     * @return the old value
     */
    <T> T set (int index, Object item);

    /**
     * Remove value at the index.
     *
     * @param <T>   the value type
     * @param index the index
     * @return the removed value
     */
    <T> T remove (int index);

    /**
     * the iterator.
     *
     * @return the iterator
     * @see java.util.List#iterator()
     */
    Iterator<Object> iterator ();

    /**
     * @see com.tvd12.ezyfoxserver.client.entity.EzyData#duplicate()
     */
    @Override
    EzyArray duplicate ();

}
