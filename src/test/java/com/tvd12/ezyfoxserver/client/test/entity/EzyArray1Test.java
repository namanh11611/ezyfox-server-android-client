package com.tvd12.ezyfoxserver.client.test.entity;

import com.tvd12.ezyfoxserver.client.builder.EzyArrayBuilder;
import com.tvd12.ezyfoxserver.client.entity.EzyArray;
import com.tvd12.ezyfoxserver.client.entity.EzyArrayList;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;
import com.tvd12.ezyfoxserver.client.util.EzyDates;
import com.tvd12.test.assertion.AssertApplier;
import com.tvd12.test.assertion.Asserts;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

public class EzyArray1Test extends EzyEntityTest {

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    public void test() {
        EzyArrayBuilder builder = newArrayBuilder()
            .append(Boolean.TRUE)
            .append((byte) 1)
            .append('a')
            .append(2D)
            .append(3F)
            .append(4)
            .append(5L)
            .append((short) 6)
            .append("str");

        builder
            .append(Arrays.asList(true, false, true))
            .append(new byte[]{((byte) 1)})
            .append(new char[]{'a', 'b', 'c'})
            .append(Arrays.asList(1D, 2D, 3D))
            .append(Arrays.asList(4F, 5F, 6F))
            .append(Arrays.asList(6, 7, 8))
            .append(Arrays.asList(9L, 10L, 11L))
            .append(Collections.singletonList((short) 12))
            .append(Arrays.asList("1", "2", "3"));

        builder
            .append(EzyDates.parse("2017-05-30T00:00:00:000"));

        EzyArray array = builder.build();

        Asserts.assertEquals(array.get(0), Boolean.TRUE);
        Asserts.assertEquals(array.get(1), (byte) 1);
        Asserts.assertEquals(array.get(2), (byte) 'a');
        Asserts.assertEquals(array.get(3), 2D);
        Asserts.assertEquals(array.get(4), 3F);
        Asserts.assertEquals(array.get(5), 4);
        Asserts.assertEquals(array.get(6), 5L);
        Asserts.assertEquals(array.get(7), (short) 6);
        Asserts.assertEquals(array.get(8), "str");

        Asserts.assertEquals(array.get(0, boolean.class), Boolean.TRUE);
        Asserts.assertEquals(array.get(1, byte.class), (byte) 1);
        Asserts.assertEquals(array.get(2, char.class), 'a');
        Asserts.assertEquals(array.get(3, double.class), 2D);
        Asserts.assertEquals(array.get(4, float.class), 3F);
        Asserts.assertEquals(array.get(5, int.class), 4);
        Asserts.assertEquals(array.get(6, long.class), 5L);
        Asserts.assertEquals(array.get(7, short.class), (short) 6);
        Asserts.assertEquals(array.get(8, String.class), "str");

        Asserts.assertEquals(array.get(0, Boolean.class), Boolean.TRUE);
        Asserts.assertEquals(array.get(1, Byte.class), (byte) 1);
        Asserts.assertEquals(array.get(2, Character.class), 'a');
        Asserts.assertEquals(array.get(3, Double.class), 2D);
        Asserts.assertEquals(array.get(4, Float.class), 3F);
        Asserts.assertEquals(array.get(5, Integer.class), 4);
        Asserts.assertEquals(array.get(6, Long.class), 5L);
        Asserts.assertEquals(array.get(7, Short.class), (short) 6);
        Asserts.assertEquals(array.get(8, String.class), "str");

        Asserts.assertEquals(array.get(9, boolean[].class), new boolean[]{true, false, true});
        Asserts.assertEquals(array.get(10, byte[].class), new byte[]{1});
        Asserts.assertEquals(array.get(11, char[].class), new char[]{'a', 'b', 'c'});
        Asserts.assertEquals(array.get(12, double[].class), new double[]{1D, 2D, 3D});
        Asserts.assertEquals(array.get(13, float[].class), new float[]{4F, 5F, 6F});
        Asserts.assertEquals(array.get(14, int[].class), new int[]{6, 7, 8});
        Asserts.assertEquals(array.get(15, long[].class), new long[]{9L, 10L, 11L});
        Asserts.assertEquals(array.get(16, short[].class), new short[]{12});
        Asserts.assertEquals(array.get(17, String[].class), new String[]{"1", "2", "3"});

        Asserts.assertEquals(array.get(9, Boolean[].class), new Boolean[]{true, false, true});
        Asserts.assertEquals(array.get(10, byte[].class), new byte[]{1});
        Asserts.assertEquals(array.get(11, Character[].class), new Character[]{'a', 'b', 'c'});
        Asserts.assertEquals(array.get(12, Double[].class), new Double[]{1D, 2D, 3D});
        Asserts.assertEquals(array.get(13, Float[].class), new Float[]{4F, 5F, 6F});
        Asserts.assertEquals(array.get(14, Integer[].class), new Integer[]{6, 7, 8});
        Asserts.assertEquals(array.get(15, Long[].class), new Long[]{9L, 10L, 11L});
        Asserts.assertEquals(array.get(16, Short[].class), new Short[]{12});
        Asserts.assertEquals(array.get(17, String[].class), new String[]{"1", "2", "3"});

        Asserts.assertEquals(array.get(18, Date.class), EzyDates.parse("2017-05-30T00:00:00:000"));

        Asserts.assertTrue(((Collection) array.get(9)).containsAll(Arrays.asList(true, false, true)));
        assert array.size() > 0;
    }

    @Test
    public void test1() {
        EzyArrayBuilder builder = newArrayBuilder()
            .append("a", "b", "c");
        EzyArray array = builder.build();
        Asserts.assertEquals(array.size(), 3);
        Asserts.assertEquals(array.get(1), "b");
        array.set(1, "d");
        Asserts.assertEquals(array.get(1), "d");
        array.remove(1);
        Asserts.assertEquals(array.size(), 2);
        Asserts.assertEquals(array.get(1), "c");
        Asserts.assertTrue(array.iterator().hasNext());
        Asserts.assertEquals(array.toList().size(), 2);
        Asserts.assertEquals(array.toList(String.class).size(), 2);
        Asserts.assertEquals(array.toArray(String.class), new String[]{"a", "c"});
        EzyArray dup = array.duplicate();
        Asserts.assertEquals(array, dup);
        Asserts.assertTrue(dup.toList().containsAll(new HashSet<>(Arrays.asList("a", "c"))));
        EzyArray sub = array.sub(0, 1);
        Asserts.assertEquals(sub.size(), 1);
    }

    @Test
    public void test2() {
        final EzyArray array = new EzyArrayList(null, null, null) {
            private static final long serialVersionUID = 8714822620207886718L;

            @Override
            public Object clone() throws CloneNotSupportedException {
                throw new CloneNotSupportedException();
            }
        };
        Throwable e = Asserts.assertThrows(new AssertApplier() {
            @Override
            public void apply () {
                array.duplicate();
            }
        });
        Asserts.assertEqualsType(e, IllegalStateException.class);
    }

    @SuppressWarnings("ALL")
    @Test
    public void equalsAndHashCodeTest() {
        EzyArray a = EzyEntityFactory.newArrayBuilder()
            .append(1, 2, 3)
            .build();
        assert !a.equals(null);
        assert a.equals(a);
        assert !a.equals(new Object());
        EzyArray b = EzyEntityFactory.newArrayBuilder()
            .append(1, 2, 3)
            .build();
        Asserts.assertEquals(a, b);
        EzyArray c = EzyEntityFactory.newArrayBuilder()
            .append(1, 2)
            .build();
        assert !a.equals(c);
    }

    @SuppressWarnings("ALL")
    @Test
    public void compareToTest() {
        EzyArray a = EzyEntityFactory.newArrayBuilder()
            .append(1, 2, 3)
            .build();
        EzyArray b = EzyEntityFactory.newArrayBuilder()
            .append(1, 2, 3)
            .build();
        EzyArray c = EzyEntityFactory.newArrayBuilder()
            .append(1, 2)
            .build();
        EzyArray d = EzyEntityFactory.newArrayBuilder()
            .append(1, 1, 1)
            .build();
        EzyArray e = EzyEntityFactory.newArrayBuilder()
            .append(3, 2, 3)
            .build();
        EzyArray f = EzyEntityFactory.newArrayBuilder()
            .append(null, 2, 3)
            .build();
        EzyArray g = EzyEntityFactory.newArrayBuilder()
            .append(null, 2, 3)
            .build();
        EzyArray h = EzyEntityFactory.newArrayBuilder()
            .append(new Object(), 2, 3)
            .build();
    }

    public static class ClassA implements Cloneable {

        @Override
        public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }
    }
}
