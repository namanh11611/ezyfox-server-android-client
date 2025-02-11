package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.MsgPackSimpleSerializer;
import com.tvd12.ezyfoxserver.client.entity.EzyArray;
import com.tvd12.ezyfoxserver.client.entity.EzyObject;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;
import com.tvd12.test.assertion.Asserts;

import org.junit.Test;
import org.msgpack.MessagePack;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@SuppressWarnings({"rawtypes", "ConstantConditions", "NullableProblems"})
public class MsgPackSimpleSerializerTest {

    private final MessagePack messagePack = new MessagePack();
    private final MsgPackSimpleSerializer serializer = new MsgPackSimpleSerializer();

    @Test
    public void test() throws Exception {
        check(Boolean.TRUE);
        check((byte) 1);
        check('a');
        check(1.0D);
        check(1.0F);
        check(1L);
        check((short) 1);

        check(new boolean[]{true, false});
        assert serializer.serialize(new byte[]{1, 2, 3})[0] == ((byte) 0xc4);
        assert serializer.serialize(new char[]{'a', 'b', 'c'})[0] == ((byte) 0xc4);
        check(new double[]{1, 2, 3});
        check(new float[]{1, 2, 3});
        check(new int[]{1, 2, 3});
        check(new long[]{1, 2, 3});
        check(new short[]{1, 2, 3});
        check(new String[]{"a", "b", "c"});

        check(new Boolean[]{true, false});
        assert serializer.serialize(new Byte[]{1, 2, 3})[0] == ((byte) 0xc4);
        assert serializer.serialize(new Character[]{'a', 'b', 'c'})[0] == ((byte) 0xc4);
        check(new Double[]{1D, 2D, 3D});
        check(new Float[]{1F, 2F, 3F});
        check(new Integer[]{1, 2, 3});
        check(new Long[]{1L, 2L, 3L});
        check(new Short[]{1, 2, 3});

        check(Collections.singletonMap(1, 2));
        check(newEmptyMap());
        check(newEmptyAbstractMap());
        serializer.serialize(EzyEntityFactory.newArray());
        serializer.serialize(EzyEntityFactory.newObject());
        check(newEmptyCollection());
        check(newEmptyAbstractCollection());
        check(new HashSet<>(Arrays.asList(1, 2, 3)));
        check(newEmptySet());
        check(new CopyOnWriteArraySet<>());
        check(new CopyOnWriteArrayList<>());
        check(newEmptyAbstractList());
        check(newEmptyList());
        serializer.serialize(newEmptyObject());
        serializer.serialize(newEmptyArray());
        System.out.println(Arrays.toString(serializer.serialize(EzyEntityFactory.EMPTY_ARRAY)));
        System.out.println(Arrays.toString(serializer.serialize(EzyEntityFactory.EMPTY_OBJECT)));
    }

    private void check(Object input) throws Exception {
        byte[] actual = serializer.serialize(input);
        byte[] expected = messagePack.write(input);
        Asserts.assertEquals(actual, expected);
    }

    private EzyArray newEmptyArray() {
        return new EmptyEzyArray();
    }

    private EzyObject newEmptyObject() {
        return new EmptyEzyObject();
    }

    private AbstractList newEmptyAbstractList() {
        return new AbstractList() {

            @Override
            public Object get(int index) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }
        };
    }

    private List newEmptyList() {
        return new List() {

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator iterator() {
                return Collections.emptyIterator();
            }

            @Override
            public Object[] toArray() {
                return null;
            }

            @Override
            public Object[] toArray(Object[] a) {
                return null;
            }

            @Override
            public boolean add(Object e) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection c) {
                return false;
            }

            @Override
            public boolean addAll(Collection c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection c) {
                return false;
            }

            @Override
            public void clear() {}

            @Override
            public Object get(int index) {
                return null;
            }

            @Override
            public Object set(int index, Object element) {
                return null;
            }

            @Override
            public void add(int index, Object element) {}

            @Override
            public Object remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator listIterator() {
                return null;
            }

            @Override
            public ListIterator listIterator(int index) {
                return null;
            }

            @Override
            public List subList(int fromIndex, int toIndex) {
                return null;
            }
        };
    }

    private Set newEmptySet() {
        return new Set() {

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator iterator() {
                return Collections.emptyIterator();
            }

            @Override
            public Object[] toArray() {
                return null;
            }

            @Override
            public Object[] toArray(Object[] a) {
                return null;
            }

            @Override
            public boolean add(Object e) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection c) {
                return false;
            }

            @Override
            public boolean addAll(Collection c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection c) {
                return false;
            }

            @Override
            public void clear() {}
        };
    }

    private Collection newEmptyAbstractCollection() {
        return new AbstractCollection() {

            @Override
            public Iterator iterator() {
                return Collections.emptyIterator();
            }

            @Override
            public int size() {
                return 0;
            }
        };
    }

    private Collection newEmptyCollection() {
        return new Collection() {

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator iterator() {
                return Collections.emptyIterator();
            }

            @Override
            public Object[] toArray() {
                return null;
            }

            @Override
            public Object[] toArray(Object[] a) {
                return null;
            }

            @Override
            public boolean add(Object e) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection c) {
                return false;
            }

            @Override
            public boolean addAll(Collection c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection c) {
                return false;
            }

            @Override
            public void clear() {}
        };
    }

    private AbstractMap newEmptyAbstractMap() {
        return new AbstractMap() {

            @Override
            public Set entrySet() {
                return new HashSet<>();
            }

        };
    }

    private Map newEmptyMap() {
        return new Map() {

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public Object get(Object key) {
                return null;
            }

            @Override
            public Object put(Object key, Object value) {
                return null;
            }

            @Override
            public Object remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map m) {

            }

            @Override
            public void clear() {
            }

            @Override
            public Set keySet() {
                return null;
            }

            @Override
            public Collection values() {
                return null;
            }

            @Override
            public Set entrySet() {
                return new HashSet<>();
            }
        };
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public static class EmptyEzyArray implements EzyArray {
        private static final long serialVersionUID = 7904063160934793640L;

        @Override
        public <T> T get(int index) {
            return null;
        }

        @Override
        public <T> T get(int index, Class<T> type) {
            return null;
        }

        @Override
        public Object getValue(int index, Class type) {
            return null;
        }

        @Override
        public <T> T getWithDefault (int index, T def) {
            return null;
        }

        @Override
        public <T> T get (int index, Class<T> type, T def) {
            return null;
        }

        @Override
        public Object getValue (int index, Class type, Object def) {
            return null;
        }

        @Override
        public boolean isNotNullValue(int index) {
            return false;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty () {
            return false;
        }

        @Override
        public <T> List<T> toList() {
            return null;
        }

        @Override
        public <T> List<T> toList(Class<T> type) {
            return null;
        }

        @Override
        public <T, A> A toArray(Class<T> type) {
            return null;
        }

        @Override
        public void add(Object item) {
        }

        @Override
        public void add(Object... items) {
        }

        @Override
        public void add(Collection items) {
        }

        @Override
        public <T> T set(int index, Object item) {
            return null;
        }

        @Override
        public <T> T remove(int index) {
            return null;
        }

        @Override
        public EzyArray sub(int fromIndex, int toIndex) {
            return null;
        }

        @Override
        public Iterator<Object> iterator() {
            return Collections.emptyIterator();
        }

        @Override
        public EzyArray duplicate() {
            return null;
        }

        public Object clone() {
            return null;
        }
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public static class EmptyEzyObject implements EzyObject {
        private static final long serialVersionUID = 5658012267109087959L;

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty () {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean isNotNullValue(Object key) {
            return false;
        }

        @Override
        public <V> V get(Object key) {
            return null;
        }

        @Override
        public <V> V get(Object key, Class<V> type) {
            return null;
        }

        @Override
        public Object getValue(Object key, Class type) {
            return null;
        }

        @Override
        public <V> V getWithDefault (Object key, V def) {
            return null;
        }

        @Override
        public <V> V get (Object key, Class<V> type, V def) {
            return null;
        }

        @Override
        public Object getValue (Object key, Class type, Object def) {
            return null;
        }

        @Override
        public Set<Object> keySet() {
            return new HashSet<>();
        }

        @Override
        public Set<Entry<Object, Object>> entrySet() {
            return new HashSet<>();
        }

        @Override
        public Map toMap() {
            return null;
        }

        @Override
        public <V> V put(Object key, Object value) {
            return null;
        }

        @Override
        public void putAll(Map m) {}

        @Override
        public <V> V remove(Object key) {
            return null;
        }

        @Override
        public void clear() {}

        @Override
        public EzyObject duplicate() {
            return null;
        }

        public Object clone() {
            return null;
        }
    }
}
