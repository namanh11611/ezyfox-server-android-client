package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzyObjectDeserializer;
import com.tvd12.ezyfoxserver.client.io.EzyStrings;

import org.junit.Test;

import java.nio.ByteBuffer;

@SuppressWarnings("unchecked")
public class EzyObjectDeserializerTest {

    @Test
    public void test() {
        ExEzyObjectDeserializer deserializer = new ExEzyObjectDeserializer();
        ByteBuffer buffer = ByteBuffer.allocate(1);
        assert deserializer.read(buffer) == buffer;
        assert deserializer.read(new byte[]{1}) != null;
        assert deserializer.deserialize(new byte[]{1}) != null;
        assert deserializer.deserialize("abc") != null;
    }

    public static class ExEzyObjectDeserializer implements EzyObjectDeserializer {

        @Override
        public <T> T deserialize(ByteBuffer buffer) {
            return (T) buffer;
        }

        @Override
        public <T> T deserialize(byte[] data) {
            return deserialize(ByteBuffer.wrap(data));
        }

        @Override
        public <T> T deserialize(String text) {
            return deserialize(EzyStrings.getUtfBytes(text));
        }

        @Override
        public <T> T read(ByteBuffer buffer) {
            return deserialize(buffer);
        }

        @Override
        public <T> T read(byte[] buffer) {
            return read(ByteBuffer.wrap(buffer));
        }
    }
}
