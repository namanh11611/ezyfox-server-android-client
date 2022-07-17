package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzyByteBufferMessageReader;
import com.tvd12.ezyfoxserver.client.exception.EzyMaxRequestSizeException;
import com.tvd12.test.assertion.AssertApplier;
import com.tvd12.test.assertion.Asserts;

import org.junit.Test;

import java.nio.ByteBuffer;

public class EzyByteBufferMessageReaderTest {

    @Test
    public void test() {
        EzyByteBufferMessageReader reader = new EzyByteBufferMessageReader();
        ByteBuffer buffer = ByteBuffer.allocate(12);
        buffer.put((byte) 0);
        buffer.putShort((short) 9);
        buffer.put("012345678".getBytes());
        buffer.flip();
        Asserts.assertTrue(reader.readHeader(buffer));
        Asserts.assertTrue(reader.readSize(buffer, 128));
        Asserts.assertTrue(reader.readContent(buffer));
        Asserts.assertEquals(reader.get().getSize(), 9);
        Asserts.assertFalse(reader.readHeader(buffer));
        Asserts.assertFalse(reader.readSize(buffer, 128));
        Asserts.assertFalse(reader.readContent(buffer));
    }

    @Test
    public void test2() {
        EzyByteBufferMessageReader reader = new EzyByteBufferMessageReader();
        ByteBuffer buffer = ByteBuffer.allocate(12);
        byte header = (byte) 0;
        header |= (1) & 0xFF;
        buffer.put(header);
        buffer.putInt(7);
        buffer.put("0123456".getBytes());
        buffer.flip();
        Asserts.assertTrue(reader.readHeader(buffer));
        Asserts.assertTrue(reader.readSize(buffer, 128));
    }

    @Test
    public void test3() {
        // given
        final EzyByteBufferMessageReader reader = new EzyByteBufferMessageReader();
        final ByteBuffer buffer = ByteBuffer.allocate(12);
        byte header = (byte) 0;
        header |= (1) & 0xFF;
        buffer.put(header);
        buffer.putInt(7);
        buffer.put("0123456".getBytes());
        buffer.flip();
        reader.readHeader(buffer);

        // when
        Throwable e = Asserts.assertThrows(new AssertApplier() {
            @Override
            public void apply () {
                reader.readSize(buffer, 1);
            }
        });

        // then
        Asserts.assertEqualsType(e, EzyMaxRequestSizeException.class);
    }

    @Test
    public void testRawBytes() {
        EzyByteBufferMessageReader reader = new EzyByteBufferMessageReader();
        ByteBuffer buffer = ByteBuffer.allocate(12);
        byte header = (byte) 0;
        header |= (1) & 0xFF;
        header |= (1 << 4) & 0xFF;
        buffer.put(header);
        buffer.putInt(7);
        buffer.put("0123456".getBytes());
        buffer.flip();
        reader.readHeader(buffer);
        reader.readSize(buffer, 128);
        reader.readContent(buffer);
    }
}
