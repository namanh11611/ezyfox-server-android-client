package com.tvd12.ezyfoxserver.client.manager;

import com.tvd12.ezyfoxserver.client.constant.EzyConstant;
import com.tvd12.ezyfoxserver.client.handler.EzyAppDataHandlers;
import com.tvd12.ezyfoxserver.client.handler.EzyDataHandler;
import com.tvd12.ezyfoxserver.client.handler.EzyDataHandlers;
import com.tvd12.ezyfoxserver.client.handler.EzyEventHandler;
import com.tvd12.ezyfoxserver.client.handler.EzyEventHandlers;

public interface EzyHandlerManager {

    EzyEventHandlers getEventHandlers ();

    EzyDataHandlers getDataHandlers ();

    EzyDataHandler getDataHandler (Object cmd);

    @SuppressWarnings("rawtypes")
    EzyEventHandler getEventHandler (EzyConstant eventType);

    void addDataHandler (Object cmd, EzyDataHandler dataHandler);

    @SuppressWarnings("rawtypes")
    void addEventHandler (EzyConstant eventType, EzyEventHandler eventHandler);

    EzyAppDataHandlers getAppDataHandlers (String appName);
}
