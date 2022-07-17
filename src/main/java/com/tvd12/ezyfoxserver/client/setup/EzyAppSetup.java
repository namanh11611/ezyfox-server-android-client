package com.tvd12.ezyfoxserver.client.setup;

import com.tvd12.ezyfoxserver.client.handler.EzyAppDataHandler;

public interface EzyAppSetup {

    EzyAppSetup addDataHandler (Object cmd, EzyAppDataHandler dataHandler);

    EzySetup done ();
}
