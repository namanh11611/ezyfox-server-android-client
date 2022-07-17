package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzySimpleMessageHeader;

import org.junit.Test;

public class EzySimpleMessageHeaderTest {

    @Test
    public void test() {
        EzySimpleMessageHeader header = new EzySimpleMessageHeader(
            true,
            true,
            true,
            true,
            true,
            true
        );
        assert header.isBigSize();
        assert header.isEncrypted();
        assert header.isCompressed();
        assert header.isText();
        assert header.isRawBytes();
        assert header.isUdpHandshake();
    }
}
