package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.MsgPackCodecCreator;

import org.junit.Test;

public class MsgPackCodecCreatorTest {

    @Test
    public void test() {
        MsgPackCodecCreator codecCreator = new MsgPackCodecCreator();
        codecCreator.newDecoder(100);
        codecCreator.newEncoder();
    }
}
