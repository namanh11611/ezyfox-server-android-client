package com.tvd12.ezyfoxserver.client.setup;

import com.tvd12.ezyfoxserver.client.event.EzyEventType;
import com.tvd12.ezyfoxserver.client.handler.EzyDataHandler;
import com.tvd12.ezyfoxserver.client.handler.EzyEventHandler;

public interface EzySetup {

    EzySetup addDataHandler (Object cmd, EzyDataHandler dataHandler);

    EzySetup addEventHandler (EzyEventType eventType, EzyEventHandler eventHandler);

    EzyAppSetup setupApp (String appName);
}
