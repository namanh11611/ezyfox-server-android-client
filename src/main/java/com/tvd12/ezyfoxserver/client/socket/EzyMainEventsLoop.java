package com.tvd12.ezyfoxserver.client.socket;

import android.os.Handler;
import android.os.Looper;

import com.tvd12.ezyfoxserver.client.EzyClient;
import com.tvd12.ezyfoxserver.client.EzyClients;
import com.tvd12.ezyfoxserver.client.concurrent.EzyThreadFactory;
import com.tvd12.ezyfoxserver.client.logger.EzyLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class EzyMainEventsLoop {

    protected volatile boolean active;
    protected final Handler uiHandler;

    public EzyMainEventsLoop () {
        this(new Handler(Looper.getMainLooper()));
    }

    public EzyMainEventsLoop (Handler uiHandler) {
        this.uiHandler = uiHandler;
    }

    public void start () {
        ThreadFactory threadFactory = EzyThreadFactory.builder()
            .poolName("main-events-loop")
            .build();
        Thread thread = threadFactory.newThread(new Runnable() {
            @Override
            public void run () {
                processEvents();
            }
        });
        thread.start();
    }

    protected void processEvents () {
        try {
            processEvents0();
        } catch (Exception e) {
            EzyLogger.warn("process events error", e);
        }
    }

    protected void processEvents0 () throws Exception {
        final List<EzyClient> cachedClients = new ArrayList<>();
        final EzyClients clients = EzyClients.getInstance();
        this.active = true;
        while (active) {
            //noinspection BusyWait
            Thread.sleep(3);
            uiHandler.post(new Runnable() {
                @Override
                public void run () {
                    clients.getClients(cachedClients);
                    for (EzyClient one : cachedClients)
                        one.processEvents();
                }
            });
        }
    }

    public void stop () {
        this.active = false;
    }
}
