package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.EzyDecodeState;
import com.tvd12.ezyfoxserver.client.codec.EzyIDecodeState;
import com.tvd12.test.assertion.Asserts;
import com.tvd12.test.base.BaseTest;

import org.junit.Test;

public class EzyDecodeStateTest extends BaseTest {

    @Test
    public void test() {
        EzyIDecodeState state = EzyDecodeState.PREPARE_MESSAGE;
        assert state.getId() == 0;
        Asserts.assertEquals(
            EzyDecodeState.valueOf("PREPARE_MESSAGE"),
            EzyDecodeState.PREPARE_MESSAGE
        );
    }
}
