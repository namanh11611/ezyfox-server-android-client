package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzyDecodeState;
import com.tvd12.test.assertion.Asserts;
import com.tvd12.test.base.BaseTest;

import org.junit.Test;

public class EzyDecodeState1Test extends BaseTest {

    @Test
    public void test() {
        Asserts.assertEquals(EzyDecodeState.PREPARE_MESSAGE.getId(), 0);
        Asserts.assertEquals(EzyDecodeState.READ_MESSAGE_HEADER.getId(), 1);
        Asserts.assertEquals(EzyDecodeState.READ_MESSAGE_SIZE.getId(), 2);
        Asserts.assertEquals(EzyDecodeState.READ_MESSAGE_CONTENT.getId(), 3);
    }
}
