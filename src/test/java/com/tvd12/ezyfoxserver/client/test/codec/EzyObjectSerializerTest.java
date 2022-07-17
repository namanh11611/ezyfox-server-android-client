package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzyObjectSerializer;

import org.junit.Test;

import java.nio.ByteBuffer;

public class EzyObjectSerializerTest {

    @Test
    public void test() {
        ExEzyObjectSerializer serializer = new ExEzyObjectSerializer();
        assert serializer.write("abc") != null;
    }

    public static class ExEzyObjectSerializer implements EzyObjectSerializer {

        public byte[] serialize(Object value) {
            return value.toString().getBytes();
        }

        @Override
        public ByteBuffer write (Object value) {
            // serialize value to byte array
            byte[] bytes = serialize(value);

            // wrap the byte array
            return ByteBuffer.wrap(bytes);
        }
    }
}
