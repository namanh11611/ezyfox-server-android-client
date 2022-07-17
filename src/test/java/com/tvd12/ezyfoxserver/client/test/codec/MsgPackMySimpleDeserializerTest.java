package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.builder.EzyArrayBuilder;
import com.tvd12.ezyfoxserver.client.builder.EzyObjectBuilder;
import com.tvd12.ezyfoxserver.client.codec.EzyMessageSerializer;
import com.tvd12.ezyfoxserver.client.codec.MsgPackSimpleDeserializer;
import com.tvd12.ezyfoxserver.client.codec.MsgPackSimpleSerializer;
import com.tvd12.ezyfoxserver.client.entity.EzyArray;
import com.tvd12.ezyfoxserver.client.io.EzyInts;
import com.tvd12.ezyfoxserver.client.io.EzyLongs;
import com.tvd12.ezyfoxserver.client.io.EzyMath;
import com.tvd12.test.assertion.Asserts;
import com.tvd12.test.reflect.MethodInvoker;

import org.junit.Test;
import org.msgpack.MessagePack;

import java.io.IOException;
import java.util.Arrays;

public class MsgPackMySimpleDeserializerTest extends MsgPackCodecTest {

    @Test
    public void test1() throws IOException {
        EzyMessageSerializer serializer = new MsgPackSimpleSerializer();
        EzyObjectBuilder dataBuilder = newObjectBuilder()
            .append("a", 1)
            .append("b", 2)
            .append("c", (Integer) null)
            .append("d", false)
            .append("e", true)
            .append("f", new byte[]{'a', 'b', 'c'})
            .append("i", EzyInts.bin2int(6))
            .append("j", EzyInts.bin2int(12))
            .append("k", EzyInts.bin2int(18))
            .append("l", EzyLongs.bin2long(34))
            .append("m", -EzyInts.bin2int(6))
            .append("n", -EzyInts.bin2int(12))
            .append("o", -EzyInts.bin2int(18))
            .append("p", -EzyLongs.bin2long(34));
        EzyArray request = newArrayBuilder()
            .append(15)
            .append(26)
            .append("abcdef")
            .append(-1)
            .append(dataBuilder)
            .build();
        long start = System.currentTimeMillis();
        byte[] bytes = serializer.serialize(request);
        long end = System.currentTimeMillis() - start;
        System.out.println("serialize time elapse = " + end);
        MessagePack msgPack = new MessagePack();
        start = System.currentTimeMillis();
        msgPack.write(dataBuilder.build().toMap());
        end = System.currentTimeMillis() - start;
        System.out.println("msg serialize time elapse = " + end);
        MsgPackSimpleDeserializer deserializer = new MsgPackSimpleDeserializer();
        start = System.currentTimeMillis();
        EzyArray answer = deserializer.deserialize(bytes);
        end = System.currentTimeMillis() - start;
        System.out.println("deserialize time elapse = " + end);
        System.out.println("answer = " + answer);
        int appId = answer.get(0);
        int command = answer.get(1);
        String token = answer.get(2);
        assert appId == 15 : "deserialize error";
        assert command == 26 : "deserialize error";
        assert token.equals("abcdef") : "deserialize error";
        Asserts.assertEquals(token, "abcdef");
    }

    @Test
    public void binTest() {
        @SuppressWarnings("MismatchedReadAndWriteOfArray")
        byte[] bin8 = new byte[EzyMath.bin2int(1)];
        byte[] bin16 = new byte[EzyMath.bin2int(6)];
        byte[] bin32 = new byte[EzyMath.bin2int(17)];
        Arrays.fill(bin8, (byte) 'a');
        Arrays.fill(bin16, (byte) 'b');
        Arrays.fill(bin32, (byte) 'c');
        EzyMessageSerializer serializer = new MsgPackSimpleSerializer();
        EzyObjectBuilder dataBuilder = newObjectBuilder()
            .append("a", bin16)
            .append("b", bin16)
            .append("c", bin32);
        EzyArray request = newArrayBuilder()
            .append(15)
            .append(26)
            .append("abcdef")
            .append(dataBuilder)
            .build();
        byte[] bytes = serializer.serialize(request);
        MsgPackSimpleDeserializer deserializer = new MsgPackSimpleDeserializer();
        EzyArray answer = deserializer.deserialize(bytes);
        int appId = answer.get(0);
        int command = answer.get(1);
        String token = answer.get(2);
        assert appId == 15 : "deserialize error";
        assert command == 26 : "deserialize error";
        assert token.equals("abcdef") : "deserialize error";
        Asserts.assertEquals(token, "abcdef");
        try {
            MethodInvoker.create()
                .method("getDataType")
                .param(int.class, -1234)
                .object(deserializer)
                .invoke();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void binTest2() {
        byte[] bin8 = new byte[EzyMath.bin2int(1)];
        byte[] bin16 = new byte[EzyMath.bin2int(6)];
        byte[] bin32 = new byte[EzyMath.bin2int(17)];
        Arrays.fill(bin8, (byte) 'a');
        Arrays.fill(bin16, (byte) 'b');
        Arrays.fill(bin32, (byte) 'c');
        String str8 = new String(bin8);
        String str16 = new String(bin16);
        String str32 = new String(bin32);
        EzyMessageSerializer serializer = new MsgPackSimpleSerializer();
        EzyObjectBuilder dataBuilder = newObjectBuilder()
            .append("a", str8)
            .append("b", str16)
            .append("c", str32);
        EzyArray request = newArrayBuilder()
            .append(15)
            .append(26)
            .append("abcdef")
            .append(dataBuilder)
            .build();
        byte[] bytes = serializer.serialize(request);
        MsgPackSimpleDeserializer deserializer = new MsgPackSimpleDeserializer();
        EzyArray answer = deserializer.deserialize(bytes);
        int appId = answer.get(0);
        int command = answer.get(1);
        String token = answer.get(2);
        assert appId == 15 : "deserialize error";
        assert command == 26 : "deserialize error";
        assert token.equals("abcdef") : "deserialize error";
        Asserts.assertEquals(token, "abcdef");
    }

    @Test
    public void arrayTest() {
        int size = EzyMath.bin2int(14);
        EzyArrayBuilder builder = newArrayBuilder();
        for (int i = 0; i < size; ++i) {
            builder.append(i);
        }
        EzyMessageSerializer serializer = new MsgPackSimpleSerializer();
        byte[] bytes = serializer.serialize(builder.build());
        MsgPackSimpleDeserializer deserializer = new MsgPackSimpleDeserializer();
        EzyArray answer = deserializer.deserialize(bytes);
        Asserts.assertEquals(answer.get(size - 1), size - 1);
    }

    @Test
    public void arrayTest2() {
        int size = EzyMath.bin2int(16);
        EzyArrayBuilder builder = newArrayBuilder();
        for (int i = 0; i < size; ++i) {
            builder.append(i);
        }
        EzyMessageSerializer serializer = new MsgPackSimpleSerializer();
        byte[] bytes = serializer.serialize(builder.build());
        MsgPackSimpleDeserializer deserializer = new MsgPackSimpleDeserializer();
        EzyArray answer = deserializer.deserialize(bytes);
        Asserts.assertEquals(answer.get(size - 1), size - 1);
    }

    @Test
    public void arrayTest3() {
        EzyMessageSerializer serializer = new MsgPackSimpleSerializer();
        EzyArray request = newArrayBuilder()
            .append(-1)
            .build();
        byte[] bytes = serializer.serialize(request);
        MsgPackSimpleDeserializer deserializer = new MsgPackSimpleDeserializer();
        EzyArray answer = deserializer.deserialize(bytes);
        System.out.println("answer = " + answer);
    }
}
