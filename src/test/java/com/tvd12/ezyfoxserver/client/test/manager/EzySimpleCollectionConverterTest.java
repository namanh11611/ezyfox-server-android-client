package com.tvd12.ezyfoxserver.client.test.manager;

import com.tvd12.ezyfoxserver.client.entity.EzyObject;
import com.tvd12.ezyfoxserver.client.io.EzyCollectionConverter;
import com.tvd12.ezyfoxserver.client.io.EzySingletonCollectionConverter;
import com.tvd12.ezyfoxserver.client.security.EzyBase64;
import com.tvd12.ezyfoxserver.client.test.CommonBaseTest;
import com.tvd12.test.assertion.AssertSupplier;
import com.tvd12.test.assertion.Asserts;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class EzySimpleCollectionConverterTest extends CommonBaseTest {

    private final EzyCollectionConverter collectionConverter;

    public EzySimpleCollectionConverterTest() {
        super();
        this.collectionConverter = EzySingletonCollectionConverter.getInstance();
    }

    @Test
    public void test() {
        Collection<Boolean> booleans = Arrays.asList(true, false, true);
        Asserts.assertEquals(collectionConverter.toArray(booleans, Boolean.class),
            new Boolean[]{true, false, true});

        Collection<Byte> bytes = Arrays.asList((byte) 1, (byte) 2, (byte) 3);
        Asserts.assertEquals(collectionConverter.toArray(bytes, Byte.class),
            new Byte[]{(byte) 1, (byte) 2, (byte) 3});

        Collection<Character> characters = Arrays.asList('a', 'b', 'c');
        Asserts.assertEquals(collectionConverter.toArray(characters, Character.class),
            new Character[]{'a', 'b', 'c'});

        Collection<Double> doubles = Arrays.asList(1D, 2D, 3D);
        Asserts.assertEquals(collectionConverter.toArray(doubles, Double.class),
            new Double[]{1D, 2D, 3D});

        Collection<Float> floats = Arrays.asList(1F, 2F, 3F);
        Asserts.assertEquals(collectionConverter.toArray(floats, Float.class),
            new Float[]{1F, 2F, 3F});

        Collection<Integer> integers = Arrays.asList(1, 2, 3);
        Asserts.assertEquals(collectionConverter.toArray(integers, Integer.class),
            new Integer[]{1, 2, 3});

        Collection<Long> longs = Arrays.asList(1L, 2L, 3L);
        Asserts.assertEquals(collectionConverter.toArray(longs, Long.class),
            new Long[]{1L, 2L, 3L});

        Collection<Short> shorts = Arrays.asList((short) 1, (short) 2, (short) 3);
        Asserts.assertEquals(collectionConverter.toArray(shorts, Short.class),
            new Short[]{(short) 1, (short) 2, (short) 3});

        Collection<String> strings = Arrays.asList("a", "b", "c");
        Asserts.assertEquals(collectionConverter.toArray(strings, String.class),
            new String[]{"a", "b", "c"});

        EzyObject object = newObjectBuilder().append("1", "a").build();
        Asserts.assertEquals(collectionConverter.toArray(Collections.singletonList(object), EzyObject.class),
            new Object[]{object});
    }

    @Test
    public void test1() {
        Collection<Boolean> booleans = Arrays.asList(true, false, true);
        Asserts.assertEquals(collectionConverter.toArray(booleans, boolean.class),
            new boolean[]{true, false, true});

        Collection<Byte> bytes = Arrays.asList((byte) 1, (byte) 2, (byte) 3);
        Asserts.assertEquals(collectionConverter.toArray(bytes, byte.class),
            new byte[]{(byte) 1, (byte) 2, (byte) 3});

        Collection<Character> characters = Arrays.asList('a', 'b', 'c');
        Asserts.assertEquals(collectionConverter.toArray(characters, char.class),
            new char[]{'a', 'b', 'c'});

        Collection<Double> doubles = Arrays.asList(1D, 2D, 3D);
        Asserts.assertEquals(collectionConverter.toArray(doubles, double.class),
            new double[]{1D, 2D, 3D});

        Collection<Float> floats = Arrays.asList(1F, 2F, 3F);
        Asserts.assertEquals(collectionConverter.toArray(floats, float.class),
            new float[]{1F, 2F, 3F});

        Collection<Integer> integers = Arrays.asList(1, 2, 3);
        Asserts.assertEquals(collectionConverter.toArray(integers, int.class),
            new int[]{1, 2, 3});

        Collection<Long> longs = Arrays.asList(1L, 2L, 3L);
        Asserts.assertEquals(collectionConverter.toArray(longs, long.class),
            new long[]{1L, 2L, 3L});

        Collection<Short> shorts = Arrays.asList((short) 1, (short) 2, (short) 3);
        Asserts.assertEquals(collectionConverter.toArray(shorts, short.class),
            new short[]{(short) 1, (short) 2, (short) 3});

        Collection<String> strings = Arrays.asList("a", "b", "c");
        Asserts.assertEquals(collectionConverter.toArray(strings, String.class),
            new String[]{"a", "b", "c"});
    }

    @Test
    public void test2() {
        Collection<Collection<Boolean>> booleanss = new ArrayList<>();
        booleanss.add(Arrays.asList(true, false, true));
        booleanss.add(Arrays.asList(false, true, false));
        Asserts.assertEquals(collectionConverter.toArray(booleanss, boolean[].class),
            new boolean[][]{{true, false, true}, {false, true, false}});

        Collection<Collection<Byte>> byteArrays = new ArrayList<>();
        byteArrays.add(Arrays.asList((byte) 1, (byte) 2, (byte) 3));
        byteArrays.add(Arrays.asList((byte) 4, (byte) 5, (byte) 6));
        Asserts.assertEquals(collectionConverter.toArray(byteArrays, byte[].class),
            new byte[][]{{(byte) 1, (byte) 2, (byte) 3}, {(byte) 4, (byte) 5, (byte) 6}});

        Collection<Collection<Character>> characterss = new ArrayList<>();
        characterss.add(Arrays.asList('a', 'b', 'c'));
        characterss.add(Arrays.asList('d', 'e', 'f'));
        Asserts.assertEquals(collectionConverter.toArray(characterss, char[].class),
            new char[][]{{'a', 'b', 'c'}, {'d', 'e', 'f'}});

        Collection<Collection<Double>> doubless = new ArrayList<>();
        doubless.add(Arrays.asList(1D, 2D, 3D));
        doubless.add(Arrays.asList(4D, 5D, 6D));
        Asserts.assertEquals(collectionConverter.toArray(doubless, double[].class),
            new double[][]{{1D, 2D, 3D}, {4D, 5D, 6D}});

        Collection<Collection<Float>> floatss = new ArrayList<>();
        floatss.add(Arrays.asList(1F, 2F, 3F));
        floatss.add(Arrays.asList(4F, 5F, 6F));
        Asserts.assertEquals(collectionConverter.toArray(floatss, float[].class),
            new float[][]{{1F, 2F, 3F}, {4F, 5F, 6F}});

        Collection<Collection<Integer>> integerss = new ArrayList<>();
        integerss.add(Arrays.asList(1, 2, 3));
        integerss.add(Arrays.asList(4, 5, 6));
        Asserts.assertEquals(collectionConverter.toArray(integerss, int[].class),
            new int[][]{{1, 2, 3}, {4, 5, 6}});

        Collection<Collection<Long>> longss = new ArrayList<>();
        longss.add(Arrays.asList(1L, 2L, 3L));
        longss.add(Arrays.asList(4L, 5L, 6L));
        Asserts.assertEquals(collectionConverter.toArray(longss, long[].class),
            new long[][]{{1L, 2L, 3L}, {4L, 5L, 6L}});

        Collection<Collection<Short>> shortss = new ArrayList<>();
        shortss.add(Arrays.asList((short) 1, (short) 2, (short) 3));
        shortss.add(Arrays.asList((short) 4, (short) 5, (short) 6));
        Asserts.assertEquals(collectionConverter.toArray(shortss, short[].class),
            new short[][]{{(short) 1, (short) 2, (short) 3}, {(short) 4, (short) 5, (short) 6}});

        Collection<Collection<String>> stringss = new ArrayList<>();
        stringss.add(Arrays.asList("a", "b", "c"));
        stringss.add(Arrays.asList("d", "e", "f"));
        Asserts.assertEquals(collectionConverter.toArray(stringss, String[].class),
            new String[][]{{"a", "b", "c"}, {"d", "e", "f"}});
    }


    @Test
    public void test3() {
        Collection<Collection<Boolean>> booleanss = new ArrayList<>();
        booleanss.add(Arrays.asList(true, false, true));
        booleanss.add(Arrays.asList(false, true, false));
        Asserts.assertEquals(collectionConverter.toArray(booleanss, Boolean[].class),
            new Boolean[][]{{true, false, true}, {false, true, false}});

        Collection<Collection<Byte>> byteArrays = new ArrayList<>();
        byteArrays.add(Arrays.asList((byte) 1, (byte) 2, (byte) 3));
        byteArrays.add(Arrays.asList((byte) 4, (byte) 5, (byte) 6));
        Asserts.assertEquals(collectionConverter.toArray(byteArrays, Byte[].class),
            new Byte[][]{{(byte) 1, (byte) 2, (byte) 3}, {(byte) 4, (byte) 5, (byte) 6}});

        Collection<Collection<Character>> characterss = new ArrayList<>();
        characterss.add(Arrays.asList('a', 'b', 'c'));
        characterss.add(Arrays.asList('d', 'e', 'f'));
        Asserts.assertEquals(collectionConverter.toArray(characterss, Character[].class),
            new Character[][]{{'a', 'b', 'c'}, {'d', 'e', 'f'}});

        Collection<Collection<Double>> doubless = new ArrayList<>();
        doubless.add(Arrays.asList(1D, 2D, 3D));
        doubless.add(Arrays.asList(4D, 5D, 6D));
        Asserts.assertEquals(collectionConverter.toArray(doubless, Double[].class),
            new Double[][]{{1D, 2D, 3D}, {4D, 5D, 6D}});

        Collection<Collection<Float>> floatss = new ArrayList<>();
        floatss.add(Arrays.asList(1F, 2F, 3F));
        floatss.add(Arrays.asList(4F, 5F, 6F));
        Asserts.assertEquals(collectionConverter.toArray(floatss, Float[].class),
            new Float[][]{{1F, 2F, 3F}, {4F, 5F, 6F}});

        Collection<Collection<Integer>> integerss = new ArrayList<>();
        integerss.add(Arrays.asList(1, 2, 3));
        integerss.add(Arrays.asList(4, 5, 6));
        Asserts.assertEquals(collectionConverter.toArray(integerss, Integer[].class),
            new Integer[][]{{1, 2, 3}, {4, 5, 6}});

        Collection<Collection<Long>> longss = new ArrayList<>();
        longss.add(Arrays.asList(1L, 2L, 3L));
        longss.add(Arrays.asList(4L, 5L, 6L));
        Asserts.assertEquals(collectionConverter.toArray(longss, Long[].class),
            new Long[][]{{1L, 2L, 3L}, {4L, 5L, 6L}});

        Collection<Collection<Short>> shortss = new ArrayList<>();
        shortss.add(Arrays.asList((short) 1, (short) 2, (short) 3));
        shortss.add(Arrays.asList((short) 4, (short) 5, (short) 6));
        Asserts.assertEquals(collectionConverter.toArray(shortss, Short[].class),
            new Short[][]{{(short) 1, (short) 2, (short) 3}, {(short) 4, (short) 5, (short) 6}});

        Collection<Collection<String>> stringss = new ArrayList<>();
        stringss.add(Arrays.asList("a", "b", "c"));
        stringss.add(Arrays.asList("d", "e", "f"));
        Asserts.assertEquals(collectionConverter.toArray(stringss, String[].class),
            new String[][]{{"a", "b", "c"}, {"d", "e", "f"}});
    }

    @Test
    public void test4() {
        try {
            Asserts.assertEquals(
                collectionConverter.toArray(
                    Arrays.asList(
                        EzyBase64.encode2utf(new byte[]{1, 2, 3}),
                        EzyBase64.encode2utf(new byte[]{3, 4, 5})),
                        Byte[].class
                    ),
                    new Byte[][]{{1, 2, 3}, {3, 4, 5}}
            );
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        Asserts.assertEquals(collectionConverter.toArray(
                Arrays.asList(
                    new byte[]{1, 2, 3},
                    new byte[]{3, 4, 5}),
                Byte[].class),
            new Byte[][]{{1, 2, 3}, {3, 4, 5}});
    }

    @Test
    public void test6() {
        Asserts.assertThat(
            new AssertSupplier<Object>() {
                @Override
                public Object apply () {
                    return EzySingletonCollectionConverter
                        .getInstance()
                        .toArray(Collections.emptyList(), Void.class);
                }
            }
        ).willThrows(IllegalArgumentException.class);
    }
}
