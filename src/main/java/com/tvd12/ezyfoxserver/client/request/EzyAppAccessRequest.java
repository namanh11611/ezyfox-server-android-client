package com.tvd12.ezyfoxserver.client.request;

import com.tvd12.ezyfoxserver.client.constant.EzyCommand;
import com.tvd12.ezyfoxserver.client.entity.EzyData;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;

public class EzyAppAccessRequest implements EzyRequest {

    protected final String appName;
    protected final EzyData data;

    public EzyAppAccessRequest (String appName) {
        this(appName, null);
    }

    public EzyAppAccessRequest (String appName, EzyData data) {
        this.appName = appName;
        this.data = data;
    }

    @Override
    public EzyData serialize () {
        return EzyEntityFactory.newArrayBuilder()
            .append(appName)
            .append(data)
            .build();
    }

    @Override
    public Object getCommand () {
        return EzyCommand.APP_ACCESS;
    }
}
