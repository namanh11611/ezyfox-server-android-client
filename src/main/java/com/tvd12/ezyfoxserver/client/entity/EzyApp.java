package com.tvd12.ezyfoxserver.client.entity;

import com.tvd12.ezyfoxserver.client.EzyClient;
import com.tvd12.ezyfoxserver.client.handler.EzyAppDataHandler;
import com.tvd12.ezyfoxserver.client.request.EzyRequest;

public interface EzyApp {
    int getId ();

    String getName ();

    EzyClient getClient ();

    EzyZone getZone ();

    void send (EzyRequest request);

    void send (String cmd);

    void send (String cmd, EzyData data);

    void send (String cmd, EzyData data, boolean encrypted);

    EzyAppDataHandler getDataHandler (Object cmd);
}
