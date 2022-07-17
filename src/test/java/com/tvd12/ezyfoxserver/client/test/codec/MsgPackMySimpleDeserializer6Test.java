package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzyMessageSerializer;
import com.tvd12.ezyfoxserver.client.codec.MsgPackSimpleDeserializer;
import com.tvd12.ezyfoxserver.client.codec.MsgPackSimpleSerializer;
import com.tvd12.ezyfoxserver.client.entity.EzyArray;

import org.junit.Test;

import java.io.IOException;

public class MsgPackMySimpleDeserializer6Test extends MsgPackCodecTest {

    @Test
    public void test1() throws IOException {
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
