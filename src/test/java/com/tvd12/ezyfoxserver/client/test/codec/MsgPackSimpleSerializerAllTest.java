package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.MsgPackConstant;
import com.tvd12.ezyfoxserver.client.codec.MsgPackSimpleDeserializer;
import com.tvd12.ezyfoxserver.client.codec.MsgPackSimpleSerializer;
import com.tvd12.ezyfoxserver.client.entity.EzyArray;
import com.tvd12.ezyfoxserver.client.entity.EzyObject;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;
import com.tvd12.test.assertion.Asserts;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class MsgPackSimpleSerializerAllTest extends CodecBaseTest {

    private final MsgPackSimpleSerializer serializer
        = new MsgPackSimpleSerializer();
    private final MsgPackSimpleDeserializer deserializer
        = new MsgPackSimpleDeserializer();

    public static void main(String[] args) {
        new MsgPackSimpleSerializerAllTest().test9();
    }

    @Test
    public void test() {
        Boolean[] value = new Boolean[]{true, false, true};
        byte[] bytes = serializer.serialize(value);
        Asserts.assertEquals(bytes, new byte[]{(byte) (0x90 | 3), (byte) 0xc3, (byte) 0xc2, (byte) 0xc3});
        EzyArray array = deserializer.deserialize(bytes);
        Asserts.assertEquals(value, array.toArray(Boolean.class));
    }

    @Test
    public void test1() {
        byte[] bytes = serializer.serialize(new boolean[]{true, false, true});
        EzyArray array = deserializer.deserialize(bytes);
        Asserts.assertEquals(array.toArray(boolean.class), new boolean[]{true, false, true});

        bytes = serializer.serialize(new char[]{'1', 'b', 'c'});
        byte[] bytes1 = deserializer.deserialize(bytes);
        Asserts.assertEquals(bytes1, new byte[]{'1', 'b', 'c'});

        bytes = serializer.serialize(new double[]{1D, 2D, 3D});
        array = deserializer.deserialize(bytes);
        Asserts.assertEquals(array.toArray(double.class), new double[]{1D, 2D, 3D});

        bytes = serializer.serialize(new float[]{1F, 2F, 3F});
        array = deserializer.deserialize(bytes);
        Asserts.assertEquals(array.toArray(float.class), new float[]{1F, 2F, 3F});

        bytes = serializer.serialize(new int[]{1, 2, 3});
        array = deserializer.deserialize(bytes);
        Asserts.assertEquals(array.toArray(int.class), new int[]{1, 2, 3});

        bytes = serializer.serialize(new long[]{1L, 2L, 3L});
        array = deserializer.deserialize(bytes);
        Asserts.assertEquals(array.toArray(long.class), new long[]{1L, 2L, 3L});

        bytes = serializer.serialize(new short[]{(short) 1, (short) 2, (short) 3});
        array = deserializer.deserialize(bytes);
        Asserts.assertEquals(array.toArray(short.class), new short[]{(short) 1, (short) 2, (short) 3});

        bytes = serializer.serialize(new String[]{"1", "2", "3"});
        array = deserializer.deserialize(bytes);
        Asserts.assertEquals(array.toArray(String.class), new String[]{"1", "2", "3"});

        EzyObject obj = EzyEntityFactory.newObjectBuilder()
            .append("a", new BigInteger("12"))
            .append("b", new BigDecimal("3.45"))
            .append("c", UUID.randomUUID())
            .build();
        assert obj.get("ff", UUID.class) == null;
        bytes = serializer.serialize(obj);
        obj = deserializer.deserialize(bytes);
        assert obj.get("a", BigInteger.class).equals(new BigInteger("12"));
        assert obj.get("b", BigDecimal.class).equals(new BigDecimal("3.45"));
        System.out.println(obj.get("c", UUID.class));
    }

    @Test
    public void test2() {
        byte[] bytes = serializer.serialize(new Boolean[]{true, false, true});
        EzyArray array = deserializer.deserialize(bytes);
        Asserts.assertEquals(array.toArray(Boolean.class), new Boolean[]{true, false, true});

        bytes = serializer.serialize(new Byte[]{'1', 'b', 'c'});
        byte[] bytes1 = deserializer.deserialize(bytes);
        Asserts.assertEquals(bytes1, new byte[]{'1', 'b', 'c'});

        bytes = serializer.serialize(new Character[]{'1', 'b', 'c'});
        byte[] bytes2 = deserializer.deserialize(bytes);
        Asserts.assertEquals(bytes2, new byte[]{'1', 'b', 'c'});

        bytes = serializer.serialize(new Double[]{1D, 2D, 3D});
        array = deserializer.deserialize(bytes);
        Asserts.assertEquals(array.toArray(double.class), new double[]{1D, 2D, 3D});

        bytes = serializer.serialize(new Float[]{1F, 2F, 3F});
        array = deserializer.deserialize(bytes);
        Asserts.assertEquals(array.toArray(float.class), new float[]{1F, 2F, 3F});

        bytes = serializer.serialize(new Integer[]{1, 2, 3});
        array = deserializer.deserialize(bytes);
        Asserts.assertEquals(array.toArray(int.class), new int[]{1, 2, 3});

        bytes = serializer.serialize(new Long[]{1L, 2L, 3L});
        array = deserializer.deserialize(bytes);
        Asserts.assertEquals(array.toArray(long.class), new long[]{1L, 2L, 3L});

        bytes = serializer.serialize(new Short[]{(short) 1, (short) 2, (short) 3});
        array = deserializer.deserialize(bytes);
        Asserts.assertEquals(array.toArray(short.class), new short[]{(short) 1, (short) 2, (short) 3});

        bytes = serializer.serialize(new String[]{"1", "2", "3"});
        array = deserializer.deserialize(bytes);
        Asserts.assertEquals(array.toArray(String.class), new String[]{"1", "2", "3"});
    }

    @Test
    public void test3() {
        byte[] bytes = serializer.serialize((byte) 12);
        Number number = deserializer.deserialize(bytes);
        Asserts.assertEquals(number.byteValue(), (byte) 12);

        bytes = serializer.serialize('a');
        number = deserializer.deserialize(bytes);
        Asserts.assertEquals((char) number.byteValue(), 'a');

        bytes = serializer.serialize((short) 10);
        number = deserializer.deserialize(bytes);
        Asserts.assertEquals(number.shortValue(), (short) 10);

        Map<String, Long> map = new HashMap<>();
        map.put("a", 1L);
        map.put("b", 2L);
        bytes = serializer.serialize(map);
        EzyObject obj = deserializer.deserialize(bytes);
        Asserts.assertEquals(obj.size(), 2);
        Asserts.assertEquals(obj.get("a", Long.class), 1L);
        Asserts.assertEquals(obj.get("b", Long.class), 2L);

        Collection<String> coll = Arrays.asList("a", "b", "c");
        bytes = serializer.serialize(coll);
        EzyArray arr = deserializer.deserialize(bytes);
        Asserts.assertEquals(arr.toList(String.class), Arrays.asList("a", "b", "c"), false);
    }

    @Test
    public void test4() {
        byte[] bytes = new byte[MsgPackConstant.MAX_BIN16_SIZE];
        bytes[123] = 5;
        byte[] bs1 = serializer.serialize(bytes);
        byte[] bs2 = deserializer.deserialize(bs1);
        Asserts.assertEquals(bs2, bytes);
    }

    @Test
    public void test5() {
        int[] ints = new int[MsgPackConstant.MAX_ARRAY16_SIZE + 1];
        ints[12] = 5;
        byte[] bs1 = serializer.serialize(ints);
        EzyArray array = deserializer.deserialize(bs1);
        Asserts.assertEquals(array.size(), ints.length);
        Asserts.assertEquals(array.get(12, int.class), 5);
        Asserts.assertEquals(array.toArray(int.class), ints);
        Asserts.assertEquals(array.toArray(int.class), ints);
    }

    @Test
    public void test6() {
        byte[] bytes = serializer.serialize(0);
        Asserts.assertEquals(bytes, new byte[]{0});
    }

    @Test
    public void test7() {
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < MsgPackConstant.MAX_MAP16_SIZE + 1; ++i) {
            map.put(String.valueOf(i), i);
        }
        byte[] bytes = serializer.serialize(map);
        EzyObject object = deserializer.deserialize(bytes);
        Asserts.assertEquals(object.size(), map.size());
        for (Object key : map.keySet()) {
            Asserts.assertEquals(object.get(key), map.get(key));
        }
    }

    @Test
    public void test8() {
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < MsgPackConstant.MAX_MAP16_SIZE; ++i) {
            map.put(String.valueOf(i), i);
        }
        byte[] bytes = serializer.serialize(map);
        EzyObject object = deserializer.deserialize(bytes);
        Asserts.assertEquals(object.size(), map.size());
        for (Object key : map.keySet()) {
            Asserts.assertEquals(object.get(key), map.get(key));
        }
    }

    @Test
    public void test9() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < MsgPackConstant.MAX_STR8_SIZE + 1; ++i) {
            builder.append(i);
        }
        String str = builder.toString();
        long startTime = System.currentTimeMillis();
        byte[] bs1 = serializer.serialize(str);
        String str2 = deserializer.deserialize(bs1);
        long endTime = System.currentTimeMillis();
        System.err.println("time = " + (endTime - startTime));
        Asserts.assertEquals(str2, str);
    }
}
