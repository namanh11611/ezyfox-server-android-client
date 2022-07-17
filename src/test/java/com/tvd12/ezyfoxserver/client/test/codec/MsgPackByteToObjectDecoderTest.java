package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzyMessage;
import com.tvd12.ezyfoxserver.client.codec.EzyMessageDeserializer;
import com.tvd12.ezyfoxserver.client.codec.MsgPackByteToObjectDecoder;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import static org.mockito.Mockito.mock;

import org.junit.Test;

public class MsgPackByteToObjectDecoderTest {

    @Test
    public void test() throws Exception {
        EzyMessageDeserializer deserializer = mock(EzyMessageDeserializer.class);
        MsgPackByteToObjectDecoder decoder = new MsgPackByteToObjectDecoder(deserializer, 128);
        ByteBuffer buffer = ByteBuffer.allocate(12);
        buffer.put((byte) 0);
        buffer.putShort((short) 9);
        buffer.put("012345678".getBytes());
        buffer.flip();
        Queue<EzyMessage> out = new LinkedList<>();
        decoder.decode(buffer, out);
        decoder.decode(Objects.requireNonNull(out.poll()));
    }
}
