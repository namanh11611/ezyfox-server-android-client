package com.tvd12.ezyfoxserver.client.codec;

import java.util.Arrays;

public class EzySimpleMessage implements EzyMessage {

    private final int size;
    private final byte[] content;
    private final EzyMessageHeader header;
    private final int byteCount;

    public EzySimpleMessage (
        EzyMessageHeader header,
        byte[] content,
        int size
    ) {
        this.header = header;
        this.content = content;
        this.size = size;
        this.byteCount = 1 + getSizeLength() + getContent().length;
    }

    @Override
    public int getSize () {
        return size;
    }

    @Override
    public byte[] getContent () {
        return content;
    }

    @Override
    public EzyMessageHeader getHeader () {
        return header;
    }

    @Override
    public int getByteCount () {
        return byteCount;
    }

    @Override
    public int getSizeLength () {
        return hasBigSize() ? 4 : 2;
    }

    @Override
    public boolean hasBigSize () {
        return getHeader().isBigSize();
    }

    @Override
    public String toString () {
        return "(" +
            "header: " + header + ", " +
            "size: " + size + ", " +
            "byteCount: " + byteCount + ", " +
            "content: " + Arrays.toString(content) +
            ")";
    }
}
