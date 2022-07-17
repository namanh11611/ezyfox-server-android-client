package com.tvd12.ezyfoxserver.client.socket;

import com.tvd12.ezyfoxserver.client.entity.EzyData;
import com.tvd12.ezyfoxserver.client.request.EzyRequest;

public interface EzySender {

    void send (EzyRequest request);

    void send (Object cmd, EzyData data);
}
