package com.tvd12.ezyfoxserver.client.socket;

import com.tvd12.ezyfoxserver.client.entity.EzyArray;

public class EzySocketResponseApi extends EzyAbstractResponseApi {

    protected final EzySocketDataEncoder encoder;

    public EzySocketResponseApi(
        EzySocketDataEncoder encoder,
        EzyPacketQueue packetQueue
    ) {
        super(packetQueue);
        this.encoder = encoder;
    }

    @Override
    protected Object encodeData (EzyArray data) throws Exception {
        return encoder.encode(data);
    }

    @Override
    protected byte[] dataToMessageContent(EzyArray data) throws Exception {
        return encoder.toMessageContent(data);
    }

    @Override
    protected byte[] encryptMessageContent(
        byte[] messageContent,
        byte[] encryptionKey
    )  throws Exception {
        return encoder.encryptMessageContent(messageContent, encryptionKey);
    }
}
