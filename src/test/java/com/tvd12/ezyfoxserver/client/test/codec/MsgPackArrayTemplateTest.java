package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.test.base.BaseTest;

import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MsgPackArrayTemplateTest extends BaseTest {

    private final MessagePack messagePack = new MessagePack();
    private final MsgPackArrayTemplate template = new MsgPackArrayTemplate();

    @Test
    public void test() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Packer packer = messagePack.createPacker(stream);
        template.write(packer, null, false);
    }
}
