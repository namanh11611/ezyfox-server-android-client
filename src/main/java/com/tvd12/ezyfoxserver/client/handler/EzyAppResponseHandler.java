package com.tvd12.ezyfoxserver.client.handler;

import com.tvd12.ezyfoxserver.client.entity.EzyApp;
import com.tvd12.ezyfoxserver.client.entity.EzyArray;
import com.tvd12.ezyfoxserver.client.entity.EzyData;
import com.tvd12.ezyfoxserver.client.logger.EzyLogger;

public class EzyAppResponseHandler extends EzyAbstractDataHandler {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void handle (EzyArray data) {
        int appId = data.get(0, int.class);
        EzyArray commandData = data.get(1, EzyArray.class);
        String cmd = commandData.get(0, String.class);
        EzyData responseData = commandData.get(1, EzyData.class, null);

        EzyApp app = client.getAppById(appId);
        if (app == null) {
            EzyLogger.info("receive message when has not joined app yet");
            return;
        }
        EzyAppDataHandler dataHandler = app.getDataHandler(cmd);
        if (dataHandler != null) {
            dataHandler.handle(app, responseData);
        } else {
            EzyLogger.warn("app: " + app.getName() + " has no handler for command: " + cmd);
        }
    }
}
