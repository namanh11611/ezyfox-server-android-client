package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzySimpleMessage;
import com.tvd12.ezyfoxserver.client.codec.EzySimpleMessageHeader;

import org.junit.Test;

public class EzySimpleMessageTest {

    @Test
    public void test() {
        EzySimpleMessageHeader header = new EzySimpleMessageHeader(
            false,
            false,
            false,
            false,
            false,
            false
        );
        EzySimpleMessage message = new EzySimpleMessage(
            header,
            new byte[]{'a', 'b', 'c'},
            3
        );
        assert message.getContentStartIndex() == 3;
        assert message.getSize() == 3;
        assert message.getHeader() != null;
        assert message.getByteCount() == 1 + 2 + 3;
        assert message.getContent().length == 3;
        System.out.println(message);
    }

    @Test
    public void test2() {
        EzySimpleMessageHeader header = new EzySimpleMessageHeader(
            false,
            false,
            false,
            false,
            true,
            false
        );
        EzySimpleMessage message = new EzySimpleMessage(
            header,
            new byte[]{'a', 'b', 'c'},
            3
        );
        assert message.getContentStartIndex() == 0;
        System.out.println(message);
    }
}
