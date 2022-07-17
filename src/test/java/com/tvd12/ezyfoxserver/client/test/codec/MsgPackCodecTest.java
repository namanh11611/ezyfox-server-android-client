package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.entity.EzyArray;
import com.tvd12.ezyfoxserver.client.entity.EzyObject;
import org.msgpack.MessagePack;

public class MsgPackCodecTest extends CodecBaseTest {

    protected MessagePack msgPack;

    public MsgPackCodecTest() {
        this.msgPack = new MessagePack();
        this.msgPack.register(EzyObject.class, new MsgPackObjectTemplate());
        this.msgPack.register(EzyArray.class, new MsgPackArrayTemplate());
    }
}
