package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.codec.MsgPackType;
import com.tvd12.test.base.BaseTest;

import org.junit.Test;

public class MsgPackTypeTest extends BaseTest {

    @Test
    public void test() {
        assert MsgPackType.POSITIVE_FIXINT.getId() == 0;
        assert MsgPackType.POSITIVE_FIXINT.getName().equals("POSITIVE_FIXINT");
        MsgPackType.valueOf("POSITIVE_FIXINT");
    }
}
