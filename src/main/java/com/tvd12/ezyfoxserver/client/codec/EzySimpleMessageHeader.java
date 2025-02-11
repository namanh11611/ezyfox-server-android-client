package com.tvd12.ezyfoxserver.client.codec;

public class EzySimpleMessageHeader implements EzyMessageHeader {

    protected boolean bigSize;
    protected boolean encrypted;
    protected boolean compressed;
    protected boolean text;
    protected boolean rawBytes;
    protected boolean udpHandshake;

    public EzySimpleMessageHeader (
        boolean bigSize,
        boolean encrypted,
        boolean compressed,
        boolean text,
        boolean rawBytes,
        boolean udpHandshake
    ) {
        this.bigSize = bigSize;
        this.encrypted = encrypted;
        this.compressed = compressed;
        this.text = text;
        this.rawBytes = rawBytes;
        this.udpHandshake = udpHandshake;
    }

    @Override
    public boolean isBigSize () {
        return bigSize;
    }

    @Override
    public boolean isEncrypted () {
        return encrypted;
    }

    @Override
    public boolean isCompressed () {
        return compressed;
    }

    @Override
    public boolean isText () {
        return text;
    }

    @Override
    public boolean isRawBytes () {
        return rawBytes;
    }

    @Override
    public boolean isUdpHandshake () {
        return udpHandshake;
    }

    @Override
    public String toString () {
        return "<" +
            "bigSize: " + bigSize + ", " +
            "encrypted: " + encrypted + ", " +
            "compressed: " + compressed + ", " +
            "text: " + text + ", " +
            "rawBytes: " + rawBytes + ", " +
            "udpHandshake: " + udpHandshake +
            ">";
    }
}
