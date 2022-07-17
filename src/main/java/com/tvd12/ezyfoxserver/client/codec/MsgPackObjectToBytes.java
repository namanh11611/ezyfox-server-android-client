package com.tvd12.ezyfoxserver.client.codec;

public class MsgPackObjectToBytes implements EzyObjectToBytes {

    private final EzyMessageSerializer serializer;

    public MsgPackObjectToBytes (EzyMessageSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public byte[] convert (Object object) {
        return serializer.serialize(object);
    }
}
