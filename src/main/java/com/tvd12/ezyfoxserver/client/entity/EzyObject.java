package com.tvd12.ezyfoxserver.client.entity;

import java.util.Map;

/**
 * Support to transport data between objects.
 */

public interface EzyObject extends EzyRoObject {

    /**
     * @param <V>   the value type
     * @param key   the key
     * @param value the value
     * @return the old value
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    <V> V put (Object key, Object value);

    /**
     * @param m the map value
     * @see java.util.Map#putAll(java.util.Map)
     */
    @SuppressWarnings("rawtypes")
    void putAll (Map m);

    /**
     * @param <V> the value type
     * @param key the key
     * @return the removed value
     * @see java.util.Map#remove(java.lang.Object)
     */
    <V> V remove (Object key);

    /**
     * @see java.util.Map#clear()
     */
    void clear ();

    /**
     * @see com.tvd12.ezyfoxserver.client.entity.EzyData#duplicate()
     */
    @Override
    EzyObject duplicate ();
}
