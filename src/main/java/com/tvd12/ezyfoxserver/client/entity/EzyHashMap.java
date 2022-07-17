package com.tvd12.ezyfoxserver.client.entity;

import com.tvd12.ezyfoxserver.client.io.EzyInputTransformer;
import com.tvd12.ezyfoxserver.client.io.EzyOutputTransformer;
import com.tvd12.ezyfoxserver.client.util.EzyObjectToMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@SuppressWarnings({"unchecked", "rawtypes"})
public class EzyHashMap extends EzyTransformable implements EzyObject {
    private static final long serialVersionUID = 2273868568933801751L;

    protected final HashMap<Object, Object> map = new HashMap<>();

    public EzyHashMap (
        EzyInputTransformer inputTransformer,
        EzyOutputTransformer outputTransformer
    ) {
        super(inputTransformer, outputTransformer);
    }

    public EzyHashMap (
        Map map,
        EzyInputTransformer inputTransformer,
        EzyOutputTransformer outputTransformer
    ) {
        this(inputTransformer, outputTransformer);
        this.map.putAll(map);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyObject#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public <V> V put (Object key, Object value) {
        return (V) map.put(key, transformInput(value));
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyObject#putAll(java.util.Map)
     */
    @Override
    public void putAll (Map m) {
        for (Object key : m.keySet())
            put(key, m.get(key));
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoObject#get(java.lang.Object, java.lang.Class)
     */
    @Override
    public <V> V get (Object key, Class<V> type) {
        return (V) getValue(key, type);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoObject#getValue(java.lang.Object, java.lang.Class)
     */
    @Override
    public Object getValue (Object key, Class type) {
        return transformOutput(map.get(key), type);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyObject#remove(java.lang.Object)
     */
    @Override
    public <V> V remove (Object key) {
        return (V) map.remove(key);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoObject#size()
     */
    @Override
    public int size () {
        return map.size();
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoObject#isEmpty()
     */
    @Override
    public boolean isEmpty () {
        return map.isEmpty();
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoObject#containsKey(java.lang.Object)
     */
    @Override
    public boolean containsKey (Object key) {
        return map.containsKey(key);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoObject#isNotNullKey(java.lang.Object)
     */
    @Override
    public boolean isNotNullValue (Object key) {
        return map.get(key) != null;
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoObject#get(java.lang.Object)
     */
    @Override
    public <V> V get (Object key) {
        return (V) map.get(key);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoObject#getWithDefault(java.lang.Object, java.lang.Object)
     */
    @Override
    public <V> V getWithDefault (Object key, V def) {
        return containsKey(key) ? (V) get(key) : def;
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoObject#get(java.lang.Object, java.lang.Class, java.lang.Object)
     */
    @Override
    public <V> V get (Object key, Class<V> type, V def) {
        return containsKey(key) ? get(key, type) : def;
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoObject#getValue(java.lang.Object, java.lang.Class, java.lang.Object)
     */
    @Override
    public Object getValue (Object key, Class type, Object def) {
        return containsKey(key) ? getValue(key, type) : def;
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoObject#keySet()
     */
    @Override
    public Set<Object> keySet () {
        return map.keySet();
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoObject#entrySet()
     */
    @Override
    public Set<Entry<Object, Object>> entrySet () {
        return map.entrySet();
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyObject#clear()
     */
    @Override
    public void clear () {
        map.clear();
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoObject#toMap()
     */
    @Override
    public Map toMap () {
        EzyObjectToMap objectToMap = EzyObjectToMap.getInstance();
        return objectToMap.toMap(this);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Object clone () throws CloneNotSupportedException {
        return new EzyHashMap(
            map,
            inputTransformer,
            outputTransformer
        );
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyObject#duplicate()
     */
    @Override
    public EzyObject duplicate () {
        try {
            return (EzyObject) clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }

    private Object transformInput (Object input) {
        return inputTransformer.transform(input);
    }

    private Object transformOutput (Object output, Class type) {
        return outputTransformer.transform(output, type);
    }

    @Override
    public String toString () {
        return map.toString();
    }
}