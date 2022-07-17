package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.builder.EzyArrayBuilder;
import com.tvd12.ezyfoxserver.client.codec.EzyMessageSerializer;
import com.tvd12.ezyfoxserver.client.codec.MsgPackSimpleDeserializer;
import com.tvd12.ezyfoxserver.client.codec.MsgPackSimpleSerializer;
import com.tvd12.ezyfoxserver.client.entity.EzyArray;
import com.tvd12.ezyfoxserver.client.io.EzyMath;
import com.tvd12.test.assertion.Asserts;

import org.junit.Test;

import java.io.IOException;

public class MsgPackMySimpleDeserializer5Test extends MsgPackCodecTest {

    @Test
    public void test1() throws IOException {
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
}
