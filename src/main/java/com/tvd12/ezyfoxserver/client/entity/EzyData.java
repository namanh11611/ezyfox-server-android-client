package com.tvd12.ezyfoxserver.client.entity;

import java.io.Serializable;

public interface EzyData extends Cloneable, Serializable {

    /**
     * Clone to new object.
     *
     * @return a new object
     * @throws CloneNotSupportedException if can't clone
     * @see java.lang.Object#clone()
     */
    Object clone () throws CloneNotSupportedException;

    /**
     * duplicate new object.
     *
     * @return a duplicated object
     */
    EzyData duplicate ();
}
