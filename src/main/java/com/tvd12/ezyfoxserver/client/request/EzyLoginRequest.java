package com.tvd12.ezyfoxserver.client.request;

import com.tvd12.ezyfoxserver.client.constant.EzyCommand;
import com.tvd12.ezyfoxserver.client.entity.EzyData;
import com.tvd12.ezyfoxserver.client.factory.EzyEntityFactory;

public class EzyLoginRequest implements EzyRequest {

    private final String zoneName;
    private final String username;
    private final String password;
    private final EzyData data;

    public EzyLoginRequest (
        String zoneName,
        String username,
        String password
    ) {
        this(zoneName, username, password, null);
    }

    public EzyLoginRequest (
        String zoneName,
        String username,
        String password,
        EzyData data
    ) {
        this.zoneName = zoneName;
        this.username = username;
        this.password = password;
        this.data = data;
    }

    @Override
    public Object getCommand () {
        return EzyCommand.LOGIN;
    }

    @Override
    public EzyData serialize () {
        return EzyEntityFactory.newArrayBuilder()
            .append(zoneName)
            .append(username)
            .append(password)
            .append(data).build();
    }
}
