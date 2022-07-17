package com.tvd12.ezyfoxserver.client.entity;

import com.tvd12.ezyfoxserver.client.io.EzyCollectionConverter;
import com.tvd12.ezyfoxserver.client.io.EzyInputTransformer;
import com.tvd12.ezyfoxserver.client.io.EzyOutputTransformer;
import com.tvd12.ezyfoxserver.client.util.EzyArrayToList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
public class EzyArrayList extends EzyTransformable implements EzyArray {
    private static final long serialVersionUID = 5952111146742741007L;

    protected final ArrayList<Object> list = new ArrayList<>();

    protected final transient EzyCollectionConverter collectionConverter;

    public EzyArrayList (
        EzyInputTransformer inputTransformer,
        EzyOutputTransformer outputTransformer,
        EzyCollectionConverter collectionConverter
    ) {
        super(inputTransformer, outputTransformer);
        this.collectionConverter = collectionConverter;
    }

    public EzyArrayList (
        Collection items,
        EzyInputTransformer inputTransformer,
        EzyOutputTransformer outputTransformer,
        EzyCollectionConverter collectionConverter
    ) {
        this(inputTransformer, outputTransformer, collectionConverter);
        this.list.addAll(items);

    }

    @Override
    public <T> T get (int index) {
        return (T) list.get(index);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoArray#get(int, java.lang.Class)
     */
    @Override
    public <T> T get (int index, Class<T> type) {
        return (T) getValue(index, type);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoArray#getValue(int, java.lang.Class)
     */
    @Override
    public Object getValue (int index, Class type) {
        return transformOutput(list.get(index), type);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoArray#getWithDefault(int, java.lang.Object)
     */
    public <T> T getWithDefault (int index, T def) {
        return size() > index ? (T) get(index) : def;
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoArray#get(int, java.lang.Class, java.lang.Object)
     */
    public <T> T get (int index, Class<T> type, T def) {
        return size() > index ? get(index, type) : def;
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoArray#getValue(int, java.lang.Class, java.lang.Object)
     */
    @SuppressWarnings("rawtypes")
    public Object getValue (int index, Class type, Object def) {
        return size() > index ? getValue(index, type) : def;
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoArray#isNotNullIndex(int)
     */
    @Override
    public boolean isNotNullValue (int index) {
        boolean answer = false;
        int size = size();
        if (index < size) {
            answer = list.get(index) != null;
        }
        return answer;
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoArray#sub(int, int)
     */
    @Override
    public EzyArray sub (int fromIndex, int toIndex) {
        List<Object> subList = list.subList(fromIndex, toIndex);
        return new EzyArrayList(
            subList,
            inputTransformer,
            outputTransformer,
            collectionConverter);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyArray#add(java.lang.Object[])
     */
    @Override
    public void add (Object... items) {
        for (Object item : items)
            this.add(item);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyArray#add(java.util.Collection)
     */
    @Override
    public void add (Collection items) {
        for (Object item : items)
            this.add(item);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoArray#size()
     */
    @Override
    public int size () {
        return list.size();
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoArray#isEmpty()
     */
    @Override
    public boolean isEmpty () {
        return list.isEmpty();
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyArray#set(int, java.lang.Object)
     */
    @Override
    public <T> T set (int index, Object item) {
        return (T) list.set(index, transformInput(item));
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyArray#remove(int)
     */
    @Override
    public <T> T remove (int index) {
        return (T) list.remove(index);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyArray#iterator()
     */
    @Override
    public Iterator<Object> iterator () {
        return list.iterator();
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoArray#toList()
     */
    @Override
    public List toList () {
        EzyArrayToList arrayToList = EzyArrayToList.getInstance();
        return arrayToList.toList(this);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoArray#toList(java.lang.Class)
     */
    @Override
    public <T> List<T> toList (Class<T> type) {
        return (List<T>) toList();
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyRoArray#toArray(java.lang.Class)
     */
    @Override
    public <T, A> A toArray (Class<T> type) {
        return collectionConverter.toArray(list, type);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Object clone () throws CloneNotSupportedException {
        Collection listClone = (Collection) list.clone();
        return new EzyArrayList(
            listClone,
            inputTransformer,
            outputTransformer,
            collectionConverter
        );
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfoxserver.client.entity.EzyArray#duplicate()
     */
    @Override
    public EzyArray duplicate () {
        try {
            return (EzyArray) clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * add an item to the list
     *
     * @param item the item
     */
    @Override
    public void add (Object item) {
        list.add(transformInput(item));
    }

    /**
     * Transform input value
     *
     * @param input the input value
     * @return the transformed value
     */
    protected Object transformInput (Object input) {
        return inputTransformer.transform(input);
    }

    /**
     * Transform output value
     *
     * @param output the output value
     * @param type   the output type
     * @return the transformed value
     */
    private Object transformOutput (Object output, Class type) {
        return outputTransformer.transform(output, type);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        return list.toString();
    }
}