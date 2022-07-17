package com.tvd12.ezyfoxserver.client.codec;

import com.tvd12.ezyfoxserver.client.constant.EzyConstant;

public class EzySimpleCodecFactory implements EzyCodecFactory {

    private final EzyCodecCreator socketCodecCreator;

    public EzySimpleCodecFactory (boolean enableSSL) {
        this.socketCodecCreator = new MsgPackCodecCreator(enableSSL);
    }

    @Override
    public Object newEncoder (EzyConstant connectionType) {
        return socketCodecCreator.newEncoder();
    }

    @Override
    public Object newDecoder (EzyConstant connectionType) {
        return socketCodecCreator.newDecoder(Integer.MAX_VALUE);
    }
}
