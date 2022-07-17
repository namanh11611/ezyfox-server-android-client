package com.tvd12.ezyfoxserver.client.request;

import com.tvd12.ezyfoxserver.client.constant.EzyCommand;
import com.tvd12.ezyfoxserver.client.entity.EzyData;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;

public class EzyAppExitRequest implements EzyRequest {

    protected final int appId;

    public EzyAppExitRequest (int appId) {
        this.appId = appId;
    }

    @Override
    public EzyData serialize () {
        return EzyEntityFactory.newArrayBuilder()
            .append(appId)
            .build();
    }

    @Override
    public Object getCommand () {
        return EzyCommand.APP_EXIT;
    }
}
