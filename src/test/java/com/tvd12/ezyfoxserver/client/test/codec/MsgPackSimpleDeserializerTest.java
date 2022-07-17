package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.builder.EzyArrayBuilder;
import com.tvd12.ezyfoxserver.client.builder.EzyObjectBuilder;
import com.tvd12.ezyfoxserver.client.codec.MsgPackSimpleDeserializer;
import com.tvd12.ezyfoxserver.client.entity.EzyArray;
import com.tvd12.ezyfoxserver.client.io.EzyInts;
import com.tvd12.ezyfoxserver.client.io.EzyLongs;
import com.tvd12.ezyfoxserver.client.io.EzyMath;
import com.tvd12.test.assertion.Asserts;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class MsgPackSimpleDeserializerTest extends MsgPackCodecTest {

    @Test
    public void test1() throws IOException {
        EzyObjectBuilder dataBuilder = newObjectBuilder()
            .append("a", 1)
            .append("b", 2)
            .append("c", (Integer) null)
            .append("d", false)
            .append("e", true)
            .append("f", new byte[]{'a', 'b', 'c'})
            .append("g", 1) // fix uint
            .append("h", -10)
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
            .append(dataBuilder)
            .build();
        byte[] bytes = msgPack.write(request);
        MsgPackSimpleDeserializer deserializer = new MsgPackSimpleDeserializer();
        EzyArray answer = deserializer.deserialize(bytes);
        System.out.println("answer = " +  answer);
        int appId = answer.get(0);
        int command = answer.get(1);
        String token = answer.get(2);
        assert appId == 15 : "deserialize error";
        assert command == 26 : "deserialize error";
        assert token.equals("abcdef") : "deserialize error";
        Asserts.assertEquals(token, "abcdef");
    }

    @Test
    public void binTest() throws IOException {
        byte[] bin32 = new byte[EzyMath.bin2int(16)];
        Arrays.fill(bin32, (byte) 'c');
        String str32 = new String(bin32);
        EzyObjectBuilder dataBuilder = newObjectBuilder()
            .append("k", str32);
        EzyArray request = newArrayBuilder()
            .append(15)
            .append(26)
            .append("abcdef")
            .append(dataBuilder)
            .build();
        int arrsize = request.size();
        byte first = (byte) (arrsize | 0x90);
        byte[] bytes = msgPack.write(request);
        MsgPackSimpleDeserializer deserializer = new MsgPackSimpleDeserializer();
        EzyArray answer = deserializer.deserialize(bytes);
        int appId = answer.get(0);
        int command = answer.get(1);
        String token = answer.get(2);
        assert appId == 15 : "deserialize error " + first;
        assert command == 26 : "deserialize error";
        assert token.equals("abcdef") : "deserialize error";
        Asserts.assertEquals(token, "abcdef");
    }

    @Test
    public void arrayTest() throws IOException {
        int size = EzyMath.bin2int(14);
        EzyArrayBuilder builder = newArrayBuilder();
        for (int i = 0; i < size; ++i) {
            builder.append(i);
        }
        byte[] bytes = msgPack.write(builder.build());
        MsgPackSimpleDeserializer deserializer = new MsgPackSimpleDeserializer();
        EzyArray answer = deserializer.deserialize(bytes);
        Asserts.assertEquals(answer.get(size - 1), size - 1);
    }

    @Test
    public void arrayTest2() throws IOException {
        int size = EzyMath.bin2int(16);
        EzyArrayBuilder builder = newArrayBuilder();
        for (int i = 0; i < size; ++i) {
            builder.append(i);
        }
        byte[] bytes = msgPack.write(builder.build());
        MsgPackSimpleDeserializer deserializer = new MsgPackSimpleDeserializer();
        EzyArray answer = deserializer.deserialize(bytes);
        Asserts.assertEquals(answer.get(size - 1), size - 1);
    }
}
