package com.tvd12.ezyfoxserver.client.request;

import com.tvd12.ezyfoxserver.client.constant.EzyCommand;
import com.tvd12.ezyfoxserver.client.entity.EzyArray;
import com.tvd12.ezyfoxserver.client.entity.EzyData;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;

public class EzyPingRequest implements EzyRequest {

    @Override
    public EzyData serialize () {
        return EzyEntityFactory.EMPTY_ARRAY;
    }

    @Override
    public Object getCommand () {
        return EzyCommand.PING;
    }
}
