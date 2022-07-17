package com.tvd12.ezyfoxserver.client.test.codec;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tvd12.ezyfoxserver.client.codec.EzyMessage;
import com.tvd12.ezyfoxserver.client.codec.EzyMessageHeader;
import com.tvd12.ezyfoxserver.client.codec.EzySimpleMessageToBytes;

import org.junit.Test;

public class EzySimpleMessageToBytesTest {

    @Test
    public void test() {
        EzySimpleMessageToBytes instance = new EzySimpleMessageToBytes();
        EzyMessage message = mock(EzyMessage.class);
        EzyMessageHeader header = mock(EzyMessageHeader.class);
        when(header.isBigSize()).thenReturn(false);
        when(header.isCompressed()).thenReturn(false);
        when(header.isEncrypted()).thenReturn(false);
        when(header.isRawBytes()).thenReturn(false);
        when(header.isText()).thenReturn(false);
        when(message.getHeader()).thenReturn(header);
        when(message.getSize()).thenReturn(3);
        when(message.getContent()).thenReturn("abc".getBytes());
        when(message.getByteCount()).thenReturn(1 + 2 + 3);
        byte[] bytes = instance.convert(message);
        assert bytes[0] == 0;
    }

    @Test
    public void test2() {
        EzySimpleMessageToBytes instance = new EzySimpleMessageToBytes();
        EzyMessage message = mock(EzyMessage.class);
        EzyMessageHeader header = mock(EzyMessageHeader.class);
        when(header.isBigSize()).thenReturn(true);
        when(header.isCompressed()).thenReturn(true);
        when(header.isEncrypted()).thenReturn(true);
        when(header.isRawBytes()).thenReturn(true);
        when(header.isText()).thenReturn(true);
        when(message.getHeader()).thenReturn(header);
        when(message.hasBigSize()).thenReturn(true);
        when(message.getSize()).thenReturn(3);
        when(message.getContent()).thenReturn("abc".getBytes());
        when(message.getByteCount()).thenReturn(1 + 4 + 3);
        byte[] bytes = instance.convert(message);
        assert bytes[0] > 1;
    }
}
