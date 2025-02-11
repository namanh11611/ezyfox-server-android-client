package com.tvd12.ezyfoxserver.client.handler;

import com.tvd12.ezyfoxserver.client.config.EzyClientConfig;
import com.tvd12.ezyfoxserver.client.config.EzyReconnectConfig;
import com.tvd12.ezyfoxserver.client.constant.EzyConnectionStatus;
import com.tvd12.ezyfoxserver.client.constant.EzyDisconnectReason;
import com.tvd12.ezyfoxserver.client.constant.EzyDisconnectReasons;
import com.tvd12.ezyfoxserver.client.event.EzyDisconnectionEvent;
import com.tvd12.ezyfoxserver.client.logger.EzyLogger;

public class EzyDisconnectionHandler
    extends EzyAbstractEventHandler<EzyDisconnectionEvent> {

    @Override
    public final void handle (EzyDisconnectionEvent event) {
        String reasonName = EzyDisconnectReasons.getDisconnectReasonName(
            event.getReason()
        );
        EzyLogger.info("handle disconnection, reason: " + reasonName);
        preHandle(event);
        EzyClientConfig config = client.getConfig();
        EzyReconnectConfig reconnectConfig = config.getReconnect();
        boolean shouldReconnect = shouldReconnect(event);
        boolean mustReconnect = reconnectConfig.isEnable() &&
            event.getReason() != EzyDisconnectReason.UNAUTHORIZED.getId() &&
            event.getReason() != EzyDisconnectReason.CLOSE.getId() &&
            shouldReconnect;
        boolean reconnecting = false;
        client.setStatus(EzyConnectionStatus.DISCONNECTED);
        if (mustReconnect) {
            reconnecting = client.reconnect();
        }
        if (reconnecting) {
            onReconnecting(event);
        } else {
            onDisconnected(event);
        }
        postHandle(event);
    }

    protected void preHandle (EzyDisconnectionEvent event) {}

    protected void onReconnecting (EzyDisconnectionEvent event) {}

    protected void onDisconnected (EzyDisconnectionEvent event) {}

    protected void postHandle (EzyDisconnectionEvent event) {}

    protected boolean shouldReconnect (EzyDisconnectionEvent event) {
        int reason = event.getReason();
        return reason != EzyDisconnectReason.ANOTHER_SESSION_LOGIN.getId();
    }
}
