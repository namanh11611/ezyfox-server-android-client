package com.tvd12.ezyfoxserver.client.test.codec;

import com.tvd12.ezyfoxserver.client.entity.EzyObject;
import org.msgpack.packer.Packer;
import org.msgpack.template.AbstractTemplate;
import org.msgpack.unpacker.Unpacker;

import java.io.IOException;

public class MsgPackObjectTemplate extends AbstractTemplate<EzyObject> {

    @Override
    public void write(Packer pk, EzyObject target, boolean required)
        throws IOException {
        if (target != null) {
            writeNotNull(pk, target);
        } else {
            writeNull(pk, required);
        }
    }

    private void writeNotNull(Packer pk, EzyObject target) throws IOException {
        pk.write(target.toMap());
    }

    private void writeNull(Packer pk, boolean required) throws IOException {
        if (!required) {
            pk.writeNil();
        } else {
            throw new NullPointerException("Attempted to write null");
        }
    }

    @Override
    public EzyObject read(Unpacker u, EzyObject to, boolean required) {
        throw new UnsupportedOperationException();
    }
}
