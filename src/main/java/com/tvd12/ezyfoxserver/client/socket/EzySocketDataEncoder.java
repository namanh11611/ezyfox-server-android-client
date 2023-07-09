package com.tvd12.ezyfoxserver.client.socket;

public interface EzySocketDataEncoder {

    byte[] encode (Object data) throws Exception;

    byte[] toMessageContent(Object data) throws Exception;

    byte[] encryptMessageContent(
        byte[] messageContent,
        byte[] encryptionKey
    ) throws Exception;
}
