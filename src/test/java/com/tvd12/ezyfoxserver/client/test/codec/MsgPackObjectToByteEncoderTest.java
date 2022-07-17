package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzyMessageToBytes;
import com.tvd12.ezyfoxserver.client.codec.EzyObjectToMessage;
import com.tvd12.ezyfoxserver.client.codec.MsgPackObjectToByteEncoder;

import static org.mockito.Mockito.mock;

import org.junit.Test;

public class MsgPackObjectToByteEncoderTest {

    @Test
    public void test() throws Exception {
        EzyMessageToBytes messageToBytes = mock(EzyMessageToBytes.class);
        EzyObjectToMessage objectToMessage = mock(EzyObjectToMessage.class);
        MsgPackObjectToByteEncoder encoder = new MsgPackObjectToByteEncoder(
            messageToBytes, objectToMessage);
        encoder.encode(new Object());
    }
}
